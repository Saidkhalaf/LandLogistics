package be.kdg.sa.land.services;

import be.kdg.sa.land.domain.Truck;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TruckService {

    List<Truck> getTrucks();
    boolean validateLicensePlate(String licensePlate);
    Truck findTruckByLicensePlate(String licensePlate);
}
