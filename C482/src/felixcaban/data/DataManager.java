package felixcaban.data;

import felixcaban.models.InHouse;
import felixcaban.models.Inventory;
import felixcaban.models.Outsourced;
import felixcaban.models.Product;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author felix.caban
 */
public final class DataManager {
    
    private static int partId;
    private static int productId;
    
    private static int selectedPartId;
    private static int selectedProductId;
    
    public static void LoadData()
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
        prod1.addAssociatedPart(os2);
        prod1.addAssociatedPart(os2);
        
        Inventory.addProduct(prod1);
        Inventory.addProduct(prod2);
        Inventory.addProduct(prod3);

        List<Integer> partIdList = new ArrayList<>();

        Inventory.getAllParts().forEach((p) -> {
            partIdList.add(p.getId());
         });
        
        partId = Collections.max(partIdList);
        
        List<Integer> productIdList = new ArrayList<>();

        Inventory.getAllProducts().forEach((p) -> 
        {
            productIdList.add(p.getId());
        });

        int productId = Collections.max(productIdList);
         
    }

    public static int getNextPartId()
    {
        
        partId++;       
        return partId;
        
    }

    public static int getNextProductId()
    {
        
        productId++;
        return productId;
        
    }
    
    public static void selectedPartId(int id)
    {
        selectedPartId = id;
    }
    
    public static void selectedProductId(int id)
    {
        selectedProductId = id;
    }
    
    public static int getPartId()
    {
        return selectedPartId;
    }
    
    public static int getProductId()
    {
        return selectedProductId;
    }
}
