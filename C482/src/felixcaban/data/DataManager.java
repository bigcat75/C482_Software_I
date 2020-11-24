package felixcaban.data;

import felixcaban.models.InHouse;
import felixcaban.models.Inventory;
import felixcaban.models.Outsourced;
import felixcaban.models.Part;
import felixcaban.models.Product;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * Responsible for providing the initial data when the application launches. 
 * Provides additional static members for testing and maintaining data
 * integrity throughout the application life cycle.
 * 
 * @author felix.caban
 * @version 1.0
 * @since 1.0
 * 
 */
public final class DataManager 
{
    
    //================================================================================
    // Properties
    //================================================================================
    
    /**
     * 
     * The next available {@link felixcaban.models.Part#id PartId}.
     * 
     */
    private static int partId;
    
    /**
     * 
     * The next available {@link felixcaban.models.Product#id ProductId}.
     * 
     */
    private static int productId;
    
    /**
     * 
     * The {@link felixcaban.models.Part Part} being modified.
     * 
     */
    private static Part partToModify;
    
     /**
     * 
     * The index of the {@link felixcaban.models.Part Part} being modified.
     * 
     */
    private static int partIndexToModify;
    
    /**
     * 
     * The {@link felixcaban.models.Product Product} being modified.
     * 
     */
    private static Product productToModify;
    
     /**
     * 
     * The index of the {@link felixcaban.models.Product Product} being modified.
     * 
     */
    private static int productIndexToModify;
    
    
    //================================================================================
    // Methods
    //================================================================================
    
    /**
     *
     * Instantiates multiple {@link felixcaban.models.Part Part} and {@link felixcaban.models.Product Product}
     * objects upon program start for testing the application. 
     * Also sets the seeds for auto-generating the {@link felixcaban.models.Part#id PartId} 
     * and {@link felixcaban.models.Product#id ProductId}.
     * 
     */
    public static void LoadDataOnStart()
    {
        
        var ih1 = new InHouse(1, "Case", 79.00, 7, 5, 10, 59);
        var ih2 = new InHouse(2, "Power Supply", 119.99, 8, 10, 30, 68);
        var ih3 = new InHouse(3, "Fan", 19.99, 32, 25, 50, 86);
        var ih4 = new InHouse(4, "Keyboard", 19.00, 12, 7, 17, 58);
        var ih5 = new InHouse(5, "Mouse", 14.09, 17, 7, 17, 57);
        var os1 = new Outsourced(20, "CPU", 499.99, 9, 5, 10,"Intel");
        var os2 = new Outsourced(21, "RAM", 249.49, 13, 10, 20, "Patriot");
        var os3 = new Outsourced(22, "Hard Drive", 299.99, 22, 10, 30, "Samsung");
        var os4 = new Outsourced(23, "Monitor", 159.45, 9, 7, 17, "AOC");
        var os5 = new Outsourced(24, "Graphics Card", 799.98, 7, 5, 10, "Nvidia");
        var os6 = new Outsourced(25, "Motherboard", 349.00, 8, 5, 10, "Gigabyte");
        
        Inventory.addPart(ih1);
        Inventory.addPart(ih2);
        Inventory.addPart(ih3);
        Inventory.addPart(ih4);
        Inventory.addPart(ih5);
        Inventory.addPart(os1);
        Inventory.addPart(os2);
        Inventory.addPart(os3);
        Inventory.addPart(os4);
        Inventory.addPart(os5);
        Inventory.addPart(os6);

        var prod1 = new Product(1, "Gaming PC", 2279.00, 7, 5, 10);
        var prod2 = new Product(2, "Office PC", 1119.99, 8, 10, 30);
        var prod3 = new Product(3, "A/V PC", 1649.99, 32, 25, 50);
        
        prod1.addAssociatedPart(os6);
        prod1.addAssociatedPart(ih1);
        prod1.addAssociatedPart(os2);
        prod1.addAssociatedPart(os2);
        
        Inventory.addProduct(prod1);
        Inventory.addProduct(prod2);
        Inventory.addProduct(prod3);

        List<Integer> partIdList = new ArrayList<>();

        Inventory.getAllParts().forEach((p) -> {
            partIdList.add(p.getId());
         });
        
        partId = Collections.max(partIdList) + 1;
        
        List<Integer> productIdList = new ArrayList<>();

        Inventory.getAllProducts().forEach((p) -> 
        {
            productIdList.add(p.getId());
        });

        productId = Collections.max(productIdList) + 1;
         
    }

    /**
     *
     * Increments the {@link #partId partId} for the next {@link felixcaban.models.Part Part} that is added.  
     * Is triggered once the {@link felixcaban.controllers.AddPartFormController AddPartForm}
     * saves a new {@link felixcaban.models.Part Part}.
     * 
     */
    public static void incrementPartId()
    {
        
        partId++;
        
    }

    /**
     *
     * Increments the {@link #productId productId} for the next {@link felixcaban.models.Product Product} that is added.  
     * Is triggered once the {@link felixcaban.controllers.AddProductFormController AddProductForm}
     * saves a new {@link felixcaban.models.Product Product}.
     * 
     */
    public static void incrementProductId()
    {
        
        productId++;
        
    }

    /**
     *
     * Gets the next auto-generated {@link felixcaban.models.Part#id PartId}. 
     * Provides it to the {@link felixcaban.controllers.AddPartFormController AddPartForm}
     * when a new {@link felixcaban.models.Part Part} is created.
     * 
     * @return the auto-generated {@link felixcaban.models.Part#id PartId} for
     * the next {@link felixcaban.models.Part Part} object instantiated.
     * 
     */
    public static int getNextPartId()
    {
        
        return partId;
        
    }

     /**
     *
     * Gets the next auto-generated {@link felixcaban.models.Product#id ProductId}. 
     * Provides it to the {@link felixcaban.controllers.AddProductFormController AddProductForm}
     * when a new {@link felixcaban.models.Product Product} is created.
     * 
     * @return the auto-generated {@link felixcaban.models.Product#id ProductId} for
     * the next {@link felixcaban.models.Product Product} object instantiated.
     * 
     */
    public static int getNextProductId()
    {
        
        return productId;
        
    }
    
    /**
     *
     * Sets the {@link felixcaban.models.Part Part} that is currently being modified. 
     * It will be used to populate the {@link felixcaban.controllers.ModifyPartFormController ModifyPartForm}.
     * 
     * @param index the index of the {@link felixcaban.models.Part Part} that is currently being modified.
     * @param part the {@link felixcaban.models.Part Part} that is currently being modified.
     * 
     */
    public static void setPartToModify(int index, Part part)
    {
        
        partToModify = part;
        partIndexToModify = index;
        
    }
    
     /**
     *
     * Sets the {@link felixcaban.models.Product Product} that is currently being modified. 
     * It will be used to populate the {@link felixcaban.controllers.ModifyProductFormController ModifyProductForm}.
     * 
     * @param index the index of the {@link felixcaban.models.Part Part} that is currently being modified.
     * @param product the {@link felixcaban.models.Part Part} that is currently being modified.
     * 
     */
    public static void setProductToModify(int index, Product product)
    {
        
        productToModify = product;
        productIndexToModify = index;
        
    }
    
    /**
     *
     * Gets the {@link felixcaban.models.Part Part} that is currently being modified. 
     * Used to populate the {@link felixcaban.controllers.ModifyPartFormController ModifyPartForm}.
     * 
     * @return the {@link felixcaban.models.Part Part} that is currently being modified.
     * 
     */
    public static Part getPartToModify()
    {
        
        return partToModify;
        
    }
    
    /**
     *
     * Gets the index of the {@link felixcaban.models.Part Part} that is currently being modified. 
     * Used to populate the {@link felixcaban.controllers.ModifyPartFormController ModifyPartForm}.
     * 
     * @return the index of the {@link felixcaban.models.Part Part} that is currently being modified.
     * 
     */
    public static int getPartIndexToModify()
    {
        
        return partIndexToModify;
        
    }
    
    /**
     *
     * Gets the {@link felixcaban.models.Product Product} that is currently being modified. 
     * Used to populate the {@link felixcaban.controllers.ModifyProductFormController ModifyProductForm}.
     * 
     * @return the {@link felixcaban.models.Part Part} that is currently being modified.
     * 
     */
    public static Product getProductToModify()
    {
        
        return productToModify;
        
    }
    
    /**
     *
     * Gets the index of the {@link felixcaban.models.Product Product} that is currently being modified. 
     * Used to populate the {@link felixcaban.controllers.ModifyProductFormController ModifyProductForm}.
     * 
     * @return the index of the {@link felixcaban.models.Part Part} that is currently being modified.
     * 
     */
    public static int getProductIndexToModify()
    {
        
        return productIndexToModify;
        
    }
    
    /**
     *
     * Tests if a string is an integer. 
     * Returns true if string is an integer, false if it is not.
     * 
     * @param input string to test.
     * @return true if string is an integer, false if it is not.
     */
    public static boolean isInteger(String input)
    {
        
        try
        {
            Integer.parseInt(input); 
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
        
    }
    
    /**
     *
     * Tests if a string is an double. 
     * Returns true if string is a double, false if it is not.
     * 
     * @param input string to test.
     * @return true if string is a double, false if it is not.
     * 
     */
    public static boolean isDouble(String input)
    {
        
        try
        {
            Double.parseDouble(input); 
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
        
    }
    
    /**
     *
     * Tests if a {@link felixcaban.models.Part Part} exists in inventory. 
     * Returns true if i is a valid {@link felixcaban.models.Part#id PartId}, false if it is not.
     * 
     * @param i the integer to test to determine if it is a valid {@link felixcaban.models.Part#id PartId}.
     * @return true if integer is a valid {@link felixcaban.models.Part#id PartId}, false if it is not.
     * 
     */
    public static boolean partExists(int i)
    {
        boolean test = false;
        
        for (Part p : Inventory.getAllParts())
        {
            if (p.getId() == i)
            {
                test = true;
                break;
            }
        }
        
        return test;
    }
    
     /**
     *
     * Tests if a {@link felixcaban.models.Product Product} exists in inventory. 
     * Returns true if i is a valid {@link felixcaban.models.Product#id ProductId}, false if it is not.
     * 
     * @param i the integer to test to determine if it is a valid {@link felixcaban.models.Product#id ProductId}.
     * @return true if integer is a valid {@link felixcaban.models.Product#id ProductId}, false if it is not.
     * 
     */
    public static boolean productExists(int i)
    {
        boolean test = false;
        
        for (Product p : Inventory.getAllProducts())
        {
            if (p.getId() == i)
            {
                test = true;
                break;
            }
        }
        
        return test;
    }
    
}
