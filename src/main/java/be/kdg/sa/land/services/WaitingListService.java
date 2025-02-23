package be.kdg.sa.land.services;

import be.kdg.sa.land.domain.MaterialType;
import be.kdg.sa.land.domain.WaitingList;
import org.springframework.stereotype.*;

import java.util.*;


@Service
public interface WaitingListService {

    Optional<WaitingList> findById(long id);
    List<WaitingList> getAllWaitingLists();
    void addTruckWithoutAppointmentToWaitingList(long waitingListId,String licensePlate, String sellerName, MaterialType materialType);
}
