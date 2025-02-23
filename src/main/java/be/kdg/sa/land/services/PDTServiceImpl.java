package be.kdg.sa.land.services;

import be.kdg.sa.land.controller.dto.PDTDto;
import be.kdg.sa.land.domain.MaterialType;
import be.kdg.sa.land.domain.PDT;
import be.kdg.sa.land.domain.Truck;
import be.kdg.sa.land.repositories.PDTRepository;
import be.kdg.sa.land.repositories.TruckRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Service
public class PDTServiceImpl implements PDTService {

    private final Logger logger = Logger.getLogger(PDTServiceImpl.class.getName());
    private final PDTRepository pdtRepository;
    private final TruckRepository truckRepository;

    public PDTServiceImpl(PDTRepository pdtRepository, TruckRepository truckRepository) {
        this.pdtRepository = pdtRepository;
        this.truckRepository = truckRepository;
    }

    @Override
    public PDT generatePDT(Truck truck, int warehouseNumber, MaterialType materialType) {
        PDT pdt = new PDT(materialType, LocalDateTime.now(), warehouseNumber, truck);
        return pdtRepository.save(pdt);
    }

    @Override
    public PDTDto findPDTById(long id) {
        logger.info("Finding PDT by id: " + id);
        PDT pdt = pdtRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PDT not found"));
        return new PDTDto(
                pdt.getMaterialType(),
                pdt.getDeliveryDate(),
                pdt.getWarehouseNr(),
                pdt.getTruck().getLicensePlate());
    }

    @Override
    public PDTDto findPDTByLicensePlate(String licensePlate) {
        logger.info("Finding PDT by license plate: " + licensePlate);
        PDT pdt = pdtRepository.findByTruckLicensePlate(licensePlate);
        if (pdt == null) {
            return null;
        }
        return new PDTDto(
                pdt.getMaterialType(),
                pdt.getDeliveryDate(),
                pdt.getWarehouseNr(),
                pdt.getTruck().getLicensePlate());
    }

    public PDTDto generatePDTByLicensePlate(String licensePlate) {
        logger.info("Generating PDT for truck with license plate: " + licensePlate);
        Truck truck = truckRepository.findByLicensePlate(licensePlate)
                .orElseThrow(() -> new RuntimeException("Truck not found"));

        // Check if a PDT already exists for this truck
        PDT existingPdt = pdtRepository.findByTruckLicensePlate(truck.getLicensePlate());
        if (existingPdt != null) {
            return new PDTDto(
                    existingPdt.getMaterialType(),
                    existingPdt.getDeliveryDate(),
                    existingPdt.getWarehouseNr(),
                    existingPdt.getTruck().getLicensePlate());
        }

        // If no existing PDT, generate a new one
        PDT newPdt = generatePDT(truck, 1, truck.getMaterialType()); // Assuming warehouseNumber is 1
        return new PDTDto(
                newPdt.getMaterialType(),
                newPdt.getDeliveryDate(),
                newPdt.getWarehouseNr(),
                newPdt.getTruck().getLicensePlate());
    }
}
