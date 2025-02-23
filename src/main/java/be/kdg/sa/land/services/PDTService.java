package be.kdg.sa.land.services;

import be.kdg.sa.land.controller.dto.PDTDto;
import be.kdg.sa.land.domain.MaterialType;
import be.kdg.sa.land.domain.PDT;
import be.kdg.sa.land.domain.Truck;
import org.springframework.stereotype.Service;

@Service
public interface PDTService {
    PDT generatePDT(Truck truck, int warehouseNumber, MaterialType materialType);
    PDTDto findPDTById(long id);

    PDTDto findPDTByLicensePlate(String licensePlate);
}
