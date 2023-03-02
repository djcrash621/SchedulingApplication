package Model;

import java.sql.Timestamp;

/**
 * Initializes and defines the users class.
 */
public class Users {
    private int userId;
    private String userName;
    private String password;


    /**
     * Constructor.
     * @param newUserId new User ID integer
     * @param newUserName new User Name String
     * @param newPassword new Password String.
     */
    public Users(int newUserId, String newUserName, String newPassword){
        this.userId = newUserId;
        this.userName = newUserName;
        this.password = newPassword;
    }

    /**
     * returns the User ID of the current object.
     * @return userID integer.
     */
    public int getUserId() {
        return this.userId;
    }

    /**
     * Sets a new user id for the current object.
     * @param newUserId new User ID Integer.
     */
    public void setUserId(int newUserId) { this.userId = newUserId; }

    /**
     * Sets a new userName for the current object.
     * @param newUsername new User Name String.
     */
    public void setUserName(String newUsername) {
        this.userName = newUsername;
    }

    /**
     * Returns the username for the current object.
     * @return username String
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Sets a new password for the current object.
     * @param newPassword new Password String
     */
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    /**
     * Returns the password for the current object.
     * @return Password String
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Overrides the default toString method to return the username.
     * @return Username String.
     */
    @Override
    public String toString() {
        return (this.userName);
    }
}
