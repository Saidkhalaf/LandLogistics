package be.kdg.sa.land.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "deliveries")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private LocalDateTime deliveryDate;

    @Column
    @Enumerated(EnumType.STRING)
    private MaterialType materialType;

    @Column
    private String warehouseNr;

    @Column
    private double weight;

    @Column
    private String status;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<DeliveryAppointment> deliveryAppointments;

    public Delivery() {
    }

    public Delivery(LocalDateTime deliveryDate, MaterialType materialType, String warehouseNr, double weight, String status, List<DeliveryAppointment> deliveryAppointments) {
        this.deliveryDate = deliveryDate;
        this.materialType = materialType;
        this.warehouseNr = warehouseNr;
        this.weight = weight;
        this.status = status;
        this.deliveryAppointments = deliveryAppointments;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public String getWarehouseNr() {
        return warehouseNr;
    }

    public void setWarehouseNr(String warehouseNr) {
        this.warehouseNr = warehouseNr;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DeliveryAppointment> getDeliveryAppointments() {
        return deliveryAppointments;
    }

    public void setDeliveryAppointments(List<DeliveryAppointment> deliveryAppointments) {
        this.deliveryAppointments = deliveryAppointments;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", deliveryDate=" + deliveryDate +
                ", materialType=" + materialType +
                ", warehouseNr='" + warehouseNr + '\'' +
                ", weight=" + weight +
                ", status='" + status + '\'' +
                '}';
    }
}
