package be.kdg.sa.land.services;

import be.kdg.sa.land.domain.WeighBridge;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WeighBridgeService {
    List<WeighBridge> getAllWeighBridges();
    WeighBridge getFreeWeighBridge();
}
