package felixcaban.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * Represents a product in inventory, which may or may not be composed of one or 
 * many {@link felixcaban.models.Part Parts}.
 * 
 * @author felix.caban
 * @version 1.0
 * @since 1.0
 * 
 */ 
public class Product
{    
    
    /**
     * 
     * If applicable, the list of {@link felixcaban.models.Part Parts} that the product is composed of.
     * 
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();;
    
    /**
     * 
     * The unique id of the product.
     * 
     */
    private int id;
    
    /**
     * 
     * The name of the product.
     * 
     */
    private String name;
    
    /**
     * 
     * The price of the product.
     * 
     */
    private double price;
    
    /**
     * 
     * The current quantity on-hand of the product.
     * 
     */
    private int stock;
    
    /**
     * 
     * The minimum quantity allowed on-hand of the product.
     * 
     */
    private int min;
    
    /**
     * 
     * The maximum quantity allowed on-hand of the product.
     * 
     */
    private int max;   

   /**
     *
     * Creates a new {@link #Product(int, java.lang.String, double, int, int, int) Product} instance.
     * 
     * @param id the unique id of the in-house part.
     * @param name the name of the in-house part.
     * @param price the price of the in-house part.
     * @param stock the current quantity on-hand of the in-house part.
     * @param min the minimum allowable on-hand quantity of the in-house part.
     * @param max the maximum allowable on-hand quantity of the in-house part.
     * 
     */
    public Product(int id, String name, double price, int stock, int min, int max) 
    {
        
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        
    }

    /**
     * 
     * Sets the {@link felixcaban.models.Product#id id} of the product.
     * 
     * @param id the unique {@link felixcaban.models.Product#id id} to set.
     * 
     */
    public void setId(int id) 
    {
        
        this.id = id;
        
    }

    /**
     * 
     * Sets the {@link felixcaban.models.Product#name name} of the product.
     * 
     * @param name the {@link felixcaban.models.Product#name name} to set.
     * 
     */
    public void setName(String name) 
    {
        
        this.name = name;
        
    }

    /**
     * 
     * Sets the {@link felixcaban.models.Product#price price} of the product.
     * 
     * @param price the {@link felixcaban.models.Product#price price} to set.
     * 
     */
    public void setPrice(double price) 
    {
        
        this.price = price;
        
    }

    /**
     * 
     * Sets the {@link felixcaban.models.Product#stock stock} of the product.
     * 
     * @param stock the {@link felixcaban.models.Product#stock stock} to set.
     * 
     */
    public void setStock(int stock) 
    {
        
        this.stock = stock;
        
    }

    /**
     * 
     * Sets the {@link felixcaban.models.Product#min min} of the product.
     * 
     * @param min the {@link felixcaban.models.Product#min min} to set.
     * 
     */
    public void setMin(int min) 
    {
        
        this.min = min;
        
    }

   /**
     * 
     * Sets the {@link felixcaban.models.Product#max max} of the product.
     * 
     * @param max the {@link felixcaban.models.Product#max max} to set.
     * 
     */
    public void setMax(int max) 
    {
        
        this.max = max;
        
    }
    
    /**
     * 
     * Gets the {@link felixcaban.models.Product#id id} of the product.
     * 
     * @return the unique {@link felixcaban.models.Product#id id} of the product.
     * 
     */
    public int getId() 
    {
        
        return id;
        
    }

    /**
     * 
     * Gets the {@link felixcaban.models.Product#name name} of the product.
     * 
     * @return the {@link felixcaban.models.Product#name name} of the product.
     * 
     */
    public String getName() 
    {
        
        return name;
        
    }
    
    /**
     * 
     * Gets the {@link felixcaban.models.Product#price price} of the product.
     * 
     * @return the {@link felixcaban.models.Product#price price} of the product.
     * 
     */
    public double getPrice() 
    {
        
        return price;
        
    }
    
    /**
     * 
     * Gets the {@link felixcaban.models.Product#stock stock} of the product.
     * 
     * @return the {@link felixcaban.models.Product#stock stock} of the product.
     * 
     */ 
    public int getStock() 
    {
        
        return stock;
        
    }

    /**
     * 
     * Gets the {@link felixcaban.models.Product#min min} of the product.
     * 
     * @return the {@link felixcaban.models.Product#min min} of the product.
     * 
     */ 
    public int getMin() 
    {
        
        return min;
        
    }

    /**
     * 
     * Gets the {@link felixcaban.models.Product#max max} of the product.
     * 
     * @return the {@link felixcaban.models.Product#max max} of the product.
     * 
     */ 
    public int getMax() 
    {
        
        return max;
        
    }
    
     /**
     * 
     * Adds any applicable {@link felixcaban.models.Part Parts} to the product.
     * 
     * @param part the {@link felixcaban.models.Part Part} to add to the product.
     * 
     */ 
    public void addAssociatedPart(Part part)
    {
        
        this.associatedParts.add(part);
        
    }
    
    /**
     * 
     * Deletes a {@link felixcaban.models.Part Part} from the product.
     * 
     * @param selectedAssociatedPart the {@link felixcaban.models.Part Part} to delete from the product.
     * 
     * @return true if the {@link felixcaban.models.Part Part} was deleted, false if not.
     * 
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart)
    {
        
        return associatedParts.removeIf(p -> (p.equals(selectedAssociatedPart)));
        
    }
    
    /**
     *
     * Gets a list of all associated {@link felixcaban.models.Part Parts} for the product.
     * 
     * @return a list of all associated {@link felixcaban.models.Part Parts}.
     */
    public ObservableList<Part> getAllAssociatedParts()
    {
        
        return associatedParts;
        
    }
    
}
