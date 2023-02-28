package Model;

import java.sql.Timestamp;

public class Users {
    private int userId;
    private String userName;
    private String password;
    private Timestamp createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;

    public Users(int newUserId, String newUserName, String newPassword, Timestamp newCreateDate, String newCreatedBy, Timestamp newLastUpdated, String newLastUpdatedBy){
        this.userId = newUserId;
        this.userName = newUserName;
        this.password = newPassword;
        this.createDate = newCreateDate;
        this.createdBy = newCreatedBy;
        this.lastUpdate = newLastUpdated;
        this.lastUpdatedBy = newLastUpdatedBy;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int newUserId) { this.userId = newUserId; }

    public void setUserName(String newUsername) {
        this.userName = newUsername;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public String getPassword() {
        return this.password;
    }

    public void setCreateDate(Timestamp newCreateDate) {
        this.createDate = newCreateDate;
    }

    public Timestamp getCreateDate() {
        return this.createDate;
    }

    public void setCreatedBy(String newCreatedBy) {
        this.createdBy = newCreatedBy;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setLastUpdate(Timestamp newLastUpdate) {
        this.lastUpdate = newLastUpdate;
    }

    public Timestamp getLastUpdate() {
        return this.lastUpdate;
    }

    public void setLastUpdatedBy(String newLastUpdatedBy) {
        this.lastUpdatedBy = newLastUpdatedBy;
    }

    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }

    @Override
    public String toString() {
        return (this.userName);
    }
}
