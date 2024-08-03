package parkinglot;

import java.util.ArrayList;

public class Level {
    private final int floor;
    private final ArrayList<ParkingSpot> parkingSpots;

    public Level(int floor){
        this.floor = floor;
        this.parkingSpots= new ArrayList<>();
    }

    public void addSpots(int numOfSpots, VehicleType vehicleType){
        for(int i=1;i<=numOfSpots;i++){
            parkingSpots.add(new ParkingSpot(i, vehicleType));
        }
    }

    public synchronized boolean parkVehicle(Vehicle vehicle){
        for(ParkingSpot spot: parkingSpots){
            if(spot.isAvailableForVehicleType(vehicle.getType())){
                spot.parkVehicle(vehicle);
                return true;
            }
        }
        return false;
    }

    public synchronized boolean unparkVehicle(Vehicle vehicle){
        for(ParkingSpot spot: parkingSpots){
            if(spot.hasParkedVehicle(vehicle)){
                spot.unparkVehicle();
                return true;
            }
        }
        return false;
    }

    public void displayAvailable(){
        System.out.println("Level "+floor+" Availability");
        for(ParkingSpot spot:parkingSpots){
            System.out.println("Spot "+spot.getSpotNumber()+" : "+(spot.isAvailable()?"Available":"Occupied"));
        }
    }
}
