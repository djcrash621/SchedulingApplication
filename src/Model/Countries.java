package Model;

import java.sql.Time;
import java.sql.Timestamp;

/**
 * Class that defines the methods for the countries object.
 */
public class Countries {
    private int countryId;
    private String name;


    /**
     * Constructor
     * @param id new ID integer
     * @param name new Name Integer
     */
    public Countries(int id, String name) {
        this.countryId = id;
        this.name = name;
    }

    /**
     * Returns the country id for the current object.
     * @return Country ID integer.
     */
    public int getCountryId() { return countryId; }

    /**
     * Sets the country id for the current object.
     * @param newCountryId new Country ID integer.
     */
    public void setCountryID(int newCountryId) {
        this.countryId = newCountryId;
    }

    /**
     * Returns the country name for the current object.
     * @return Country Name String.
     */
    public String getCountryName() { return name; }

    /**
     * Sets a new country name for the current object.
     * @param newCountryName new Country name String.
     */
    public void setCountryName(String newCountryName) {
        this.name = newCountryName;
    }

    /**
     * Overrides default toString method to return the country name of the object.
     * @return Country Name String.
     */
    @Override
    public String toString() {
        return (this.name);
    }


}
