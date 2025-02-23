package be.kdg.sa.land.controller.mvc;

import be.kdg.sa.land.domain.DeliveryAppointment;
import be.kdg.sa.land.services.DeliveryAppointmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DeliveryAppointmentController {

    private final Logger logger = LoggerFactory.getLogger(DeliveryAppointmentController.class);
    private final DeliveryAppointmentService deliveryAppointmentService;

    public DeliveryAppointmentController(DeliveryAppointmentService deliveryAppointmentServiceImp) {
        this.deliveryAppointmentService = deliveryAppointmentServiceImp;
    }

    @GetMapping("/appointments")
    public String appointments(Model model) {
        logger.info("Getting all appointments");
        List<DeliveryAppointment> appointments = deliveryAppointmentService.getAllDeliveryAppointments();
        model.addAttribute("appointments", appointments);
        return "deliveryAppointments";
    }

    @PostMapping("/appointments/{id}/delete")
    public String deleteAppointment(@PathVariable Long id) {
        logger.info("Deleting appointment with ID: {}", id);
        deliveryAppointmentService.deleteAppointment(id);
        return "redirect:/appointments"; // Redirect back to the view after deletion
    }
}
