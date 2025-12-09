package atm.services;

import java.math.BigDecimal;

public class VisaService implements NetworkService {
    @Override
    public boolean deductMoney(String cardNumber, String pin, BigDecimal amount) {
        return false;
    }
}
