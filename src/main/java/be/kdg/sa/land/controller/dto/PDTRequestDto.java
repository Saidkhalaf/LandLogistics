package be.kdg.sa.land.controller.dto;

import be.kdg.sa.land.domain.MaterialType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record PDTRequestDto(
                            MaterialType materialType,
                            @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime deliveryDate,
                            int warehouseNumber,
                            String licensePlate) {
}
