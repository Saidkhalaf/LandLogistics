package be.kdg.sa.land.controller.api;

import be.kdg.sa.land.controller.dto.DeliveryAppointmentDto;
import be.kdg.sa.land.controller.dto.TruckDto;
import be.kdg.sa.land.domain.DeliveryAppointment;
import be.kdg.sa.land.domain.SellerParty;
import be.kdg.sa.land.domain.Truck;
import be.kdg.sa.land.services.SellerPartyService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class SellerPartyRestController {

    Logger logger = Logger.getLogger(SellerPartyRestController.class.getName());
    private final SellerPartyService sellerPartyService;

    public SellerPartyRestController(SellerPartyService sellerPartyService) {
        this.sellerPartyService = sellerPartyService;
    }

    @GetMapping("/sellerParties/{id}")
    public ResponseEntity<SellerParty> getSellerPartyById(@PathVariable long id) {
        logger.info("Getting SellerParty with ID: " + id);
        SellerParty sellerParty = sellerPartyService.getSellerPartyById(id);
        return ResponseEntity.ok(sellerParty);
    }

    @PostMapping("/sellerParties/{id}/addTruck")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TruckDto> addTruck(@PathVariable long id, @RequestBody TruckDto newTruckDto) {
        try {
            Truck createdTruck = sellerPartyService.addTruckToSellerParty(id, newTruckDto.toEntity());
            return ResponseEntity.ok(new TruckDto(
                    createdTruck.getLicensePlate(),
                    createdTruck.getCapacity(),
                    createdTruck.getMaxLoad(),
                    createdTruck.getMaterialType()
            ));
        } catch (DataIntegrityViolationException e) {
            logger.severe("Error adding truck: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @PostMapping("/sellerParties/{id}/makeAppointment")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DeliveryAppointmentDto> makeAppointment(@PathVariable long id, @RequestBody DeliveryAppointmentDto newAppointmentDto) {
    DeliveryAppointment createdAppointment = sellerPartyService.addAppointment(id, newAppointmentDto);
    logger.info("Created appointment with ID: " + createdAppointment.getId());
    return ResponseEntity.ok(new DeliveryAppointmentDto(
            createdAppointment.getActualArrivalTime(),
            createdAppointment.getTruck().getId()
    ));
    }
}
