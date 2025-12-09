package atm.states;

import atm.Atm;
import atm.Card;

import java.math.BigDecimal;

public class AmountEnteredState extends AtmMachineState {
    public AmountEnteredState(Atm atm) {
        super(atm);
    }

    @Override
    public void insertCard(Card card) {
        System.out.println("Card already inserted");
    }

    @Override
    public void enterPin(String pin) {
        System.out.println("Pin already inserted");
    }

    @Override
    public void enterAmount(BigDecimal amount) throws Exception {
        //allow to change amount
        if(amount.compareTo(BigDecimal.valueOf(0))<=0){
            throw new Exception("invalid amount");
        }
        atm.setAmount(amount);
    }

    @Override
    public void confirm() {
       if(atm.deductMoney()){
           atm.dispenseMoney();
           atm.ejectCard();
           atm.setState(atm.getIdleState());
       }else{
           atm.setState(atm.getErrorState());
       }
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
