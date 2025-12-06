package revision_practice;

public class ParkingSpot {
    private int id;
    private Vehicle parkedVehicle;

    public ParkingSpot(int id){
        this.id = id;
    }
    public Vehicle getVehicle(){
          return parkedVehicle;
    }

    public void parkVehicle(Vehicle vehicle) throws Exception{
        if(!isAvailable()){
            throw new Exception("Spot not avilable");
        }
        this.parkedVehicle = vehicle;
    }

    public void unParkVehicle(){
        this.parkedVehicle = null;
    }

    public boolean isAvailable(){
          return parkedVehicle==null;
    }
}
