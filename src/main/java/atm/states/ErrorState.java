package atm.states;

import atm.Atm;
import atm.Card;

import java.math.BigDecimal;

public class ErrorState extends AtmMachineState {
    public ErrorState(Atm atm){
        super(atm);
    }
    @Override
    public void insertCard(Card card) {
        System.out.println("Not Applicable");
    }

    @Override
    public void enterPin(String pin) {
        System.out.println("Not Applicable");
    }

    @Override
    public void enterAmount(BigDecimal amount) throws Exception {
        System.out.println("Not Applicable");
    }

    @Override
    public void confirm() {
        System.out.println("Not Applicable");
    }

    @Override
    public void goBack() {
        System.out.println("Not Applicable");
    }

    @Override
    public void cancel() {
        System.out.println("Not Applicable");
    }

    @Override
    public void error() {
        System.out.println("Error in deducting amount");
    }
}
