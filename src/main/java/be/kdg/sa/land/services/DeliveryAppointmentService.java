package be.kdg.sa.land.services;

import be.kdg.sa.land.domain.DeliveryAppointment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DeliveryAppointmentService {
    List<DeliveryAppointment> getAllDeliveryAppointments();
    void deleteAppointment(Long id);
    Optional<DeliveryAppointment> getDeliveryAppointmentById(long id);
}
