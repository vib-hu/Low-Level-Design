package vendingMachine;

import java.util.ArrayList;

public class ProductDispensedState extends VendingMachineState {

    private final VendingMachine machine;
    public ProductDispensedState(VendingMachine machine){
        this.machine = machine;
    }

    @Override
    public void selectProduct(Product product) {
        System.out.println("Please collect change first");
    }

    @Override
    public void insertMoney(ArrayList<Money> money) {
        System.out.println("Please collect change first");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Please collect change first");
    }

    @Override
    public void dispenseChange() {
        float change = machine.getTotalInsertedMoney()-machine.getSelectedProduct().getPrice();
        if(change==0){
            System.out.println("No change remaining");
            return;
        }
        System.out.println("Change return $"+change);
        machine.resetProductSelection();
        machine.resetInsertedMoney();
        machine.setState(machine.getIdleState());
    }
}
