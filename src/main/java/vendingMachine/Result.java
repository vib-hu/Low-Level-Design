package vendingMachine;
import java.util.ArrayList;

public class Result {

    private final ArrayList<Money> changes;
    private final Product product;

    public Result(ArrayList<Money> changes, Product product){
        this.changes = changes;
        this.product = product;
    }

    public ArrayList<Money> getChanges(){
        return changes;
    }

    public Product getProduct(){
        return product;
    }
}
