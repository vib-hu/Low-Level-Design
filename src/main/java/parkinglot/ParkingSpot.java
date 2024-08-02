package parkinglot;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ParkingSpot {
    private int id;
    private ParkingSpotStatus status;
    private VehicleType spotType;
    private float parkingPerHour;
    private LocalDateTime parkingTime;
    private LocalDateTime releaseTime;
    private Vehicle vehicle;

    public ParkingSpot(int id, VehicleType spotType, float parkingPerHour){
        this.id = id;
        this.status = ParkingSpotStatus.Available;
        this.spotType = spotType;
        this.parkingPerHour = parkingPerHour;
    }

    public void parkVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
    }

    public float calculateParkingPriceTillNow(){
        return calculatePrice(parkingTime, LocalDateTime.now());
    }

    public float calculateParkingPrice(){
       return calculatePrice(parkingTime, releaseTime);
    }

    public boolean isAvailable(){
        return status==ParkingSpotStatus.Available;
    }

    public float releaseSpot(){
        releaseTime = LocalDateTime.now();
        float parkingPrice = calculateParkingPrice();

        status = ParkingSpotStatus.Available;
        vehicle = null;
        parkingTime = null;
        return parkingPrice;
    }

    public void reserveSpot(){
        status = ParkingSpotStatus.Reserved;
    }

    private float calculatePrice(LocalDateTime startDatetime, LocalDateTime endDatetime){
        Duration duration =  Duration.between(startDatetime, endDatetime);
        long minutes = duration.toMinutes();
        if(minutes<60)
            return parkingPerHour;
        long fullHours = (minutes/60);
        long partialHours = (minutes%60);
        return fullHours*parkingPerHour+partialHours>0?parkingPerHour:0;
    }
}
