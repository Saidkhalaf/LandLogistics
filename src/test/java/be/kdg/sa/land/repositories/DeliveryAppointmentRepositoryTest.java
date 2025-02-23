package be.kdg.sa.land.repositories;

import be.kdg.sa.land.domain.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class DeliveryAppointmentRepositoryTest {

    @Autowired
    private DeliveryAppointmentRepository deliveryAppointmentRepository;
    @Autowired
    private TruckRepository truckRepository;
    @Autowired
    private SellerPartyRepository sellerPartyRepository;

    @Test
    void canAddNewDeliveryAppointment() {
        // Arrange
        Truck truck = new Truck("ABC999", null, MaterialType.GYPSUM, new SellerParty("SteelCo Ltd."));
        this.truckRepository.save(truck);

        DeliveryAppointment deliveryAppointment = new DeliveryAppointment(
                LocalDateTime.of(2024, 10, 07, 12, 30, 00), truck);

        // Act
        DeliveryAppointment savedDeliveryAppointment = this.deliveryAppointmentRepository.save(deliveryAppointment);
        // Assert
        assertNotNull(savedDeliveryAppointment.getId());
        assertEquals("ABC999", savedDeliveryAppointment.getTruck().getlicensePlate());
        assertEquals("SteelCo Ltd.", savedDeliveryAppointment.getTruck().getSellerParty().getName());
    }

    @Test
    void canFetchDeliveryAppointmentById() {
        // Arrange
        SellerParty sellerParty = new SellerParty("IronCo Ltd.");
        this.sellerPartyRepository.save(sellerParty);

        Truck truck = new Truck("DEF456", null, MaterialType.GYPSUM, sellerParty);
        this.truckRepository.save(truck);

        DeliveryAppointment deliveryAppointment = new DeliveryAppointment(
                LocalDateTime.of(2024, 10, 8, 12, 30, 00), truck);
        DeliveryAppointment savedDeliveryAppointment = this.deliveryAppointmentRepository.save(deliveryAppointment);
        // Act
        Optional<DeliveryAppointment> fetchedDeliveryAppointment = this.deliveryAppointmentRepository.findById(savedDeliveryAppointment.getId());
        // Assert
        assertTrue(fetchedDeliveryAppointment.isPresent());
        assertEquals(savedDeliveryAppointment.getId(), fetchedDeliveryAppointment.get().getId());
    }

    @Test
    void cannotAddDeliveryAppointmentWithoutRequiredFields() {
        // Arrange
        Truck truck = new Truck("GHI789", null, MaterialType.CEMENT, new SellerParty("CementCo Ltd."));
        this.truckRepository.save(truck);

        DeliveryAppointment deliveryAppointment = new DeliveryAppointment();
        deliveryAppointment.setTruck(truck);
        deliveryAppointment.setScheduledArrivalTime(null); // Missing required field

        // Act & Assert
        assertThrows(Exception.class, () -> {
            this.deliveryAppointmentRepository.save(deliveryAppointment);
        });
    }

    @Test
    void CanDeleteExistingDeliveryAppointment() {
        // Arrange
        Truck truck = new Truck("JKL123", null, MaterialType.CEMENT, new SellerParty("CementCo Ltd."));
        this.truckRepository.save(truck);

        DeliveryAppointment deliveryAppointment = new DeliveryAppointment(
                LocalDateTime.of(2024, 10, 8, 12, 30, 00), truck);
        DeliveryAppointment savedDeliveryAppointment = this.deliveryAppointmentRepository.save(deliveryAppointment);
        // Act
        this.deliveryAppointmentRepository.deleteById(savedDeliveryAppointment.getId());
        // Assert
        Optional<DeliveryAppointment> fetchedDeliveryAppointment = this.deliveryAppointmentRepository.findById(savedDeliveryAppointment.getId());
        assertFalse(fetchedDeliveryAppointment.isPresent());
    }
}