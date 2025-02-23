package be.kdg.sa.land.repositories;

import be.kdg.sa.land.domain.WeighBridge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeighBridgeRepository extends JpaRepository<WeighBridge, Long> {

}
