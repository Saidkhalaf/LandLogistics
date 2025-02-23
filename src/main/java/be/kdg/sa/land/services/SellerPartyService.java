package be.kdg.sa.land.services;

import be.kdg.sa.land.controller.dto.DeliveryAppointmentDto;
import be.kdg.sa.land.domain.DeliveryAppointment;
import be.kdg.sa.land.domain.SellerParty;
import be.kdg.sa.land.domain.Truck;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SellerPartyService {
    List<SellerParty> getSellerParties();
    Truck addTruckToSellerParty(long sellerPartyId, Truck newTruck);
    SellerParty getSellerPartyById(long id);
    DeliveryAppointment addAppointment(long sellerPartyId, DeliveryAppointmentDto newAppointmentDto);
    List<Truck> getTrucksBySellerPartyId(long sellerPartyId);
    DeliveryAppointment getAppointmentById(long id);
}
