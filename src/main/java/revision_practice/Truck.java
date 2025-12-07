package revision_practice;

public class Truck extends Vehicle {
    public Truck(String plateNumber){
        super(plateNumber);
    }
    @Override
    public int getSpotsRequired(){
        return 4;
    }
}
