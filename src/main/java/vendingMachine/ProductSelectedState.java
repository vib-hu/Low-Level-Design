package vendingMachine;

import java.util.ArrayList;

public class ProductSelectedState extends VendingMachineState {

    private VendingMachine machine;
    public ProductSelectedState(VendingMachine machine){
        this.machine = machine;
    }
    @Override
    public void selectProduct(Product product) {
        System.out.println("Product already selected: "+machine.getSelectedProduct().getName());
    }

    @Override
    public void insertMoney(ArrayList<Money> money) {
        float totalMoney = 0;
        for(Money item:money){
            totalMoney += item.getValue();
        }
        if(totalMoney<machine.getSelectedProduct().getPrice()){
            throw new IllegalArgumentException("Money is not enough");
        }

        machine.addMoney(money);
        machine.setState(machine.getMoneyInsertedState());
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Insert money first");
    }

    @Override
    public void dispenseChange() {
        System.out.println("Insert money first");
    }
}
