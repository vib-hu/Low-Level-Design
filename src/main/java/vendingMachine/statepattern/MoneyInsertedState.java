package vendingMachine.statepattern;

import java.util.ArrayList;

public class MoneyInsertedState extends VendingMachineState{

    private final VendingMachine machine;

    public MoneyInsertedState(VendingMachine machine){
        this.machine = machine;
    }

    @Override
    public void selectProduct(Product product) {
        System.out.println("Product already selected "+machine.getSelectedProduct().getName());
    }

    @Override
    public void insertMoney(ArrayList<Money> money) {
        System.out.println("Money already inserted");
    }

    @Override
    public void dispenseProduct() {
        Product product = machine.getInventory().removeProduct(machine.getSelectedProduct().getName());
        machine.collectMoney();
        System.out.println("Dispensing product "+product.getName());
        machine.setState(machine.getProductDispensedState());
    }

    @Override
    public void dispenseChange() {
        System.out.println("Dispensing Product first");
    }
}
