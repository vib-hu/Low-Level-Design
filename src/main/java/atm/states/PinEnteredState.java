package atm.states;

import atm.Atm;
import atm.Card;

import java.math.BigDecimal;

public class PinEnteredState extends AtmMachineState{
    public PinEnteredState(Atm atm){
        super(atm);
    }
    @Override
    public void insertCard(Card card) {
        System.out.println("Card already inserted");
    }

    @Override
    public void enterPin(String pin) {
        System.out.println("Pin already entered");
    }

    @Override
    public void enterAmount(BigDecimal amount) throws Exception {
        //validate amount
        atm.setAmount(amount);
        atm.setState(atm.getAmountEnteredState());
    }

    @Override
    public void confirm() {
        System.out.println("Enter Amount first");
    }

    @Override
    public void goBack() {

    }

    @Override
    public void cancel() {
        atm.ejectCard();
        atm.setState(atm.getIdleState());
    }

    @Override
    public void error() {
        System.out.println("No transaction");
    }
}
