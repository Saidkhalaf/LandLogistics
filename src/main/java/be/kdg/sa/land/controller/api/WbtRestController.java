package be.kdg.sa.land.controller.api;

import be.kdg.sa.land.controller.dto.WbtDto;
import be.kdg.sa.land.services.WeighBridgeAssignmentService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class WbtRestController {

    Logger logger = Logger.getLogger(WbtRestController.class.getName());
    private final WeighBridgeAssignmentService weighBridgeAssignmentService;

    public WbtRestController(WeighBridgeAssignmentService weighBridgeAssignmentService) {
        this.weighBridgeAssignmentService = weighBridgeAssignmentService;
    }

    @PostMapping("/wbt/assign")
    public WbtDto assignWbt(@RequestParam String licensePlate, Model model) {
        logger.info("Assigning weigh bridge to license plate: " + licensePlate);
        return weighBridgeAssignmentService.assignWeighBridge(licensePlate, model);
    }
}
