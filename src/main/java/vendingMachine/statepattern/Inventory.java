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

   public boolean checkAvailability(Product product){
      return products.containsKey(product.getName()) && !products.get(product.getName()).isEmpty();
   }

   public Product removeProduct(String name){
      if(!products.containsKey(name))
            throw new IllegalArgumentException("invalid product name");
      return products.get(name).remove(0);
   }

   public HashMap<String, Integer> getProductsAvailability(){
      HashMap<String, Integer> productsAvailability = new HashMap<>();
      for(Map.Entry<String, ArrayList<Product>> entry:products.entrySet()){
         productsAvailability.put(entry.getKey(), entry.getValue().size());
      }
      return productsAvailability;
   }
}
