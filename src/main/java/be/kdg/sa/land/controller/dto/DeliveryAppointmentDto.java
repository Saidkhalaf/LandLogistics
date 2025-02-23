package be.kdg.sa.land.controller.dto;

import be.kdg.sa.land.domain.DeliveryAppointment;
import be.kdg.sa.land.domain.MaterialType;
import be.kdg.sa.land.domain.Truck;
import be.kdg.sa.land.repositories.TruckRepository;

import java.time.LocalDateTime;

public record DeliveryAppointmentDto(LocalDateTime scheduledArrivalTime, long truckId) {
    public DeliveryAppointment toEntity(TruckRepository truckRepository) {
        DeliveryAppointment deliveryAppointment = new DeliveryAppointment();
        deliveryAppointment.setScheduledArrivalTime(this.scheduledArrivalTime);
        Truck truck = truckRepository.findById(this.truckId)
                .orElseThrow( () -> new IllegalArgumentException("Truck not found with ID: " + this.truckId));
        deliveryAppointment.setTruck(truck);
        return deliveryAppointment;
    }
}
