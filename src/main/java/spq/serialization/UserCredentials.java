package spq.serialization;

/**
 * Class representing user credentials for password change.
 */
public class UserCredentials {
    private String name;
    private String oldPassword;
    private String newPassword;
    
    /**
     * Constructs a UserCredentials object.
     * 
     * @param name The name of the user.
     * @param oldPassword The old password of the user.
     * @param newPassword The new password to set for the user.
     */
    public UserCredentials(String name, String oldPassword, String newPassword) {
        this.name = name;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
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
     * Gets the old password of the user.
     * 
     * @return The old password of the user.
     */
    public String getOldPassword() {
        return oldPassword;
    }
    
    /**
     * Sets the old password of the user.
     * 
     * @param oldPassword The old password of the user.
     */
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
    
    /**
     * Gets the new password to set for the user.
     * 
     * @return The new password to set for the user.
     */
    public String getNewPassword() {
        return newPassword;
    }
    
    /**
     * Sets the new password for the user.
     * 
     * @param newPassword The new password to set for the user.
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }   
}
