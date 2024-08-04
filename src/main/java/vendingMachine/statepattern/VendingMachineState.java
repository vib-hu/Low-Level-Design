package vendingMachine.statepattern;

import java.util.*;
public abstract class VendingMachineState {

    public abstract void selectProduct(Product product);
    public abstract void insertMoney(ArrayList<Money> money);
    public abstract void dispenseProduct();
    public abstract void dispenseChange();
}
