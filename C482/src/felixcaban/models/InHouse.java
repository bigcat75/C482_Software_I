package felixcaban.models;

/**
 *
 * Represents an in-house part, which extends {@link felixcaban.models.Part Part}.
 * This class adds the {@link #machineId machineId} for in-house parts.
 * 
 * @author felix.caban
 * @version 1.0
 * @since 1.0
 * 
 */
public class InHouse extends Part
{  
    /**
     * 
     * The machine id of the in-house part.
     * 
     */
    private int machineId;

    /**
     *
     * Creates a new {@link #InHouse(int, java.lang.String, double, int, int, int, int) InHouse} instance.
     * 
     * @param id the unique id of the in-house part.
     * @param name the name of the in-house part.
     * @param price the price of the in-house part.
     * @param stock the current quantity on-hand of the in-house part.
     * @param min the minimum allowable on-hand quantity of the in-house part.
     * @param max the maximum allowable on-hand quantity of the in-house part.
     * @param machineId the machine id of the in-house part.
     * 
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) 
    {
        
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
        
    }
    
    /**
     *
     * Sets the {@link #machineId machineId} of the in-house part.
     * 
     * @param machineId the id of the {@link #machineId machine}.
     * 
     */
    public void setMachineId(int machineId)
    {
        
        this.machineId = machineId;  
        
    }
    
    /**
     *
     * Gets the {@link #machineId machineId} of the in-house part.
     * 
     * @return the id of the {@link #machineId machine}.
     * 
     */
    public int getMachineId()
    {
        
        return machineId;
        
    }

}
