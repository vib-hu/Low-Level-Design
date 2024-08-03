package parkinglot;

public abstract class Vehicle {

    private final VehicleType vehicleType;
    private final String licensePlate;

    public Vehicle(VehicleType vehicleType, String licensePlate){
        this.vehicleType = vehicleType;
        this.licensePlate = licensePlate;
    }

    public VehicleType getType(){
        return vehicleType;
    }

    public String getLicensePlate(){
        return licensePlate;
    }
}
