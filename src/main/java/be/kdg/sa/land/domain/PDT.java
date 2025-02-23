package be.kdg.sa.land.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class PDT {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MaterialType materialType;

    @Column(nullable = false)
    private LocalDateTime deliveryDate; // Date of delivery

    @Column(nullable = false)
    private int warehouseNr;

    @ManyToOne
    private Delivery delivery;

    @OneToOne(optional = false)
    private Truck truck;

    public PDT() {
    }

    public PDT(MaterialType materialType, LocalDateTime now, int warehouseNumber, Truck truck) {
        this.materialType = materialType;
        this.deliveryDate = now;
        this.warehouseNr = warehouseNumber;
        this.truck = truck;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public int getWarehouseNr() {
        return warehouseNr;
    }

    public void setWarehouseNr(int warehouseNr) {
        this.warehouseNr = warehouseNr;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    @Override
    public String toString() {
        return "PDT{" +
                "id=" + id +
                ", materialType=" + materialType +
                ", deliveryDate=" + deliveryDate +
                ", warehouseNr='" + warehouseNr + '\'' +
                ", delivery=" + delivery +
                ", truck=" + truck +
                '}';
    }
}
