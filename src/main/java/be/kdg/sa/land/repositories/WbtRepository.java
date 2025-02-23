package be.kdg.sa.land.repositories;

import be.kdg.sa.land.domain.WBT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WbtRepository extends JpaRepository<WBT, Long> {
    @Query("SELECT w FROM WBT w WHERE w.truck.licensePlate = :licensePlate")
    WBT findByTrucklicensePlate(@Param("licensePlate") String trucklicensePlate);
}
