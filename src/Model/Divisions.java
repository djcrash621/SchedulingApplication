package Model;

import java.sql.Timestamp;

/**
 * The divisions class and its methods.
 */
public class Divisions {
    private int divisionId;
    private String division;
    /*
    private Timestamp createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    */
    private int countryId;

    /**
     * Constructor.
     * @param newDivisionId new Divisions ID Integer
     * @param newDivision new Division name String
     * @param newCountryId new Country ID Integer
     */
    public Divisions(int newDivisionId, String newDivision, /*Timestamp newCreateDate, String newCreatedBy, Timestamp newLastUpdate, String newLastUpdatedBy,*/ int newCountryId) {
        this.divisionId = newDivisionId;
        this.division = newDivision;
        /*
        this.createDate = newCreateDate;
        this.createdBy = newCreatedBy;
        this.lastUpdate = newLastUpdate;
        this.lastUpdatedBy = newLastUpdatedBy;
        */
        this.countryId = newCountryId;
    }

    /**
     * Returns the division ID for the current object.
     * @return Division ID Integer.
     */
    public int getDivisionId() { return this.divisionId; }

    /**
     * Sets a new division id for the current object.
     * @param newDivisionId new Division ID integer.
     */
    public void setDivisionId(int newDivisionId) { this.divisionId = newDivisionId; }

    /**
     * Returns the division name for the current object.
     * @return Division name String
     */
    public String getDivision() { return this.division; }

    /**
     * Sets a new division name for the current object.
     * @param newDivision new Division Name String.
     */
    public void setDivision(String newDivision) { this.division = newDivision; }

    /*
    public void setCreateDate(Timestamp newCreateDate) { this.createDate = newCreateDate; }

    public Timestamp getCreateDate() { return this.createDate; }

    public void setCreatedBy(String newCreatedBy) { this.createdBy = newCreatedBy; }

    public String getCreatedBy() { return this.createdBy; }

    public void setLastUpdate(Timestamp newLastUpdate) { this.lastUpdate = newLastUpdate; }

    public Timestamp getLastUpdate() { return this.lastUpdate; }

    public void setLastUpdatedBy(String newLastUpdatedBy) { this.lastUpdatedBy = newLastUpdatedBy; }

    public String getLastUpdatedBy() { return this.lastUpdatedBy; }
     */

    /**
     * Returns the country ID for the current object.
     * @return Country ID integer.
     */
    public int getCountryId() { return this.countryId; }

    /**
     * Sets a new country id for the current object
     * @param newCountryId new Country ID integer.
     */
    public void setCountryId(int newCountryId) { this.countryId = newCountryId; }

    /**
     * Overrides the default toString method to return the division name of the object.
     * @return Division Name String.
     */
    @Override
    public String toString() {
        return (this.division);
    }
}
