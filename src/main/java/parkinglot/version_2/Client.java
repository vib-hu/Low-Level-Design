package parkinglot.version_2;

public class Client {

    public static void main(String[] args){
        ParkingLot parkingLot = ParkingLot.getInstance();
        Level level1 = new Level(1);
        level1.addSpots(10,VehicleType.Car);
        level1.addSpots(10,VehicleType.Motorcycle);
        level1.addSpots(10,VehicleType.Truck);
        parkingLot.addLevel(level1);

        Level level2 = new Level(2);
        level2.addSpots(5,VehicleType.Car);
        level2.addSpots(5,VehicleType.Motorcycle);
        level2.addSpots(5,VehicleType.Truck);
        parkingLot.addLevel(level2);

        Vehicle car = new Car("UP32-1234");
        parkingLot.parkVehicle(car);

        Vehicle truck = new Truck("UP32-4321");
        parkingLot.parkVehicle(truck);

        Vehicle motorcycle = new Motorcycle("UP32-8765");
        parkingLot.parkVehicle(motorcycle);

        parkingLot.displayAvailability();

        parkingLot.unparkVehicle(car);

        parkingLot.displayAvailability();
    }
}
