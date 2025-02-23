package be.kdg.sa.land.repositories;

import be.kdg.sa.land.domain.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

import java.util.Optional;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Long> {
    Truck findTruckByLicensePlate(String licensePlate);
    Optional<Truck> findByLicensePlate(String licensePlate);
}
