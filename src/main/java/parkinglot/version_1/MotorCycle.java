package parkinglot.version_1;

public class MotorCycle extends Vehicle {
    public MotorCycle(String plateNumber){
        super(plateNumber);
    }
    @Override
    public int getSpotsRequired(){
        return 1;
    }
}
