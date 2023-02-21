package Model;

import java.sql.Timestamp;

public class Customers {
    private int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phoneNum;
    /*private Timestamp createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    */
    private int divisionId;

    public Customers(int newCustomerId, String newCustomerName, String newAddress, String newPostalCode, String newPhoneNum, /*Timestamp newCreateDate, String newCreatedBy, Timestamp newLastUpdate, String newLastUpdatedBy, */int newDivisionId) {
        this.customerId = newCustomerId;
        this.customerName = newCustomerName;
        this.address = newAddress;
        this.postalCode = newPostalCode;
        this.phoneNum = newPhoneNum;
        /*
        this.createDate = newCreateDate;
        this.createdBy = newCreatedBy;
        this.lastUpdate = newLastUpdate;
        this.lastUpdatedBy = newLastUpdatedBy;
        */
        this.divisionId = newDivisionId;
    }

    public int getCustomerId() { return this.customerId; }

    public void setCustomerId(int newCustomerId) {
        this.customerId = newCustomerId;
    }

    public String getCustomerName() { return this.customerName; }

    public void setCustomerName(String newCustomerName) { this.customerName = newCustomerName; }

    public String getAddress() { return this.address; }

    public void setAddress(String newAddress) { this.address = newAddress; }

    public String getPostalCode() { return this.postalCode; }

    public void setPostalCode(String newPostalCode) { this.postalCode = newPostalCode; }

    public String getPhoneNum() { return this.phoneNum; }

    public void setPhoneNum(String newPhoneNum) {this.phoneNum = newPhoneNum; }

    /*

    public Timestamp getCreateDate() { return this.createDate; }

    public void setCreateDate(Timestamp newCreateDate) { this.createDate = newCreateDate; }

    public String getCreatedBy() { return this.createdBy; }

    public void setCreatedBy(String newCreatedBy) { this.createdBy = newCreatedBy; }

    public Timestamp getLastUpdate() { return this.lastUpdate; }

    public void setLastUpdate(Timestamp newLastUpdate) { this.lastUpdate = newLastUpdate; }

    public String getLastUpdatedBy() { return this.lastUpdatedBy; }

    public void setLastUpdatedBy(String newLastUpdatedBy) { this.lastUpdatedBy = newLastUpdatedBy; }

     */

    public int getDivisionId() { return this.divisionId; }

    public void setDivisionId(int newDivisionId) { this.divisionId = newDivisionId; }

    @Override
    public String toString() {
        return (this.customerName);
    }
}
