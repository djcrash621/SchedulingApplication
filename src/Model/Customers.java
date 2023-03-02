package Model;

import java.sql.Timestamp;

/**
 * This class defines the methods for the Customers class.
 */
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

    /**
     * Constructor.
     * @param newCustomerId new Customer ID integer.
     * @param newCustomerName new Customer Name String.
     * @param newAddress new Address String.
     * @param newPostalCode new postal code String
     * @param newPhoneNum new Phone Num String
     * @param newDivisionId new Division ID String
     */
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

    /**
     * Returns the customer ID of the current object.
     * @return Customer ID Intger.
     */
    public int getCustomerId() { return this.customerId; }

    /**
     * Sets a new customer id for the current object.
     * @param newCustomerId new Customer ID integer.
     */
    public void setCustomerId(int newCustomerId) { this.customerId = newCustomerId; }

    /**
     * returns the customer name for the current object.
     * @return Customer Name String.
     */
    public String getCustomerName() { return this.customerName; }

    /**
     * Sets a new customer name for the current object.
     * @param newCustomerName new Customer Name String.
     */
    public void setCustomerName(String newCustomerName) { this.customerName = newCustomerName; }

    /**
     * Returns the address for the current object.
     * @return Address string.
     */
    public String getAddress() { return this.address; }

    /**
     * Sets a new address for the current object.
     * @param newAddress new Address String
     */
    public void setAddress(String newAddress) { this.address = newAddress; }

    /**
     * Returns the postal code for the current object.
     * @return Postal Code String.
     */
    public String getPostalCode() { return this.postalCode; }

    /**
     * Sets a new postal code for the current object.
     * @param newPostalCode new Postal Code String.
     */
    public void setPostalCode(String newPostalCode) { this.postalCode = newPostalCode; }

    /**
     * Returns the phone number for the current object.
     * @return Phone Number String.
     */
    public String getPhoneNum() { return this.phoneNum; }

    /**
     * Sets a new phone number for the current object.
     * @param newPhoneNum new Phone Number String.
     */
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

    /**
     * Returns the division ID for the current object.
     * @return Division ID integer.
     */
    public int getDivisionId() { return this.divisionId; }

    /**
     * Sets a new division ID for the current object.
     * @param newDivisionId new Division ID integer.
     */
    public void setDivisionId(int newDivisionId) { this.divisionId = newDivisionId; }

    /**
     * Overrides the default to string method to return the customer name.
     * @return customer name String.
     */
    @Override
    public String toString() {
        return (this.customerName);
    }
}
