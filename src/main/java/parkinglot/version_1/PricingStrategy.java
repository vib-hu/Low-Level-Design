package parkinglot.version_1;

import java.math.BigDecimal;

public interface PricingStrategy {
    BigDecimal getPrice(ParkingTicket ticket);
}
