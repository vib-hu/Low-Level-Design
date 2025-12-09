package atm.services;

import java.math.BigDecimal;

public interface NetworkService {
    boolean deductMoney(String cardNumber, String pin, BigDecimal amount);
}
