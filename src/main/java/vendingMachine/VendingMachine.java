package vendingMachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VendingMachine {
    private final HashMap<String, ArrayList<Product>> products;
    private ArrayList<Money> collectedMoney;
    private ArrayList<Money> insertedMoney;
    private Product selectedProduct;

    public VendingMachine(){
        this.products = new HashMap<>();
        collectedMoney = new ArrayList<>();
        insertedMoney = null;
    }

    public void addProduct(Product product){
     if(products.containsKey(product.getCode())){
         products.get(product.getCode()).add(product);
     }else{
         ArrayList<Product> productList = new ArrayList<>();
         productList.add(product);
         products.put(product.getCode(), productList);
     }
    }

    public ArrayList<Money> collectMoney(){
        ArrayList<Money> tempMoney = collectedMoney;
        collectedMoney = new ArrayList<>();
        return tempMoney;
    }

    public boolean selectProduct(Product product){
        if(!products.containsKey(product.getCode()))
            return false;

       selectedProduct = products.get(product.getCode()).get(0);
        return true;
    }

    public boolean insertMoney(ArrayList<Money> money){
        if(selectedProduct==null)
            throw new IllegalArgumentException("Please select product first");

        float productPrice = selectedProduct.getPrice();
        float insertedMoney = (float) money.stream().mapToDouble(Money::getValue).sum();
        if(insertedMoney<productPrice)
            throw new IllegalArgumentException("money is not enough");

        if(checkIfChangeAvailable(insertedMoney-productPrice))
        {
            this.insertedMoney = money;
            return true;
        }
        return false;
    }

    public Result dispense(){
        chargeMoney();
        Product product = dispenseProduct();
        ArrayList<Money> changes = dispenseChange();
        return new Result(changes, product);
    }

    public void displayAvailableProducts(){
        for(Map.Entry<String, ArrayList<Product>> product:products.entrySet()){
            System.out.println("Product code"+product.getKey()+" Count:"+product.getValue().size());
        }
    }

    private void chargeMoney(){
        collectedMoney.addAll(insertedMoney);
        insertedMoney = null;
    }

    private Product dispenseProduct(){
        ArrayList<Product> productsPerCode = products.get(selectedProduct.getCode());
        selectedProduct = null;
        return productsPerCode.remove(0);
    }

    private ArrayList<Money> dispenseChange(){
        return new ArrayList<Money>();
    }

    private boolean checkIfChangeAvailable(float change){
        if(change==0)
            return true;
        return false;
    }
}
