package revision_practice;

import java.math.BigDecimal;

public interface PricingStrategy {
    BigDecimal getPrice(ParkingTicket ticket);
}
