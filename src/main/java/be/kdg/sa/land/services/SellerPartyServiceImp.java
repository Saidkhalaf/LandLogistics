package be.kdg.sa.land.services;

import be.kdg.sa.land.controller.dto.DeliveryAppointmentDto;
import be.kdg.sa.land.domain.DeliveryAppointment;
import be.kdg.sa.land.domain.SellerParty;
import be.kdg.sa.land.domain.Truck;
import be.kdg.sa.land.domain.exceptions.InvalidDataException;
import be.kdg.sa.land.repositories.DeliveryAppointmentRepository;
import be.kdg.sa.land.repositories.SellerPartyRepository;
import be.kdg.sa.land.repositories.TruckRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SellerPartyServiceImp implements SellerPartyService{

    private static final Logger logger = LoggerFactory.getLogger(SellerPartyServiceImp.class);
    private final SellerPartyRepository sellerPartyRepository;
    private final TruckRepository truckRepository;
    private final DeliveryAppointmentRepository deliveryAppointmentRepository;

    public SellerPartyServiceImp(SellerPartyRepository sellerPartyRepository, TruckRepository truckRepository, DeliveryAppointmentRepository deliveryAppointmentRepository) {
        this.sellerPartyRepository = sellerPartyRepository;
        this.truckRepository = truckRepository;
        this.deliveryAppointmentRepository = deliveryAppointmentRepository;
    }

    @Override
    public List<SellerParty> getSellerParties() {
        return sellerPartyRepository.findAll();
    }

    @Override
    public Truck addTruckToSellerParty(long sellerPartyId, Truck newTruck) {
        logger.info("Adding truck to SellerParty with ID: {}", sellerPartyId);
        SellerParty sellerParty = sellerPartyRepository.findById(sellerPartyId)
                .orElseThrow(() -> new NoSuchElementException("SellerParty not found with ID: " + sellerPartyId));
        if (!newTruck.getLicensePlate().matches("^[a-zA-Z0-9]{6,8}$")) {
            logger.error("Invalid license plate format");
            throw new InvalidDataException("Invalid license plate format");
        }
        newTruck.setId(0); // To ensure a new truck is created
        newTruck.setSellerParty(sellerParty);
        return truckRepository.save(newTruck); // Return the saved truck
    }


    @Override
    public SellerParty getSellerPartyById(long id) {
        logger.info("Getting SellerParty with ID: {}", id);
        return sellerPartyRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("SellerParty not found with ID: "+ id));
    }

    @Override
    public DeliveryAppointment addAppointment(long sellerPartyId, DeliveryAppointmentDto newAppointmentDto) {
        logger.info("Adding appointment to SellerParty with ID: {}", sellerPartyId);
        SellerParty sellerParty = sellerPartyRepository.findById(sellerPartyId)
                .orElseThrow(() -> new NoSuchElementException("SellerParty not found with ID: " + sellerPartyId));
        logger.info("SellerParty found with ID: {}", sellerPartyId);
        Truck truck = truckRepository.findById(newAppointmentDto.truckId())
                .orElseThrow(() -> new NoSuchElementException("Truck not found with ID: " + newAppointmentDto.truckId()));

        boolean hasFutureAppointment = deliveryAppointmentRepository.existsByTruckAndScheduledArrivalTimeAfter(truck, LocalDateTime.now());
        if (hasFutureAppointment) {
            logger.error("Truck with License Plate: {} already has a future appointment", truck.getLicensePlate());
            throw new InvalidDataException("Truck with License Plate: " + truck.getLicensePlate() + " already has a future appointment.");
        }

        DeliveryAppointment newAppointment = new DeliveryAppointment();
        newAppointment.setSellerParty(sellerParty);
        newAppointment.setTruck(truck);
        newAppointment.setScheduledArrivalTime(newAppointmentDto.scheduledArrivalTime());
        return deliveryAppointmentRepository.save(newAppointment);
    }

    @Override
    public List<Truck> getTrucksBySellerPartyId(long sellerPartyId) {
        return sellerPartyRepository.findTrucksBySellerPartyId(sellerPartyId);
    }

    @Override
    public DeliveryAppointment getAppointmentById(long id) {
        logger.info("Getting appointment with ID: {}", id);
        return deliveryAppointmentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Appointment not found with ID: " + id));
    }
}
