package vendingMachine;

public class Product {
    private final String code;
    private final String name;
    private final float price;

    public Product(String code, String name, float  price){
            this.code = code;
            this.name = name;
            this.price = price;
    }

    public String getCode(){
        return code;
    }

    public float getPrice(){
        return price;
    }

}
