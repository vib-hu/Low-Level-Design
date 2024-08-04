package vendingMachine;

public class Money {
    private final float value;
    private final String currency;
    private final MoneyType moneyType;

    public Money(float value, String currency, MoneyType moneyType){
          this.value = value;
          this.currency = currency;
          this.moneyType = moneyType;
    }

    public float getValue(){
        return value;
    }

    public MoneyType getType(){
        return moneyType;
    }
}