package revision_practice;

import java.math.BigDecimal;

public class HourlyPricingStrategy implements PricingStrategy {
    private BigDecimal perHourPrice = BigDecimal.valueOf(5);

    @Override
    public BigDecimal getPrice(ParkingTicket ticket) {
        BigDecimal totalSpots = BigDecimal.valueOf(ticket.getVehicle().getSpotsRequired());
        BigDecimal totalHours = BigDecimal.valueOf(ticket.getParkingDurationInHours());
        return perHourPrice.multiply(totalSpots).multiply(totalHours);
    }
}
