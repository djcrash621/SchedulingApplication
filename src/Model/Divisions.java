package Model;

import java.sql.Timestamp;

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

    public int getDivisionId() { return this.divisionId; }

    public void setDivisionId(int newDivisionId) { this.divisionId = newDivisionId; }

    public String getDivision() { return this.division; }

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

    public int getCountryId() { return this.countryId; }

    public void setCountryId(int newCountryId) { this.countryId = newCountryId; }

    @Override
    public String toString() {
        return (this.division);
    }
}
