package parkinglot;

import java.util.ArrayList;
import java.util.HashMap;

public class ParkingLot {
    private final ArrayList<Level> levels;
    private static ParkingLot instance;
    private ParkingLot(){
        this.levels = new ArrayList<>();
    }

    public static synchronized ParkingLot getInstance(){
        if(instance==null){
            instance = new ParkingLot();
        }
            return instance;
    }

    public void addLevel(Level level){
        this.levels.add(level);
    }

    public boolean parkVehicle(Vehicle vehicle){
        for(Level level:levels){
            if(level.parkVehicle(vehicle))
                return true;
        }
        return false;
    }

    public boolean unparkVehicle(Vehicle vehicle){
        for(Level level:levels){
            if(level.unparkVehicle(vehicle))
                return true;
        }
        return false;
    }

    public void displayAvailability(){
        for(Level level:levels){
          level.displayAvailable();
        }
    }
}
