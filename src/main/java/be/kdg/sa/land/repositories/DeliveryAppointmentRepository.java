package be.kdg.sa.land.repositories;

import be.kdg.sa.land.domain.DeliveryAppointment;
import be.kdg.sa.land.domain.Truck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface DeliveryAppointmentRepository extends JpaRepository<DeliveryAppointment, Long> {
    boolean existsByTruckAndScheduledArrivalTimeAfter(Truck truck,LocalDateTime dateTime);
}
