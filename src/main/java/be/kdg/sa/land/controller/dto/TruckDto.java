package be.kdg.sa.land.controller.dto;

import be.kdg.sa.land.domain.MaterialType;
import be.kdg.sa.land.domain.Truck;

public record TruckDto(String licensePlate, double capacity, double maxLoad, MaterialType materialType) {

    public TruckDto {
    }

    public Truck toEntity(){
        Truck truck = new Truck();
        truck.setLicensePlate(this.licensePlate);
        truck.setCapacity(this.capacity);
        truck.setMaxLoad(this.maxLoad);
        truck.setMaterialType(this.materialType);
        return truck;
    }
}
