package be.kdg.sa.land.controller.mvc;

import be.kdg.sa.land.controller.dto.DeliveryAppointmentDto;
import be.kdg.sa.land.controller.dto.TruckDto;
import be.kdg.sa.land.domain.DeliveryAppointment;
import be.kdg.sa.land.domain.MaterialType;
import be.kdg.sa.land.domain.SellerParty;
import be.kdg.sa.land.domain.Truck;
import be.kdg.sa.land.domain.exceptions.InvalidDataException;
import be.kdg.sa.land.services.SellerPartyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SellerPartyController {

    private final Logger logger = LoggerFactory.getLogger(SellerPartyController.class);
    private final SellerPartyService sellerPartyService;

    public SellerPartyController(SellerPartyService sellerPartyService) {
        this.sellerPartyService = sellerPartyService;
    }

    @GetMapping("/sellerParties")
    public String sellerParties(Model model) {
        logger.info("Getting all seller parties");
        model.addAttribute("sellerParties", sellerPartyService.getSellerParties());
        return "sellerParties";
    }

    @GetMapping("/sellerParties/{id}/addTruck")
    public String showAddTruckForm(@PathVariable long id, Model model) {
        logger.info("Showing add truck form for SellerParty with ID: {}", id);
        SellerParty sellerParty = sellerPartyService.getSellerPartyById(id);
        model.addAttribute("sellerParty", sellerParty);
        model.addAttribute("newTruck", new TruckDto("", 0, 0, null));
        model.addAttribute("showAddTruckFormId", id); //To show the form of add Truck button
        model.addAttribute("sellerParties", sellerPartyService.getSellerParties());
        return "sellerParties";
    }

    @GetMapping("/sellerParties/{id}/makeAppointment")
    public String showMakeAppointmentForm(@PathVariable long id, Model model) {
        logger.info("Showing make appointment form for SellerParty with ID: {}", id);
        SellerParty sellerParty = sellerPartyService.getSellerPartyById(id);
        model.addAttribute("sellerParty", sellerParty);
        model.addAttribute("trucks", sellerPartyService.getTrucksBySellerPartyId(id));
        model.addAttribute("newAppointment", new DeliveryAppointmentDto(null, 0));
        model.addAttribute("materialTypes", MaterialType.values());
        model.addAttribute("showMakeAppointmentFormId", id); //To show the form of make Appointment button
        model.addAttribute("sellerParties", sellerPartyService.getSellerParties());
        return "sellerParties";
    }

    @GetMapping("/appointmentDetails/{id}")
    public String showAppointmentDetails(@PathVariable long id, Model model) {
        logger.info("Showing appointment details for appointment with ID: {}", id);
        DeliveryAppointment appointment = sellerPartyService.getAppointmentById(id);
        model.addAttribute("appointment", appointment);
        return "appointmentDetails";
    }

    @PostMapping("/sellerParties/{id}/addTruck")
    public String addTruck(@PathVariable long id, @ModelAttribute Truck newTruck, Model model) {
        try {
            logger.info("Adding truck to SellerParty with ID: {}", id);
            sellerPartyService.addTruckToSellerParty(id, newTruck);
            return "redirect:/sellerParties"; // redirect back to the seller parties page
        } catch (DataIntegrityViolationException e) {
            logger.error("Error adding truck to SellerParty with ID: {}", id);
            model.addAttribute("error", "Truck with license plate already exists. Please use a unique license plate.");
            return showAddTruckForm(id, model);
        } catch (InvalidDataException e) {
            model.addAttribute("error", e.getMessage());
            return showAddTruckForm(id, model);
        }
    }

    @PostMapping("/sellerParties/{id}/makeAppointment")
    public String makeAppointment(@PathVariable long id, @ModelAttribute DeliveryAppointmentDto newAppointmentDto, Model model) {
        try {
            logger.info("Making appointment for SellerParty with ID: {}", id);
            DeliveryAppointment createdAppointment = sellerPartyService.addAppointment(id, newAppointmentDto); // Ensure this method exists in your service
            return "redirect:/appointmentDetails/" + createdAppointment.getId(); // Redirect back to the seller parties page
        } catch (InvalidDataException e) {
            logger.error("Error making appointment for SellerParty with ID: {}", id);
            model.addAttribute("error", e.getMessage());
            return showMakeAppointmentForm(id, model); // Show form again with error message
        }
    }
}
