package revision_practice;

public class Client {

    public static void main(String[] args) throws Exception{
        ParkingSpots spots = new ParkingSpots();
        spots.addSpot(new ParkingSpot(1));
        spots.addSpot(new ParkingSpot(2));
        spots.addSpot(new ParkingSpot(3));
        spots.addSpot(new ParkingSpot(4));
        spots.addSpot(new ParkingSpot(5));

        ParkingLot parkingLot = new ParkingLot(new HourlyPricingStrategy(), spots);
        ParkingTicket ticket = parkingLot.parkVehicle(new Car("IN-1234"));
        System.out.println(ticket.getTicketId());
        System.out.println(ticket.getAssignedSpots());

        ParkingTicket ticket1 = parkingLot.parkVehicle(new Truck("IN-45678"));
        System.out.println(ticket1.getTicketId());
        System.out.println(ticket1.getAssignedSpots());

        parkingLot.unparkVehicle(ticket1.getTicketId());
        parkingLot.unparkVehicle(ticket.getTicketId());
    }
}
