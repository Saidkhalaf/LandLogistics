package be.kdg.sa.land.repositories;

import be.kdg.sa.land.domain.SellerParty;
import be.kdg.sa.land.domain.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SellerPartyRepository extends JpaRepository<SellerParty, Long> {
    Optional<SellerParty> findByName(String sellerName);
    @Query("SELECT t FROM Truck t WHERE t.sellerParty.id = :sellerPartyId")
    List<Truck> findTrucksBySellerPartyId(Long sellerPartyId);
}
