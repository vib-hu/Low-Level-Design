package vendingMachine;

import java.util.ArrayList;

public class Client {

    public static void main(String[] args){
        Product biscuit = new Product("Food-1","Biscuit", 11);
        Product anotherBiscuit = new Product("Food-1","Biscuit", 11);
        Product thirdBiscuit = new Product("Food-1","Biscuit", 11);

        Product maggie = new Product("Food-2","Maggie", 20);

        VendingMachine machine = new VendingMachine();
        machine.addProduct(biscuit);
        machine.addProduct(anotherBiscuit);
        machine.addProduct(thirdBiscuit);
        machine.addProduct(maggie);

        machine.displayAvailableProducts();

        machine.selectProduct(biscuit);

        ArrayList<Money> money = new ArrayList<>();
        money.add(createMoney(5, MoneyType.Coin));
        money.add(createMoney(5, MoneyType.Coin));
        money.add(createMoney(1, MoneyType.Coin));
        machine.insertMoney(money);

        printResult(machine.dispense());

       machine.selectProduct(biscuit);

       money.clear();
       money.add(createMoney(5, MoneyType.Coin));
       money.add(createMoney(5, MoneyType.Coin));
       money.add(createMoney(1, MoneyType.Coin));

       machine.insertMoney(money);
       printResult(machine.dispense());

        machine.displayAvailableProducts();
       collectMoney(machine);
    }

    private static Money createMoney(float value, MoneyType moneyType){
        return new Money(value, "AED", moneyType);
    }

    private static void printResult(Result result){
        System.out.println(result.getProduct().getCode());
        System.out.println(result.getChanges());
    }

    private static void collectMoney(VendingMachine machine){
        for (Money money: machine.collectMoney() ){
            System.out.println(money.getValue() +" "+ money.getType());
        }
    }
}
