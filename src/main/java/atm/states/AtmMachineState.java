package atm.states;

import atm.Atm;
import atm.Card;

import java.math.BigDecimal;

public abstract class AtmMachineState {
    public Atm atm;
    public AtmMachineState(Atm atm){
        this.atm = atm;
    }

    // Customer-invoked methods
    public abstract void insertCard(Card card);
    public abstract void enterPin(String pin);
    public abstract void enterAmount(BigDecimal amount) throws Exception;
    public abstract void confirm();
    public abstract void goBack();
    public abstract void cancel();

    // System-invoked methods (but state-dependent)
    public abstract void error();
}
