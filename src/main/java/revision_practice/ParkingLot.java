package revision_practice;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {
    private ParkingSpots spots;
    private Map<String, ParkingTicket> issuedTickets = new ConcurrentHashMap<>();
    private final PricingStrategy pricingStrategy;
    private final Object lock = new Object();

    public ParkingLot(PricingStrategy pricingStrategy, ParkingSpots spots){
        this.pricingStrategy = pricingStrategy;
        this.spots = spots;
    }

    public  ParkingTicket parkVehicle(Vehicle vehicle) throws Exception{
        List<ParkingSpot> parkedSpots;
        synchronized (lock){
             parkedSpots = spots.parkVehicle(vehicle);
        }
        ParkingTicket ticket = new ParkingTicket(vehicle, parkedSpots);
        issuedTickets.put(ticket.getTicketId(), ticket);
        return ticket;
    }

    public void unparkVehicle(String ticketId) throws Exception{
        ParkingTicket ticket = issuedTickets.get(ticketId);
        if (ticket == null) {
            throw new Exception("Ticket not found");
        }

        ticket.markExit();
        BigDecimal price = pricingStrategy.getPrice(ticket);
        //customer pays
        ticket.updateCost(price);
        System.out.println("Payable Amount by customer: "+price);
        synchronized (lock){
            for(ParkingSpot spot: ticket.getAssignedSpots()){
                spot.unParkVehicle();
            }
            issuedTickets.remove(ticketId);
        }
    }
}
