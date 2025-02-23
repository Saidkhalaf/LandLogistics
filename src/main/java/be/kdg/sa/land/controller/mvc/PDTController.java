package be.kdg.sa.land.controller.mvc;

import be.kdg.sa.land.controller.dto.PDTDto;
import be.kdg.sa.land.services.PDTServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PDTController {

    private final Logger logger = LoggerFactory.getLogger(PDTController.class);
    private final PDTServiceImpl pdtServiceImpl;

    public PDTController(PDTServiceImpl pdtServiceImpl) {
        this.pdtServiceImpl = pdtServiceImpl;
    }

    @GetMapping("/pdt/{id}")
    public String getPDT(@PathVariable long id, Model model) {
        logger.info("Getting PDT with ID: {}", id);
        PDTDto pdtDto = pdtServiceImpl.findPDTById(id);
        model.addAttribute("pdt", pdtDto);
        return "pdtView";
    }
}
