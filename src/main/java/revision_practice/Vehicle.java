package revision_practice;

public abstract class Vehicle {
    private final String plateNumber;
    public Vehicle(String plateNumber){
        this.plateNumber = plateNumber;
    }
    public String getPlateNumber(){
        return plateNumber;
    }
    public abstract int getSpotsRequired();

}
