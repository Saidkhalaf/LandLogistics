package be.kdg.sa.land.controller.mvc;

import be.kdg.sa.land.services.WeighBridgeAssignmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeighBridgeController {

    private final Logger logger = LoggerFactory.getLogger(WeighBridgeController.class);
    private final WeighBridgeAssignmentService weighBridgeAssignmentService;

    public WeighBridgeController(WeighBridgeAssignmentService weighBridgeAssignmentService) {
        logger.info("Creating WeighBridgeController");
        this.weighBridgeAssignmentService = weighBridgeAssignmentService;
    }

    @GetMapping("/assignWeighBridge")
    public String assignWeighBridge() {
        logger.info("Assigning weigh bridge to license plate");
        return "assignWbt";
    }

    @PostMapping("/assignWeighBridge")
    public String assignWeighBridge(@RequestParam String licensePlate, Model model) {
        logger.info("Assigning weigh bridge to license plate: {}", licensePlate);
        weighBridgeAssignmentService.assignWeighBridge(licensePlate, model);
        return "assignWbt";
    }

}
