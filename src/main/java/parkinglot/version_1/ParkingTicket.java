package parkinglot.version_1;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class ParkingTicket {
    private final String ticketId;
    private final Vehicle vehicle;
    private final List<ParkingSpot> assignedSpots;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private PaymentStatus paymentStatus;
    private BigDecimal cost;
    private Clock clock;

    //One shared copy: All ParkingTicket objects ever created will share the exact same
    // idCounter variable because it's static and belongs to class, not Object
    private static final AtomicLong idCounter = new AtomicLong(1);

    public ParkingTicket(Vehicle vehicle, List<ParkingSpot> assignedSpots, Clock clock){
        this.ticketId = generateTicketId();
        this.vehicle = vehicle;
        this.assignedSpots = assignedSpots;
        this.entryTime = LocalDateTime.now(clock);
        paymentStatus = PaymentStatus.UNPAID;
        this.clock = clock;
    }

    public void markExit(){
        this.exitTime = LocalDateTime.now(clock);
    }

    public String getTicketId(){
        return ticketId;
    }

    public List<ParkingSpot> getAssignedSpots(){
        return assignedSpots;
    }

    public long getParkingDurationInMinutes(){
        LocalDateTime end = exitTime==null? LocalDateTime.now(clock):exitTime;
        return Duration.between(entryTime, end).toMinutes();
    }

    public Vehicle getVehicle(){
        return vehicle;
    }

    public long getParkingDurationInHours(){
        long minutes = getParkingDurationInMinutes();
        if(minutes==0)
            return 1;

        // Round up to next hour
        return (long)Math.ceil(minutes/60.0);
    }

    public void updateCost(BigDecimal cost){
        this.cost = cost;
    }

    private String generateTicketId(){
        return "T-"+idCounter.getAndIncrement();
    }
}
