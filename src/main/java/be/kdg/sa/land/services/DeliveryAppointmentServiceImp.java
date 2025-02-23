package be.kdg.sa.land.services;

import be.kdg.sa.land.domain.DeliveryAppointment;
import be.kdg.sa.land.repositories.DeliveryAppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryAppointmentServiceImp implements DeliveryAppointmentService {

    private final DeliveryAppointmentRepository deliveryAppointmentRepository;

    public DeliveryAppointmentServiceImp(DeliveryAppointmentRepository deliveryAppointmentRepository) {
        this.deliveryAppointmentRepository = deliveryAppointmentRepository;
    }

    @Override
    public List<DeliveryAppointment> getAllDeliveryAppointments() {
        return deliveryAppointmentRepository.findAll();
    }

    @Override
    public void deleteAppointment(Long id) {
        deliveryAppointmentRepository.deleteById(id);
    }

    @Override
    public Optional<DeliveryAppointment> getDeliveryAppointmentById(long id) {
        return deliveryAppointmentRepository.findById(id);
    }
}
