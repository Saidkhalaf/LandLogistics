package be.kdg.sa.land.controller.mvc;

import be.kdg.sa.land.controller.dto.PDTDto;
import be.kdg.sa.land.controller.dto.WbtDto;
import be.kdg.sa.land.services.PDTServiceImpl;
import be.kdg.sa.land.services.TruckServiceImp;
import be.kdg.sa.land.services.WeighBridgeAssignmentService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TruckController {

    private final Logger logger = LoggerFactory.getLogger(TruckController.class);
    private final TruckServiceImp truckServiceImp;
    private final WeighBridgeAssignmentService weighBridgeAssignmentService;
    private final PDTServiceImpl pdtServiceImpl;

    public TruckController(TruckServiceImp truckServiceImp, WeighBridgeAssignmentService weighBridgeAssignmentService, PDTServiceImpl pdtServiceImpl) {
        this.truckServiceImp = truckServiceImp;
        this.weighBridgeAssignmentService = weighBridgeAssignmentService;
        this.pdtServiceImpl = pdtServiceImpl;
    }

    @GetMapping("/trucks")
    public String trucks(Model model) {
        logger.info("Getting all trucks");
        model.addAttribute("trucks", truckServiceImp.getTrucks());
        return "trucks";
    }

    @GetMapping("/checkLicensePlate")
    public String showCheckLicensePlate() {
        logger.info("Showing check license plate form");
        return "checkLicensePlate";
    }

    @GetMapping("/pdtView")
    public String pdtView(@RequestParam(name = "licensePlate", required = false) String licensePlate, Model model) {
        if (licensePlate == null || licensePlate.isEmpty()) {
            logger.error("License plate is required.");
            model.addAttribute("error", "License plate is required.");
            return "checkLicensePlate";
        }
        logger.info("Getting PDT for license plate: {}", licensePlate);
        PDTDto pdtDto = pdtServiceImpl.generatePDTByLicensePlate(licensePlate);
        model.addAttribute("pdt", pdtDto);
        return "pdtView";
    }

    @PostMapping("/checkLicensePlate")
    public String checkLicensePlate(@RequestParam String licensePlate, Model model, HttpSession session) {
        logger.info("Checking license plate: {}", licensePlate);
        boolean isValid = truckServiceImp.validateLicensePlate(licensePlate);
        session.setAttribute("licensePlate", licensePlate);

        if (isValid) {
            logger.info("Truck is within the appointment window.");
            model.addAttribute("message", "Truck is within the appointment window.");
            model.addAttribute("licensePlate", licensePlate);

            // Check if PDT already exists
            PDTDto pdtDto = pdtServiceImpl.findPDTByLicensePlate(licensePlate);
            if (pdtDto == null) {
                // Create PDT automatically
                logger.info("Generating PDT for license plate: {}", licensePlate);
                pdtDto = pdtServiceImpl.generatePDTByLicensePlate(licensePlate);
            }
            model.addAttribute("pdt", pdtDto);

            // Assign WBT
            logger.info("Assigning weigh bridge to license plate: {}", licensePlate);
            WbtDto wbtDto = weighBridgeAssignmentService.assignWeighBridge(licensePlate, model);
            model.addAttribute("wbt", wbtDto);
        } else {
            logger.error("Truck is not within the appointment window or has to wait.");
            model.addAttribute("error", "Truck is not within the appointment window or has to wait.");
        }

        return "checkLicensePlate";
    }
}
