package be.kdg.sa.land.repositories;

import be.kdg.sa.land.domain.SellerParty;
import be.kdg.sa.land.domain.WaitingList;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
@Rollback
class WaitingListRepositoryTest {

    @Autowired
    private WaitingListRepository waitingListRepository;
    @Autowired
    private TruckRepository truckRepository;

//    @BeforeEach
//    void setUp() {
//        waitingListRepository.deleteAll(); // Clear all entries in the WaitingList table
//    }

    @Test
    void canAddNewWaitingList() {
        WaitingList waitingList = new WaitingList(LocalDateTime.of(2024, 10, 07, 12, 30, 00), new SellerParty("SteelCo Ltd.")); // the seller party should be already exist in the database
        WaitingList savedWaitingList = this.waitingListRepository.save(waitingList);

        assertThat(savedWaitingList.getId()).isNotNull();
    }

    @Test
    void canFetchWaitingList(){
        Optional<WaitingList> optionalWaitingList = this.waitingListRepository.findById(4);
        assertThat(optionalWaitingList).isPresent();
    }

    @Test
    void canFetchWaitingListWithTrucks(){
        Optional<WaitingList> optionalWaitingList = this.waitingListRepository.findByIdWithTrucks(4);
        assertThat(optionalWaitingList).isPresent();
        assertThat(optionalWaitingList.get().getTrucks()).isNotNull();
    }

    @Test
    void canNotFetchNonExistenceWaitingList(){
        Optional<WaitingList> optionalWaitingList = this.waitingListRepository.findById(999);
        assertThat(optionalWaitingList).isNotPresent();
    }
}