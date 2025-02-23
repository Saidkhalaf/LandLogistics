package be.kdg.sa.land.services;

import be.kdg.sa.land.domain.WeighBridge;
import be.kdg.sa.land.repositories.WeighBridgeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class WeighBridgeServiceImpl implements WeighBridgeService {

    private final WeighBridgeRepository weighBridgeRepository;

    public WeighBridgeServiceImpl(WeighBridgeRepository weighBridgeRepository) {
        this.weighBridgeRepository = weighBridgeRepository;
    }

    @Override
    public List<WeighBridge> getAllWeighBridges() {
        return weighBridgeRepository.findAll();
    }

    @Override
    public WeighBridge getFreeWeighBridge() {
        List<WeighBridge> weighBridges = getAllWeighBridges()
                .stream()
                .filter(WeighBridge::isFree)
                .toList();
        if(weighBridges.isEmpty()) {
            return null;
        }
        Random random = new Random();
        return weighBridges.get(random.nextInt(weighBridges.size()));
    }
}
