package vendingMachine.statepattern;

import vendingMachine.Money;
import vendingMachine.Product;

import java.util.ArrayList;

public class DispensedState extends VendingMachineState {

    private final VendingMachine machine;
    public DispensedState(VendingMachine machine){
        this.machine = machine;
    }

    @Override
    public void selectProduct(Product product) {

    }

    @Override
    public void insertMoney(ArrayList<Money> money) {

    }

    @Override
    public void dispenseProduct() {

    }

    @Override
    public void dispenseChange() {

    }
}
