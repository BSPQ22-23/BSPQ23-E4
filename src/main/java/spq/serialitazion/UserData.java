package spq.serialitazion;

public class UserData {
	 private String name;
	 private String password;
	 private double purse;
	 private int type;
	 public UserData() {
	        // required by serialization
	    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getPurse() {
		return purse;
	}
	public void setPurse(double purse) {
		this.purse = purse;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "UserData [name=" + name + ", password=" + password + "]";
	}
	 
}
