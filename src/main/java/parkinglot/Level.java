package parkinglot;

import java.util.ArrayList;

public class Level {
    private int id;
    private ArrayList<ParkingSpot> parkingSpots;

    public Level(int id){
        this.id = id;
        this.parkingSpots= new ArrayList<>();
    }

    public void addParkingSpot(ParkingSpot parkingSpot){
        parkingSpots.add(parkingSpot);
    }
}
