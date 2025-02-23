package be.kdg.sa.land.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "weigh_bridges")
public class WeighBridge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String location;

    private boolean isFree;

    private int bridgeNumber;

    @OneToMany(mappedBy = "weighbridge", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<WBT> initialWeightings;

    public WeighBridge() {
    }

    public WeighBridge(long id, String location, boolean isFree, int bridgeNumber, List<WBT> initialWeightings) {
        this.id = id;
        this.location = location;
        this.isFree = isFree;
        this.bridgeNumber = bridgeNumber;
        this.initialWeightings = initialWeightings;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<WBT> getInitialWeightings() {
        return initialWeightings;
    }

    public void setInitialWeightings(List<WBT> initialWeightings) {
        this.initialWeightings = initialWeightings;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public int getBridgeNumber() {
        return bridgeNumber;
    }

    public void setBridgeNumber(int bridgeNumber) {
        this.bridgeNumber = bridgeNumber;
    }

    @Override
    public String toString() {
        return "WeighBridge{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", initialWeighings=" + initialWeightings +
                '}';
    }
}
