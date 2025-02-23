package be.kdg.sa.land.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class DeliveryAppointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotNull(message = "Scheduled time is required")
    private LocalDateTime scheduledArrivalTime;

    @Column
    private LocalDateTime actualArrivalTime;

    @Column
    private String status;

    @Enumerated(EnumType.STRING)
    @Column
    private MaterialType materialType;

    @Column
    private boolean isLate;

    @Column
    private String weighBridgeIdentifier;

    @NotNull(message = "Truck is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="truck_id")
    private Truck truck;

    @ManyToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @ManyToOne
    @JoinColumn(name= "seller_party_id")
    private SellerParty sellerParty;

    public DeliveryAppointment() {
    }

    public DeliveryAppointment(LocalDateTime scheduledArrivalTime, Truck truck) {
        this.scheduledArrivalTime = scheduledArrivalTime;
        this.truck = truck;
    }

    public DeliveryAppointment(LocalDateTime scheduledArrivalTime, LocalDateTime actualArrivalTime, MaterialType materialType, boolean isLate, String weighBridgeIdentifier, Truck truck, Delivery delivery, SellerParty sellerParty) {
        this.scheduledArrivalTime = scheduledArrivalTime;
        this.actualArrivalTime = actualArrivalTime;
        this.materialType = materialType;
        this.isLate = isLate;
        this.weighBridgeIdentifier = weighBridgeIdentifier;
        this.truck = truck;
        this.delivery = delivery;
        this.sellerParty = sellerParty;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getScheduledArrivalTime() {
        return scheduledArrivalTime;
    }

    public void setScheduledArrivalTime(LocalDateTime scheduledArrivalTime) {
        this.scheduledArrivalTime = scheduledArrivalTime;
    }

    public LocalDateTime getActualArrivalTime() {
        return actualArrivalTime;
    }

    public void setActualArrivalTime(LocalDateTime actualArrivalTime) {
        this.actualArrivalTime = actualArrivalTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isLate() {
        return isLate;
    }

    public void setLate(boolean late) {
        isLate = late;
    }

    public String getWeighBridgeIdentifier() {
        return weighBridgeIdentifier;
    }

    public void setWeighBridgeIdentifier(String weighBridgeIdentifier) {
        this.weighBridgeIdentifier = weighBridgeIdentifier;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public SellerParty getSellerParty() {
        return sellerParty;
    }

    public void setSellerParty(SellerParty sellerParty) {
        this.sellerParty = sellerParty;
    }

    @Override
    public String toString() {
        return "DeliveryAppointment{" +
                "id=" + id +
                ", scheduledArrivalTime=" + scheduledArrivalTime +
                ", actualArrivalTime=" + actualArrivalTime +
                ", materialType=" + materialType +
                ", isLate=" + isLate +
                ", weighBridgeIdentifier='" + weighBridgeIdentifier + '\'' +
                ", truck=" + truck +
                ", delivery=" + delivery +
                ", sellerParty=" + sellerParty +
                '}';
    }
}
