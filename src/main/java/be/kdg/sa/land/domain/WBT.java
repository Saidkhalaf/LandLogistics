package be.kdg.sa.land.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class WBT {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private double grossWeight;
    @Column
    private double tareWeight;
    @Column
    private double netWeight;

    @Column
    private LocalDateTime weighingMoment;

    @ManyToOne
    @JoinColumn(name = "truck_id", nullable = false)
    private Truck truck;

    @ManyToOne(optional = false)
    @JoinColumn(name = "weighbridge_id", nullable = false)
    private WeighBridge weighbridge;

    @Column
    private int warehouseNumber;


    public WBT() {
    }

    public WBT(double grossWeight, double tareWeight, double netWeight, LocalDateTime weighingMoment, Truck truck, WeighBridge weighbridge) {
        this.grossWeight = grossWeight;
        this.tareWeight = tareWeight;
        this.netWeight = netWeight;
        this.weighingMoment = weighingMoment;
        this.truck = truck;
        this.weighbridge = weighbridge;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(double grossWeight) {
        this.grossWeight = grossWeight;
    }

    public double getTareWeight() {
        return tareWeight;
    }

    public void setTareWeight(double tareWeight) {
        this.tareWeight = tareWeight;
    }

    public double getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(double netWeight) {
        this.netWeight = netWeight;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public WeighBridge getWeighbridge() {
        return weighbridge;
    }

    public void setWeighbridge(WeighBridge weighbridge) {
        this.weighbridge = weighbridge;
    }

    public LocalDateTime getWeighingMoment() {
        return weighingMoment;
    }

    public void setWeighingMoment(LocalDateTime weighingMoment) {
        this.weighingMoment = weighingMoment;
    }

    public int getWarehouseNumber() {return warehouseNumber;}

    public void setWarehouseNumber(int warehouseNumber) {this.warehouseNumber = warehouseNumber;}

    @Override
    public String toString() {
        return "InitialWeighing{" +
                "id=" + id +
                ", grossWeight=" + grossWeight +
                ", tareWeight=" + tareWeight +
                ", netWeight=" + netWeight +
                ", truck=" + truck +
                ", weighbridge=" + weighbridge +
                '}';
    }
}
