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
       return null; 
       
    }
    
    public static Product lookupProduct(int productId)
    { 
        return null;
    }
    
    public static ObservableList<Part> lookupPart(String partName)
    { 
      return null;  
    }
    
    public static ObservableList<Product> lookupProduct(String productName)
    { 
        return null;
    }
    
    public static void updatePart(int index, Part newPart)
    {
        
    }
        
    public static void updateProduct(int index, Product newProduct)
    {
        
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
