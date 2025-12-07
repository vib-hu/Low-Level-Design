package revision_practice;

public class Car extends Vehicle {
    public Car(String plateNumber){
        super(plateNumber);
    }
    @Override
    public int getSpotsRequired(){
        return 1;
    }
}
