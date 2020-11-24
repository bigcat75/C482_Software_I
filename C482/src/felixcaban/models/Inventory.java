package felixcaban.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * Static class representing the current inventory. 
 * Also includes all members to process {@link felixcaban.models.Part Parts} 
 * and {@link felixcaban.models.Product Products}.
 * 
 * @author felix.caban
 * @version 1.0
 * @since 1.0
 * 
 */
public final class Inventory 
{
    
    //================================================================================
    // Properties
    //================================================================================
    
    /**
     * 
     * List of all {@link felixcaban.models.Part Parts} currently in inventory.
     * 
     */
    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    
    /**
     * 
     * List of all {@link felixcaban.models.Product Products} currently in inventory.
     * 
     */
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();  
    
    
    //================================================================================
    // Getters
    //================================================================================
    
    /**
     *
     * Gets a list of all {@link felixcaban.models.Part Parts} in inventory.
     * 
     * @return a list of all {@link felixcaban.models.Part Parts} in inventory.
     * 
     */
    public static ObservableList<Part> getAllParts()
    {
        
        return allParts;
        
    }
    
     /**
     *
     * Gets a list of all {@link felixcaban.models.Product Products} in inventory.
     * 
     * @return a list of all {@link felixcaban.models.Product Products} in inventory.
     * 
     */
    public static ObservableList<Product> getAllProducts()
    {
        
        return allProducts;
        
    }
    
    
    //================================================================================
    // Methods
    //================================================================================

    /**
     *
     * Adds a new {@link felixcaban.models.Part Part} to inventory.
     * 
     * @param newPart the new {@link felixcaban.models.Part Part} getting added to inventory.
     * 
     */
    public static void addPart(Part newPart)
    {
        
        allParts.add(newPart);
        
    }
    
    /**
     *
     * Adds a new {@link felixcaban.models.Product Product} to inventory.
     * 
     * @param newProduct the new {@link felixcaban.models.Product Product} getting added to inventory.
     * 
     */
    public static void addProduct(Product newProduct)
    {
        
        allProducts.add(newProduct);
        
    }
    
    /**
     *
     * Looks up and returns the specified {@link felixcaban.models.Part Part} by {@link felixcaban.models.Part#id PartId}.
     * 
     * @param partId the {@link felixcaban.models.Part#id PartId} of the {@link felixcaban.models.Part Part} to return.
     * @return all of the information for the specified {@link felixcaban.models.Part Part}.
     * 
     */
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
    
    /**
     *
     * Looks up and returns the specified {@link felixcaban.models.Product Product} by {@link felixcaban.models.Product#id ProductId}.
     * 
     * @param productId the {@link felixcaban.models.Product#id ProductId} of the {@link felixcaban.models.Product Product} to return.
     * @return all of the information for the specified {@link felixcaban.models.Product Product}.
     * 
     */
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
    
    /**
     *
     * Looks up and returns a list of {@link felixcaban.models.Part Parts} by {@link felixcaban.models.Part#name PartName}.
     * 
     * @param partName the {@link felixcaban.models.Part#name PartName} of the {@link felixcaban.models.Part Part} to return.
     * @return all of the information for the specified {@link felixcaban.models.Part Part}.
     * 
     */
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
    
    /**
     *
     * Looks up and returns a list of {@link felixcaban.models.Product Products} by {@link felixcaban.models.Product#name ProductName}.
     * 
     * @param productName the {@link felixcaban.models.Product#name ProductName} of the {@link felixcaban.models.Product Product} to return.
     * @return all of the information for the specified {@link felixcaban.models.Product Product}.
     * 
     */
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
    
    /**
     *
     * Updates an existing {@link felixcaban.models.Part Part} in inventory.
     * 
     * @param index the index of the existing {@link felixcaban.models.Part Part} to update.
     * @param newPart the new properties to update the existing {@link felixcaban.models.Part Part}. 
     * 
     */
    public static void updatePart(int index, Part newPart)
    {
        
        allParts.set(index, newPart);
        
    }
        
    /**
     *
     * Updates an existing {@link felixcaban.models.Product Product} in inventory.
     * 
     * @param index the index of the existing {@link felixcaban.models.Product Product} to update.
     * @param newProduct the new properties to update the existing {@link felixcaban.models.Product Product}. 
     * 
     */
    public static void updateProduct(int index, Product newProduct)
    {
        
        allProducts.set(index, newProduct);
        
    }
    
    /**
     *
     * Deletes a {@link felixcaban.models.Part Part} from inventory and returns true or false if the delete was performed.
     * 
     * @param selectedPart the {@link felixcaban.models.Part Part} to delete from inventory.
     * @return true if the {@link felixcaban.models.Part Part} was in inventory and was deleted, false if not.
     * 
     */
    public static boolean deletePart(Part selectedPart)
    {
        
       allParts.remove(selectedPart);    
        
       return true;
        
    }
        
     /**
     *
     * Deletes a {@link felixcaban.models.Product Product} from inventory and returns true or false if the delete was performed.
     * 
     * @param selectedProduct the {@link felixcaban.models.Product Product} to delete from inventory.
     * @return true if the {@link felixcaban.models.Product Product} was in inventory and was deleted, false if not.
     * 
     */
    public static boolean deleteProduct(Product selectedProduct)
    {
        
        allProducts.remove(selectedProduct);
        
        return true;
     
    }
    
}
