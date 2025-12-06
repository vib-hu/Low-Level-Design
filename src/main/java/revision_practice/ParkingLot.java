package revision_practice;

public class ParkingLot {
    private ParkingSpots spots = new ParkingSpots();

    public void parkVehicle(Vehicle vehicle) throws Exception{
        spots.parkVehicle(vehicle);
    }

    public void unparkVehicle(Vehicle vehicle) throws Exception{
        spots.parkVehicle(vehicle);
    }
}
