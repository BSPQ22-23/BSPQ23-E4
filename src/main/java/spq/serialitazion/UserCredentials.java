package spq.serialitazion;

public class UserCredentials {
    private String name;
    private String oldPassword;
    private String newPassword;
    
    
	public UserCredentials(String name, String oldPassword, String newPassword) {
		super();
		this.name = name;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
}