package revision_practice;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class ParkingTicket {
    private final String ticketId;
    private final Vehicle vehicle;
    private final List<ParkingSpot> assignedSpots;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private PaymentStatus paymentStatus;
    private BigDecimal cost;

    public ParkingTicket(Vehicle vehicle, List<ParkingSpot> assignedSpots){
        this.ticketId = generateTicketId();
        this.vehicle = vehicle;
        this.assignedSpots = assignedSpots;
        this.entryTime = LocalDateTime.now();
        paymentStatus = PaymentStatus.UNPAID;
    }

    public void markExit(){
        this.exitTime = LocalDateTime.now();
    }

    public String getTicketId(){
        return ticketId;
    }

    public List<ParkingSpot> getAssignedSpots(){
        return assignedSpots;
    }

    public long getParkingDurationInMinutes(){
        LocalDateTime end = exitTime==null? LocalDateTime.now():exitTime;
        return Duration.between(end, entryTime).toMinutes();
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

    //Math.random()
    //Returns: A double value between 0.0 (inclusive) and 1.0 (exclusive)
    // Example: 0.9999999
    private String generateTicketId(){
        return "T-"+System.currentTimeMillis()+"-"+(int)(Math.random()*1000);
    }
}
