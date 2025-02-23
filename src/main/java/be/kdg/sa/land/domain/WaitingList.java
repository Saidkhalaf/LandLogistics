package be.kdg.sa.land.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "waiting_lists")
public class WaitingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime date;

    @OneToMany(mappedBy = "waitingList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Truck> trucks ;

    @ManyToOne
    @JoinColumn(name = "seller_party_id")
    private SellerParty sellerParty;

    public WaitingList() {
    }

    public WaitingList(LocalDateTime date, SellerParty sellerParty) {
        this.date = date;
        this.sellerParty = sellerParty;
        this.trucks = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Truck> getTrucks() {
        return trucks;
    }

    public void setTrucks(List<Truck> trucks) {
        this.trucks = trucks;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public SellerParty getSellerParty() {
        return sellerParty;
    }

    public void setSellerParty(SellerParty sellerParty) {
        this.sellerParty = sellerParty;
    }

    @Override
    public String toString() {
        return "WaitingList{" +
                "id=" + id +
                ", trucks.html=" + trucks +
                '}';
    }
}
