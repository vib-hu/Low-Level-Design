package vendingMachine;

import java.util.ArrayList;

public class IdleState extends VendingMachineState {
    private final VendingMachine machine;
    public IdleState(VendingMachine machine){
        this.machine = machine;
    }

    @Override
    public void selectProduct(Product product) {
       if(machine.getInventory().checkAvailability(product)){
           machine.setSelectedProduct(product);
           machine.setState(machine.getProductSelectedState());
           System.out.println("Product selected "+product.getName());
       }else{
            System.out.println("Product not available "+product.getName());
       }
    }

    @Override
    public void insertMoney(ArrayList<Money> money) {
        System.out.println("Please select a product first");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Please select a product first");
    }

    @Override
    public void dispenseChange() {
        System.out.println("Please select a product and make payment");
    }
}
