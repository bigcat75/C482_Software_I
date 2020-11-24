package felixcaban.models;

/**
 *
 * Represents an outsourced part, which extends {@link felixcaban.models.Part Part}.
 * This class adds the {@link #companyName CompanyName} for outsourced parts.
 * 
 * @author felix.caban
 * @version 1.0
 * @since 1.0
 * 
 */
public class Outsourced extends Part
{   
     /**
     * 
     * The company name of the in-house part.
     * 
     */
    private String companyName;
    
     /**
     *
     * Creates a new instance {@link #Outsourced(int, java.lang.String, double, int, int, int, java.lang.String) Outsourced} instance.
     * 
     * @param id the unique id of the outsourced part.
     * @param name the name of the outsourced part.
     * @param price the price of the outsourced part.
     * @param stock the current quantity on-hand of the outsourced part.
     * @param min the minimum allowable on-hand quantity of the outsourced part.
     * @param max the maximum allowable on-hand quantity of the outsourced part.
     * @param companyName the company name of the outsourced part.
     * 
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) 
    {
        
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
        
    }
    
    /**
     *
     * Sets the {@link #companyName companyName} of the outsourced part.
     * 
     * @param companyName the name of the {@link #companyName company}.
     * 
     */
    public void setCompanyName(String companyName)
    {
        
        this.companyName = companyName;
        
    }
    
    /**
     *
     * Gets the {@link #companyName companyName} of the outsourced part.
     * 
     * @return the name of the {@link #companyName company}.
     * 
     */
    public String getCompanyName()
    {
        
        return companyName;
        
    }
    
}
