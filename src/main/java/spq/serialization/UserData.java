package spq.serialization;

/**
 * Class representing user data.
 */
public class UserData {
	 private String name;
	 private String password;
	 private double purse;
	 private int type;
	 
	 /**
	  * Constructs a UserData object.
	  */
	 public UserData() {
	     // Required by serialization
	 }
	 
	 /**
	  * Constructs a UserData object with the specified parameters.
	  * 
	  * @param name The name of the user.
	  * @param password The password of the user.
	  * @param purse The amount of money in the user's purse.
	  * @param type The type of the user.
	  */
	 public UserData(String name, String password, double purse, int type) {
		super();
		this.name = name;
		this.password = password;
		this.purse = purse;
		this.type = type;
	}
	
	/**
	 * Gets the name of the user.
	 * 
	 * @return The name of the user.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of the user.
	 * 
	 * @param name The name of the user.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the password of the user.
	 * 
	 * @return The password of the user.
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets the password of the user.
	 * 
	 * @param password The password of the user.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Gets the amount of money in the user's purse.
	 * 
	 * @return The amount of money in the user's purse.
	 */
	public double getPurse() {
		return purse;
	}
	
	/**
	 * Sets the amount of money in the user's purse.
	 * 
	 * @param purse The amount of money in the user's purse.
	 */
	public void setPurse(double purse) {
		this.purse = purse;
	}
	
	/**
	 * Gets the type of the user.
	 * 
	 * @return The type of the user.
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * Sets the type of the user.
	 * 
	 * @param type The type of the user.
	 */
	public void setType(int type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "UserData [name=" + name + ", password=" + password + ", purse=" + purse + ", type=" + type + "]";
	}
}
