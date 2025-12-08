package parkinglot.version_1;

public class Truck extends Vehicle {
    public Truck(String plateNumber){
        super(plateNumber);
    }
    @Override
    public int getSpotsRequired(){
        return 4;
    }
}
