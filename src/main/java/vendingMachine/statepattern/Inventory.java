package vendingMachine.statepattern;

import java.util.ArrayList;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Inventory {

   private final Map<String, ArrayList<Product>> products;

   public Inventory(){
      products = new ConcurrentHashMap<>();
   }

   public void addProduct(Product product){
      if(products.containsKey(product.getName())){
         products.get(product.getName()).add(product);
      }else{
         ArrayList<Product> newProducts = new ArrayList<>();
         newProducts.add(product);
         products.put(product.getName(), newProducts);
      }
   }

   public boolean isProductAvailable(Product product){
      return products.containsKey(product.getName()) && !products.get(product.getName()).isEmpty();
   }
}
