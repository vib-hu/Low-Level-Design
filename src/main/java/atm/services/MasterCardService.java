package atm.services;

import java.math.BigDecimal;

public class MasterCardService implements NetworkService {

    @Override
    public boolean deductMoney(String cardNumber, String pin, BigDecimal amount) {
        return true;
    }
}
