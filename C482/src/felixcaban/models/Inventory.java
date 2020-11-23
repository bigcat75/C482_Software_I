package felixcaban.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public final class Inventory 
{
    
    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();    

    public static void addPart(Part newPart)
    {
        
        allParts.add(newPart);
        
    }
    
    public static void addProduct(Product newProduct)
    {
        
        allProducts.add(newProduct);
        
    }
    
    public static Part lookupPart(int partId)
    { 
        
       int index = 0;
        
       for (Part pa : allParts)
       {
           if (pa.getId() == partId)
           {
               index = allParts.indexOf(pa);
           }
       }
       
       return allParts.get(index);
       
    }
    
    public static Product lookupProduct(int productId)
    { 
        
       int index = 0;
        
       for (Product pr : allProducts)
       {
           if (pr.getId() == productId)
           {
               index = allProducts.indexOf(pr);
           }
       }
       
       return allProducts.get(index);
        
    }
    
    public static ObservableList<Part> lookupPart(String partName)
    { 
        
        ObservableList<Part> results = FXCollections.observableArrayList();
        
        for (Part pa : allParts)
        {
            if ( pa.getName().startsWith(partName))
            {
                results.add(pa);
            }
        }
        
        return results; 
      
    }
    
    public static ObservableList<Product> lookupProduct(String productName)
    { 
        
        ObservableList<Product> results = FXCollections.observableArrayList();
        
        for (Product pr : allProducts)
        {
            if ( pr.getName().startsWith(productName))
            {
                results.add(pr);
            }
        }
        
        return results; 
        
    }
    
    public static void updatePart(int index, Part newPart)
    {
        
        allParts.set(index, newPart);
        
    }
        
    public static void updateProduct(int index, Product newProduct)
    {
        
        allProducts.set(index, newProduct);
        
    }
    
    public static boolean deletePart(Part selectedPart)
    {
        
       allParts.remove(selectedPart);    
        
       return true;
        
    }
        
    public static boolean deleteProduct(Product selectedProduct)
    {
        
        allProducts.remove(selectedProduct);
        
        return true;
     
    }
    
    public static ObservableList<Part> getAllParts()
    {
        
        return allParts;
        
    }
    
    public static ObservableList<Product> getAllProducts()
    {
        
        return allProducts;
        
    }
    
}
