import java.awt.Image;

public class Possession {
//Data
	private String name;
	private Person owner;
	private Image img;
    private float price;
	
//Constructor
    
    /**
     * Sets the specifications of the possession.
     * @param ownerName
     * @param itemImg
     * @param itemPrice
     */
	public void Car(String ownerName, Image itemImg, float itemPrice) 
	{
        name = ownerName;
        img = itemImg;
        price = itemPrice;
	}

//Methods
	
	/**
	 * Gets the possession's price.
	 * @return the price of the item
	 */
    public float getPrice() 
    {
        return price;
    }
    
    /**
     * Sets the owner of the possession.
     * @param person
     */
    public void setOwner(Person person) 
    {
        owner = person;
    }
    
    /**
     * Sets the possession's image.
     * @param itemImg
     */
    public void setImage(Image itemImg) 
    {
        img = itemImg;
    }
    
    /**
     * Sets the possession's price.
     * @param itemPrice
     */
    public void setPrice(float itemPrice) 
    {
        price = itemPrice;
    }
	
}