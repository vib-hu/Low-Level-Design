package atm.states;

import atm.Atm;
import atm.Card;

import java.math.BigDecimal;

public class CardInsertedState extends AtmMachineState {
    public CardInsertedState(Atm atm){
        super(atm);
    }
    @Override
    public void insertCard(Card card) {
        System.out.println("Card already inserted");
    }

    @Override
    public void enterPin(String pin) {
        //validation
        atm.setPin(pin);
        atm.setState(atm.getPinEnteredState());
    }

    @Override
    public void enterAmount(BigDecimal amount) throws Exception {
        System.out.println("First enter the Pin");
    }

    @Override
    public void confirm() {
        System.out.println("First enter the Pin");
    }

    @Override
    public void goBack() {
        //Todo: Go to idle state
    }

    @Override
    public void cancel() {
        atm.ejectCard();
        atm.setState(atm.getIdleState());
    }

    @Override
    public void error() {
        System.out.println("First enter the Pin and Amount");
    }
}
