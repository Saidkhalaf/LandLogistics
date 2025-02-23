package be.kdg.sa.land.controller.mvc;

import be.kdg.sa.land.domain.MaterialType;
import be.kdg.sa.land.domain.WaitingList;
import be.kdg.sa.land.services.WaitingListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class WaitingListController {

    private final Logger logger = LoggerFactory.getLogger(WaitingListController.class);
    private final WaitingListService waitingListService;

    public WaitingListController(WaitingListService waitingListService) {
        this.waitingListService = waitingListService;
    }

    @GetMapping("/waiting-lists")
    public String waitingLists(Model model) {
        logger.info("Getting all waiting lists");
        model.addAttribute("waitingLists", waitingListService.getAllWaitingLists());
        return "waiting-lists"; // Return the HTML page view
    }

    @GetMapping("/waiting-list/{id}")
    public ModelAndView viewWaitingList(@PathVariable long id) {
        logger.info("Getting WaitingList with ID: {}", id);
        ModelAndView mav = new ModelAndView("waitingListView");
        WaitingList waitingList = waitingListService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Waiting list not found"));
        mav.addObject("waitingList", waitingList);
        return mav;
    }

    @GetMapping("/waiting-list/{waitingListId}/addTruck")
    public ModelAndView showAddTruckForm(@PathVariable long waitingListId) {
        logger.info("Showing add truck form for WaitingList with ID: {}", waitingListId);
        ModelAndView mav = new ModelAndView("addTruck");
        WaitingList waitingList = waitingListService.findById(waitingListId)
                .orElseThrow(() -> new IllegalArgumentException("Waiting list not found"));
        mav.addObject("waitingList", waitingList);
        return mav;
    }

    @PostMapping("/waiting-list/{waitingListId}/addTruck")
    public ModelAndView addTruckToWaitingList(@PathVariable long waitingListId,
                                                                                      @RequestParam String licensePlate,
                                                                                      @RequestParam String sellerName,
                                                                                      @RequestParam MaterialType materialType) {
        try {
            logger.info("Adding truck to WaitingList with ID: {}", waitingListId);
            waitingListService.addTruckWithoutAppointmentToWaitingList(waitingListId, licensePlate, sellerName, materialType);
            return new ModelAndView("redirect:/waiting-list/" + waitingListId);
        } catch (Exception e) {
            logger.error("Failed to add truck to WaitingList with ID: {}", waitingListId, e);
            return new ModelAndView("error").addObject("message", "Failed to add truck: " + e.getMessage());
        }
    }
}
