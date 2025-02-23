package be.kdg.sa.land.controller.api;

import be.kdg.sa.land.controller.dto.DeliveryAppointmentDto;
import be.kdg.sa.land.domain.DeliveryAppointment;
import be.kdg.sa.land.services.DeliveryAppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class DeliveryAppointmentRestController {

    Logger logger = Logger.getLogger(DeliveryAppointmentRestController.class.getName());
    private final DeliveryAppointmentService deliveryAppointmentService;

    public DeliveryAppointmentRestController(DeliveryAppointmentService deliveryAppointmentServiceImp) {
        this.deliveryAppointmentService = deliveryAppointmentServiceImp;
    }

    @DeleteMapping("/appointments/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        logger.info("Deleting appointment with ID: " + id);
        deliveryAppointmentService.deleteAppointment(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<DeliveryAppointment>> getAllAppointments() {
        logger.info("Getting all appointments");
        List<DeliveryAppointment> appointments = deliveryAppointmentService.getAllDeliveryAppointments();
        return ResponseEntity.ok(appointments); // Return JSON list of appointments
    }

    @GetMapping("/appointments/{id}")
    public ResponseEntity<DeliveryAppointmentDto> getAppointmentById(@PathVariable long id) {
        logger.info("Getting appointment with ID: " + id);
        return this.deliveryAppointmentService.getDeliveryAppointmentById(id)
                .map(appointment -> ResponseEntity.ok(this.mapToDto(appointment)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private DeliveryAppointmentDto mapToDto(DeliveryAppointment appointment) {
        return new DeliveryAppointmentDto(
                appointment.getScheduledArrivalTime(),
                appointment.getTruck().getId()
        );
    }
}
