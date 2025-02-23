package be.kdg.sa.land.repositories;

import be.kdg.sa.land.domain.WaitingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.*;

import java.util.Optional;

@Repository
public interface WaitingListRepository extends JpaRepository<WaitingList, Long> {
    Optional<WaitingList> findById(long id);

    @Query(value = """
            SELECT w 
            FROM WaitingList w
            LEFT JOIN FETCH w.trucks
            WHERE w.id = :id
""")
    Optional<WaitingList> findByIdWithTrucks(long id);
}
