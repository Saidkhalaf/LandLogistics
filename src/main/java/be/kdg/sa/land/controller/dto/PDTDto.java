package be.kdg.sa.land.controller.dto;

import be.kdg.sa.land.domain.MaterialType;

import java.time.LocalDateTime;

public record PDTDto(
        MaterialType materialType,
        LocalDateTime deliveryDate,
        int warehouseNumber,
        String licensePlate
) {
}
