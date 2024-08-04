package vendingMachine.statepattern;

public class Product {
    private final String name;
    private final Money price;

    public Product(String name, Money price){
        this.name = name;
        this.price = price;
    }

    public String getName(){
         return name;
    }

    public Money getPrice(){
        return price;
    }
}
