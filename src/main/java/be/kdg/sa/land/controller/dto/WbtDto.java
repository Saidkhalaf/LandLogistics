package be.kdg.sa.land.controller.dto;

import java.time.LocalDateTime;

public record WbtDto(double grossWeight,
                     double tareWeight,
                     double netWeight,
                     LocalDateTime weighingMoment,
                     String trucklicensePlate,
                     int WeighBridgeLocation,
                     String weighBridgeLocation,
                     int warehouseNumber) {

}
