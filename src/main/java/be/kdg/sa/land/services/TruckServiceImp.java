package be.kdg.sa.land.services;

import be.kdg.sa.land.domain.DeliveryAppointment;
import be.kdg.sa.land.domain.Truck;
import be.kdg.sa.land.repositories.DeliveryAppointmentRepository;
import be.kdg.sa.land.repositories.TruckRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class TruckServiceImp implements TruckService {

    private  final TruckRepository truckRepository;
    private final DeliveryAppointmentRepository deliveryAppointmentRepository;

    public TruckServiceImp(TruckRepository truckRepository, DeliveryAppointmentRepository deliveryAppointmentRepository) {
        this.truckRepository = truckRepository;
        this.deliveryAppointmentRepository = deliveryAppointmentRepository;
    }

    public List<Truck> getTrucks() {
        return truckRepository.findAll();
    }

    @Override
    public boolean validateLicensePlate(String licensePlate) {
        //Check if the truck exists
        Truck truck = truckRepository.findTruckByLicensePlate(licensePlate);
        if (truck == null) {
            return false;
        }

        // Check if the truck has any valid appointments
        List<DeliveryAppointment> deliveryAppointments = truck.getDeliveryAppointments();
        if (deliveryAppointments == null || deliveryAppointments.isEmpty()) {
            return false;
        }

        LocalDateTime now = LocalDateTime.now();
        for(DeliveryAppointment appointment : deliveryAppointments) {
            LocalDateTime scheduledArrivalTime = appointment.getScheduledArrivalTime();
            if(scheduledArrivalTime != null) {
                LocalDateTime endWindow = scheduledArrivalTime.plusHours(1);

                // Set the actual arrival time
                appointment.setActualArrivalTime(now);
                deliveryAppointmentRepository.save(appointment);

                if(now.isAfter(scheduledArrivalTime) && now.isBefore(endWindow)) {
                    return true; //Truck is within the appointment window
                } else if(now.isBefore(scheduledArrivalTime)) {
                    // Truck has to wait until the scheduled time
                    return false; // Truck is too early and has to wait until 8:00
                } else if (now.isAfter(endWindow) && now.toLocalTime().isBefore(LocalTime.of(20, 0))) {
                    return false; //Truck has to wait until 20:00
                }
            }
        }
        return false;
    }

    @Override
    public Truck findTruckByLicensePlate(String licensePlate) {
        return truckRepository.findTruckByLicensePlate(licensePlate);
    }
}
