package be.kdg.sa.land.controller.api;

import be.kdg.sa.land.controller.dto.PDTDto;
import be.kdg.sa.land.controller.dto.PDTRequestDto;
import be.kdg.sa.land.domain.PDT;
import be.kdg.sa.land.domain.Truck;
import be.kdg.sa.land.services.PDTServiceImpl;
import be.kdg.sa.land.services.TruckServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PDTRestController {

    private static final Logger logger = LoggerFactory.getLogger(PDTRestController.class);
    private final PDTServiceImpl pdtServiceImpl;
    private final TruckServiceImp truckServiceImp;

    public PDTRestController(PDTServiceImpl pdtServiceImpl, TruckServiceImp truckServiceImp) {
        this.pdtServiceImpl = pdtServiceImpl;
        this.truckServiceImp = truckServiceImp;
    }

    @PostMapping("/pdt/generate")
    public ResponseEntity<PDTDto> generatePDT(@RequestBody PDTRequestDto pdtRequestDto) {
        logger.info("Received request to generate PDT: {}", pdtRequestDto);
        Truck truck = truckServiceImp.findTruckByLicensePlate(pdtRequestDto.licensePlate());
        if (truck == null) {
            logger.error("Truck with license plate {} not found", pdtRequestDto.licensePlate());
            return ResponseEntity.badRequest().body(null);
        }
        PDT pdt = pdtServiceImpl.generatePDT(truck, pdtRequestDto.warehouseNumber(), pdtRequestDto.materialType());
        PDTDto pdtDto = new PDTDto(
                pdt.getMaterialType(),
                pdt.getDeliveryDate(),
                pdt.getWarehouseNr(),
                pdt.getTruck().getLicensePlate());
        logger.info("Successfully generated PDT: {}", pdtDto);
        return ResponseEntity.ok(pdtDto);
    }
}
