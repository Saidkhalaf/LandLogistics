package be.kdg.sa.land.controller.api;

import be.kdg.sa.land.controller.dto.WaitingListDto;
import be.kdg.sa.land.domain.WaitingList;
import be.kdg.sa.land.services.WaitingListServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class WaitingListRestController {

    Logger logger = Logger.getLogger(WaitingListRestController.class.getName());
    private final WaitingListServiceImp waitingListServiceImp;

    public WaitingListRestController(WaitingListServiceImp waitingListServiceImp) {
        this.waitingListServiceImp = waitingListServiceImp;
    }

    @GetMapping("/waiting-lists/{id}")
    public ResponseEntity<WaitingListDto> findById(@PathVariable long id) {
        logger.info("Getting WaitingList with ID: " + id);
        return this.waitingListServiceImp.findById(id)
                .map(waitingList -> ResponseEntity.ok(mapToDto(waitingList)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/waiting-lists/all")
    public ResponseEntity<List<WaitingListDto>> getAllWaitingLists() {
        logger.info("Getting all waiting lists");
        List<WaitingList> waitingLists = this.waitingListServiceImp.getAllWaitingLists();
        List<WaitingListDto> waitingListDtos = waitingLists.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(waitingListDtos);
    }

    private WaitingListDto mapToDto(WaitingList waitingList) {
        return new WaitingListDto(waitingList.getId(), waitingList.getDate());
    }
}


