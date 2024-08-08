package vendingMachine;

import java.util.ArrayList;

public class Client {

    public static void main(String[] args){
        ArrayList<Product> oreos = productBuilder("Oreo", 12, 3);
        ArrayList<Product> peanuts = productBuilder("Peanuts", 3, 4);

        VendingMachine machine = VendingMachine.getInstance();
        machine.addProducts(oreos);
        machine.addProducts(peanuts);

        //1nd time
        machine.selectProduct(oreos.get(0));
        ArrayList<Money> money = new ArrayList<>();
        money.add(new Money(10, "USD", MoneyType.Note));
        money.add(new Money(5, "USD", MoneyType.Coin));
        machine.insertMoney(money);
        machine.dispenseProduct();
        machine.dispenseChange();
        machine.displayAvailability();
        machine.displayCollectedMoney();

        //2nd time
        machine.selectProduct(peanuts.get(0));
        machine.insertMoney(money);
        machine.dispenseProduct();
        machine.dispenseChange();
        machine.displayAvailability();
        machine.displayCollectedMoney();
    }

    private static ArrayList<Product> productBuilder(String name, float price, int quantity){
        ArrayList<Product> products = new ArrayList<>();
        for(int i=0;i<quantity;i++){
            products.add(new Product(name, price));
        }
        return products;
    }
}
