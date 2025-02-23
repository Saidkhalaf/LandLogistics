package be.kdg.sa.land.services;

import be.kdg.sa.land.controller.dto.WbtDto;
import be.kdg.sa.land.domain.Truck;
import be.kdg.sa.land.domain.WBT;
import be.kdg.sa.land.domain.WeighBridge;
import be.kdg.sa.land.repositories.TruckRepository;
import be.kdg.sa.land.repositories.WbtRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class WeighBridgeAssignmentService {

    private final Logger logger = LoggerFactory.getLogger(WeighBridgeAssignmentService.class);
    private final WeighBridgeService weighBridgeService;
    private final WbtRepository wbtRepository;
    private final TruckRepository truckRepository;
    private final TruckServiceImp truckServiceImp;

    public WeighBridgeAssignmentService(WeighBridgeService weighBridgeService, WbtRepository wbtRepository, TruckRepository truckRepository, TruckServiceImp truckServiceImp) {
        this.weighBridgeService = weighBridgeService;
        this.wbtRepository = wbtRepository;
        this.truckRepository = truckRepository;
        this.truckServiceImp = truckServiceImp;
    }

    public WbtDto assignWeighBridge(String licensePlate, Model model) {
        if (!truckServiceImp.validateLicensePlate(licensePlate)) {
            logger.info("License plate is not valid or truck is not within the appointment window or has to wait.");
            model.addAttribute("error", "License plate is not valid or truck is not within the appointment window or has to wait.");
            return null;
        }

        WBT wbt = wbtRepository.findByTrucklicensePlate(licensePlate);
        if (wbt == null) {
            Optional<Truck> truckOptional = truckRepository.findByLicensePlate(licensePlate);
            if (truckOptional.isPresent()) {
                Truck truck = truckOptional.get();
                WeighBridge selectedWeighBridge = weighBridgeService.getFreeWeighBridge();
                if (selectedWeighBridge != null) {
                    wbt = generateRandomWbtDetails(truck, selectedWeighBridge);
                    wbtRepository.save(wbt);
                } else {
                    logger.info("No free weigh bridges available.");
                    model.addAttribute("error", "No free weigh bridges available.");
                }
            } else {
                model.addAttribute("error", "No free weigh bridges available.");
            }
        }
        assert wbt != null;
        logger.info("Truck with license plate: {} is scanned and there is a valid appointment.", licensePlate);
        model.addAttribute("message", "Welcome, your truck with License plate <strong>"
                + licensePlate + "</strong> is scanned and there is a valid appointment." +
                "<br>Assigned Weigh Bridge nr: <strong>" + wbt.getWeighbridge().getBridgeNumber() + "</strong>" +
                "<br>Assigned Warehouse nr: <strong>" + wbt.getWarehouseNumber() + "</strong>");

        return new WbtDto(
                wbt.getGrossWeight(),
                wbt.getTareWeight(),
                wbt.getNetWeight(),
                wbt.getWeighingMoment(),
                wbt.getTruck().getLicensePlate(),
                wbt.getWeighbridge().getBridgeNumber(),
                wbt.getWeighbridge().getLocation(),
                wbt.getWarehouseNumber()
        );
    }

    private WBT generateRandomWbtDetails(Truck truck, WeighBridge selectedWeighBridge) {
        Random random = new Random();
        double grossWeight = 10000 + (20000 - 10000) * random.nextDouble();
        double tareWeight = 5000 + (10000 - 5000) * random.nextDouble();
        double netWeight = grossWeight - tareWeight;
        LocalDateTime weighingMoment = LocalDateTime.now();
        int warehouseNumber = 1 + random.nextInt(60);

        WBT wbt = new WBT();
        wbt.setGrossWeight(grossWeight);
        wbt.setTareWeight(tareWeight);
        wbt.setNetWeight(netWeight);
        wbt.setWeighingMoment(weighingMoment);
        wbt.setTruck(truck);
        wbt.setWeighbridge(selectedWeighBridge);
        wbt.setWarehouseNumber(warehouseNumber);
        return wbt;
    }

}
