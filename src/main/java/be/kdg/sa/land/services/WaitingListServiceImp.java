package be.kdg.sa.land.services;

import be.kdg.sa.land.domain.*;
import be.kdg.sa.land.repositories.SellerPartyRepository;
import be.kdg.sa.land.repositories.TruckRepository;
import be.kdg.sa.land.repositories.WaitingListRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;

@Service
@Transactional
public class WaitingListServiceImp implements WaitingListService {

    Logger logger = Logger.getLogger(WaitingListServiceImp.class.getName());
    private final WaitingListRepository waitingListRepository;
    private final TruckRepository truckRepository;
    private final SellerPartyRepository sellerPartyRepository;

    public WaitingListServiceImp(WaitingListRepository waitingListRepository, TruckRepository truckRepository, SellerPartyRepository sellerPartyRepository) {
        this.waitingListRepository = waitingListRepository;
        this.truckRepository = truckRepository;
        this.sellerPartyRepository = sellerPartyRepository;
    }

    @Override
    public void addTruckWithoutAppointmentToWaitingList(long waitingListId,String licensePlate, String sellerName, MaterialType materialType) {
        logger.info("Adding truck to waiting list with ID: " + waitingListId);
        WaitingList waitingList = waitingListRepository.findById(waitingListId)
                .orElseThrow(() -> new IllegalArgumentException("Waiting list not found"));

        SellerParty sellerParty = sellerPartyRepository.findByName(sellerName)
                .orElseGet(() -> {
                    SellerParty newSellerParty = new SellerParty();
                    newSellerParty.setName(sellerName);
//                    newSellerParty.setBirthDate("1980-01-01"); // Default birthdate
                    return sellerPartyRepository.save(newSellerParty);
                });

        Truck truck = new Truck();
        truck.setLicensePlate(licensePlate);
        truck.setSellerParty(sellerParty);
        truck.setMaterialType(materialType);
        truck.setWaitingList(waitingList);
        truck.setTruckDriver(null);
        truckRepository.save(truck);
    }


    public List<WaitingList> getAllWaitingLists() {
        return waitingListRepository.findAll();
    }


    @Transactional
    public Optional<WaitingList> findById(long id) {
        return waitingListRepository.findById(id);
    }
}
