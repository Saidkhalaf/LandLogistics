package be.kdg.sa.land.controller.mvc;

import be.kdg.sa.land.controller.dto.WbtDto;
import be.kdg.sa.land.services.WeighBridgeAssignmentService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WbtController {

    private final Logger logger = LoggerFactory.getLogger(WbtController.class);
    private final WeighBridgeAssignmentService weighBridgeAssignmentService;

    public WbtController(WeighBridgeAssignmentService weighBridgeAssignmentService) {
        logger.info("Creating WbtController");
        this.weighBridgeAssignmentService = weighBridgeAssignmentService;
    }

    @GetMapping("/assignWbt")
    public String assignWbt(Model model, HttpSession session) {
        String licensePlate = (String) session.getAttribute("licensePlate");
        if (licensePlate != null) {
            logger.info("Assigning weigh bridge to license plate");
            WbtDto wbtDto = weighBridgeAssignmentService.assignWeighBridge(licensePlate, model);
          model.addAttribute("wbt", wbtDto);
        } else {
            logger.error("No license plate found in session.");
            model.addAttribute("error", "No license plate found in session.");
        }
        return "assignWbt";
    }
}
