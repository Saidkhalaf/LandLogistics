package be.kdg.sa.land.repositories;

import be.kdg.sa.land.domain.TruckDriver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

@Repository
public interface TruckDriverRepository extends JpaRepository<TruckDriver, Long> {
}
