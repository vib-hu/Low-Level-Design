package atm.states;

import atm.Atm;
import atm.Card;

import java.math.BigDecimal;

public class IdleState extends AtmMachineState {
    public IdleState(Atm atm){
        super(atm);
    }

    @Override
    public void insertCard(Card card) {
        //validation
        atm.setCard(card);
        atm.setState(atm.getCardInsertedState());
    }

    @Override
    public void enterPin(String pin) {
        System.out.println("Please insert card first");
    }

    @Override
    public void enterAmount(BigDecimal amount) throws Exception {
        System.out.println("Please insert card and enter PIN first");
    }

    @Override
    public void confirm() {
        System.out.println("Please insert card first");
    }

    @Override
    public void goBack() {
        System.out.println("Already at initial state");
    }

    @Override
    public void cancel() {
        System.out.println("No transaction in progress");
    }

    @Override
    public void error() {
        System.out.println("No transaction in progress");
    }
}
