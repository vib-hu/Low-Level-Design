package revision_practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingSpots {
    private List<ParkingSpot> spots = new ArrayList<>();

    public void addSpot(ParkingSpot spot){
        spots.add(spot);
    }

    public synchronized List<ParkingSpot> parkVehicle(Vehicle vehicle) throws Exception{
        int requiredSpots = vehicle.getSpotsRequired();
        Optional<List<ParkingSpot>> availableSpots = getConsecutiveSpots(requiredSpots);
        if(!availableSpots.isPresent()){
            throw new Exception("Spots not found");
        }
        for(ParkingSpot spot: availableSpots.get()){
            System.out.println("Parking vehicle "+vehicle.getPlateNumber() +" at spot:"+spot.getId());
            spot.parkVehicle(vehicle);
        }
        return availableSpots.get();
    }

    public synchronized void releaseSpots(List<ParkingSpot> spotsToRelease){
        for (ParkingSpot spot : spotsToRelease) {
            spot.unParkVehicle();
        }
    }

    private Optional<List<ParkingSpot>> getConsecutiveSpots(int requiredSpots){
        for(int counter=0;counter<=spots.size()-requiredSpots;counter++){
          List<ParkingSpot> subSpots = spots.subList(counter, counter+requiredSpots);
          if(subSpots.stream().allMatch(p->p.isAvailable())){
                return Optional.of(subSpots);
          }
        }
        return Optional.empty();
    }
}
