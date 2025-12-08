package parkinglot.version_1;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.time.Clock;

public class ParkingLot {
    private ParkingSpots spots;
    private Map<String, ParkingTicket> issuedTickets = new ConcurrentHashMap<>();
    private final PricingStrategy pricingStrategy;
    private final Clock clock;

    public ParkingLot(PricingStrategy pricingStrategy, ParkingSpots spots, Clock clock){
        this.pricingStrategy = pricingStrategy;
        this.spots = spots;
        this.clock = clock;
    }

    public  ParkingTicket parkVehicle(Vehicle vehicle) throws Exception{
        List<ParkingSpot> parkedSpots;
        parkedSpots = spots.parkVehicle(vehicle);
        ParkingTicket ticket = new ParkingTicket(vehicle, parkedSpots, clock);
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
        spots.releaseSpots(ticket.getAssignedSpots());
        issuedTickets.remove(ticketId);
    }
}
