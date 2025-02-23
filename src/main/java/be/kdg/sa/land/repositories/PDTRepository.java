package be.kdg.sa.land.repositories;

import be.kdg.sa.land.domain.PDT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PDTRepository extends JpaRepository<PDT, Long> {
    PDT findByTruckLicensePlate(String licensePlate);
}
