package vendingMachine.statepattern;

import java.util.ArrayList;

public class VendingMachine {
    private final VendingMachineState idleState;
    private final VendingMachineState productSelectedState;
    private final VendingMachineState dispensedState;
    private VendingMachineState currentState;
    private final Inventory inventory;
    private Product selecteProduct;
    private ArrayList<Money> collectedMoney;

    public VendingMachine(){
        this.idleState = new IdleState(this);
        this.productSelectedState = new ProductSelectedState(this);
        this.dispensedState = new DispensedState(this);
        inventory = new Inventory();
        currentState = idleState;
        selecteProduct = null;
        collectedMoney = new ArrayList<>();
    }

    public void setSelectedProduct(Product product){
        selecteProduct = product;
    }

    public Inventory getInventory(){
        return inventory;
    }

    public void setState(VendingMachineState state){
        this.currentState = state;
    }

    public VendingMachineState getProductSelectedState(){
        return productSelectedState;
    }

    public Product getSelectedProduct(){
        return selecteProduct;
    }

    public void addMoney(ArrayList<Money> money){
        collectedMoney.addAll(money);
    }

}
