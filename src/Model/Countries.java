package Model;

import java.sql.Time;
import java.sql.Timestamp;

public class Countries {
    private int countryId;
    private String name;
    private Timestamp createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;

    public Countries(int id, String name, Timestamp newCreateDate, String newCreatedBy, Timestamp newLastUpdate, String newLastUpdateBy) {
        this.countryId = id;
        this.name = name;
        this.createDate = newCreateDate;
        this.createdBy = newCreatedBy;
        this.lastUpdate = newLastUpdate;
        this.lastUpdatedBy = newLastUpdateBy;
    }

    public int getCountryId() { return countryId; }

    public void setCountryID(int newCountryId) {
        this.countryId = newCountryId;
    }

    public String getCountryName() { return name; }

    public void setCountryName(String newCountryName) {
        this.name = newCountryName;
    }

    public Timestamp getCreateDate() { return createDate; }

    public void setCreateDate(Timestamp newCreateDate) { this.createDate = newCreateDate; }

    public String getCreatedBy() { return createdBy; }

    public void setCreatedBy(String newCreatedBy) { this.createdBy = newCreatedBy; }

    public Timestamp getLastUpdate() { return this.lastUpdate; }

    public void setLastUpdate(Timestamp newLastUpdate) { this.lastUpdate = newLastUpdate; }

    public String getLastUpdatedBy() {return lastUpdatedBy;}

    public void setLastUpdatedBy(String newLastUpdateBy) { this.lastUpdatedBy = newLastUpdateBy; }



}
