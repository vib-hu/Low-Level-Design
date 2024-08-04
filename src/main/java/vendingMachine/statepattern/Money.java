package vendingMachine.statepattern;

public class Money {
    private final float value;
    private final MoneyType type;
    private final String currency;

    public Money(float value, String currency, MoneyType type){
        this.value = value;
        this.currency = currency;
        this.type = type;
    }

    public float getValue(){
        return value;
    }

    public String getCurrency(){
        return currency;
    }

    public MoneyType getType(){
        return type;
    }
}
