package be.kdg.sa.land.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SellerParty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private LocalDate birthDate;

    @OneToMany(mappedBy = "sellerParty", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Truck> trucks = new ArrayList<>();

    @OneToMany(mappedBy = "sellerParty", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<DeliveryAppointment> deliveryAppointments;

    public SellerParty() {
    }

    public SellerParty(String name) {
        this.name = name;
    }

    public SellerParty(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public SellerParty(String name, LocalDateTime birthDate) {
        this.name = name;
        this.birthDate = birthDate.toLocalDate();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Truck> getTrucks() {
        return trucks;
    }

    public void setTrucks(List<Truck> trucks) {
        this.trucks = trucks;
    }

    public void addTruck(Truck truck) {
        if (trucks == null) {
            trucks = new ArrayList<>();
        }
        trucks.add(truck);
    }

    public List<DeliveryAppointment> getDeliveryAppointments() {
        return deliveryAppointments;
    }

    public void setDeliveryAppointments(List<DeliveryAppointment> deliveryAppointments) {
        this.deliveryAppointments = deliveryAppointments;
    }

    @Override
    public String toString() {
        return "SellerParty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate='" + birthDate + '\'' +
                '}';
    }
}
