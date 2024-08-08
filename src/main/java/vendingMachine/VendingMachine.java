package vendingMachine;

import java.util.ArrayList;
import java.util.Map;

public class VendingMachine {
    private final VendingMachineState idleState;
    private final VendingMachineState productSelectedState;
    private final VendingMachineState moneyInsertedState;
    private final VendingMachineState productDispensedState;
    private VendingMachineState currentState;
    private final Inventory inventory;
    private Product selecteProduct;
    private final ArrayList<Money> collectedMoney;
    private ArrayList<Money> insertedMoney;

    public static VendingMachine instance;

    private VendingMachine(){
        this.idleState = new IdleState(this);
        this.productSelectedState = new ProductSelectedState(this);
        this.moneyInsertedState = new MoneyInsertedState(this);
        this.productDispensedState = new ProductDispensedState(this);
        inventory = new Inventory();
        currentState = idleState;
        selecteProduct = null;
        collectedMoney = new ArrayList<>();
        insertedMoney = new ArrayList<>();
    }

    public static synchronized VendingMachine getInstance(){
        if(instance==null){
            instance = new VendingMachine();
        }
        return instance;
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

    public VendingMachineState getMoneyInsertedState(){
        return moneyInsertedState;
    }
    public VendingMachineState getIdleState(){
        return idleState;
    }

    public VendingMachineState getProductDispensedState(){
        return productDispensedState;
    }

    public Product getSelectedProduct(){
        return selecteProduct;
    }

    public void addMoney(ArrayList<Money> money){
        insertedMoney.addAll(money);
    }

    public float getTotalInsertedMoney(){
        float total =0;
        for(Money money: insertedMoney){
            total += money.getValue();
        }
        return total;
    }

    public void addProducts(ArrayList<Product> products){
        for(Product product: products){
            inventory.addProduct(product);
        }
    }

    public void selectProduct(Product product){
        currentState.selectProduct(product);
    }

    public void insertMoney(ArrayList<Money> money){
        currentState.insertMoney(money);
    }

    public void dispenseProduct(){
        currentState.dispenseProduct();
    }

    public void dispenseChange(){
        currentState.dispenseChange();
    }

    public void resetProductSelection(){
        selecteProduct = null;
    }

    public void displayAvailability(){
        for(Map.Entry<String, Integer> map: inventory.getProductsAvailability().entrySet()){
            System.out.println("Product Name: "+map.getKey()+" Available Quantity: "+map.getValue());
        }
    }

    public void displayCollectedMoney(){
        for(Money money: collectedMoney){
            System.out.println(money.getCurrency()+" "+money.getValue()+" Type: "+money.getType());
        }
    }

    public void collectMoney() {
        collectedMoney.addAll(insertedMoney);
    }

    public void resetInsertedMoney() {
        insertedMoney.clear();
    }
}
