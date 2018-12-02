import java.awt.Image;

public class Pet {
// Data
	private String petName; 
	private Person owner;
	private Image img; 
	
// Constructor
	/**
	 * Sets the pet's personal information with a picture.
	 * @param petName
	 * @param petPic
	 */
	public Pet(String name, Image petPic) 
	{
		petName = name; 
		img = petPic;
	}

// Methods
	
	/**
	 * Set's the owner of the pet. 
	 * @param theOwner
	 */
	public void setOwner (Person theOwner)
	{
		owner = theOwner;
	}
}
