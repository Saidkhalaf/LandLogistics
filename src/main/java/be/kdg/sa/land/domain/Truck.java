package be.kdg.sa.land.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;


@Entity
@Table(name = "trucks")
public class Truck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "License plate is mandatory")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,8}$", message = "License plate must be alphanumeric and 6-8 characters long")
    @Column(nullable = false, unique = true)
    private String licensePlate;

    @Min(value = 0, message = "Capacity must be positive")
    @Column
    private double capacity;

    @Min(value = 0, message = "Max load must be positive")
    @Column
    private double maxLoad;

    @Min(value = 0, message = "Current load must be positive")
    @Column
    private double currentLoad;

    @ManyToOne
    @JoinColumn(name = "seller_party_id") // seller_party_id is the foreign key column
    private SellerParty sellerParty;

    @ManyToOne
    @JoinColumn(name = "waiting_list_id") // waiting_list_id is the foreign key column
    private WaitingList waitingList;

    @ManyToOne
//    @JoinColumn(name = "truck_driver_id", nullable = true) // truck_driver_id is the foreign key column
    private TruckDriver truckDriver;

    @Enumerated(EnumType.STRING)
    private MaterialType materialType;

    @OneToMany(mappedBy = "truck", cascade = CascadeType.ALL)
    private List<WBT> wbts;

    @OneToMany(mappedBy = "truck", cascade = CascadeType.ALL)
    private List<DeliveryAppointment> deliveryAppointments;

    @Transient
    private long sellerPartyId;

    public Truck() {
    }

    public Truck(long id, String licensePlate, double capacity, double maxLoad, double currentLoad, SellerParty sellerParty, WaitingList waitingList, TruckDriver truckDriver, MaterialType materialType, List<WBT> wbts, List<DeliveryAppointment> deliveryAppointments, long sellerPartyId) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.capacity = capacity;
        this.maxLoad = maxLoad;
        this.currentLoad = currentLoad;
        this.sellerParty = sellerParty;
        this.waitingList = waitingList;
        this.truckDriver = truckDriver;
        this.materialType = materialType;
        this.wbts = wbts;
        this.deliveryAppointments = deliveryAppointments;
        this.sellerPartyId = sellerPartyId;
    }

    public Truck(String licensePlate, WaitingList waitingList, MaterialType materialType, SellerParty sellerParty) {
        this.licensePlate = licensePlate;
        this.waitingList = waitingList;
        this.materialType = materialType;
        this.sellerParty = sellerParty;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getMaxLoad() {
        return maxLoad;
    }

    public void setMaxLoad(double maxLoad) {
        this.maxLoad = maxLoad;
    }

    public double getCurrentLoad() {
        return currentLoad;
    }

    public void setCurrentLoad(double currentLoad) {
        this.currentLoad = currentLoad;
    }

    public SellerParty getSellerParty() {
        return sellerParty;
    }

    public void setSellerParty(SellerParty sellerParty) {
        this.sellerParty = sellerParty;
    }

    public WaitingList getWaitingList() {
        return waitingList;
    }

    public void setWaitingList(WaitingList waitingList) {
        this.waitingList = waitingList;
    }

    public TruckDriver getTruckDriver() {
        return truckDriver;
    }

    public void setTruckDriver(TruckDriver truckDriver) {
        this.truckDriver = truckDriver;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public List<WBT> getWbts() {
        return wbts;
    }

    public void setWbts(List<WBT> wbts) {
        this.wbts = wbts;
    }

    public long getSellerPartyId() {
        return sellerPartyId;
    }

    public void setSellerPartyId(long sellerPartyId) {
        this.sellerPartyId = sellerPartyId;
    }

    public List<DeliveryAppointment> getDeliveryAppointments() {
        return deliveryAppointments;
    }

    public void setDeliveryAppointments(List<DeliveryAppointment> deliveryAppointments) {
        this.deliveryAppointments = deliveryAppointments;
    }

    @Override
    public String toString() {
        return "Truck{" +
                "id=" + id +
                ", licensePlate='" + licensePlate + '\'' +
                ", capacity=" + capacity +
                ", maxLoad=" + maxLoad +
                ", currentLoad=" + currentLoad +
                '}';
    }
}
