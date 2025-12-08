package parkinglot.version_2;

public class ParkingSpot {

    private final int spotNumber;
    private final VehicleType vehicleType;
    private Vehicle parkedVehicle;

    public ParkingSpot(int spotNumber, VehicleType spotType){
        this.spotNumber = spotNumber;
        this.vehicleType = spotType;
    }

    public synchronized void parkVehicle(Vehicle vehicle){
        if(isAvailable() && vehicle.getType().equals(vehicleType))
            parkedVehicle = vehicle;
        else
            throw new IllegalArgumentException("Invalid vehicle type or spot already occupied");
    }

    public boolean isAvailable(){
        return parkedVehicle == null;
    }

    public boolean isAvailableForVehicleType(VehicleType vehicleType){
        return isAvailable() && this.vehicleType.equals(vehicleType);
    }

   public void unparkVehicle(){
       parkedVehicle = null;
   }

   public VehicleType getVehicleType(){
        return vehicleType;
   }

   public Vehicle getParkedVehicle(){
        return parkedVehicle;
   }

    public boolean hasParkedVehicle(Vehicle vehicle){
        return parkedVehicle!=null && parkedVehicle.getLicensePlate().equals(vehicle.getLicensePlate());
    }

   public int getSpotNumber(){
        return spotNumber;
   }
}
