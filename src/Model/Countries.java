package Model;

import java.sql.Time;
import java.sql.Timestamp;

public class Countries {
    private int countryId;
    private String name;


    public Countries(int id, String name) {
        this.countryId = id;
        this.name = name;

    }

    public int getCountryId() { return countryId; }

    public void setCountryID(int newCountryId) {
        this.countryId = newCountryId;
    }

    public String getCountryName() { return name; }

    public void setCountryName(String newCountryName) {
        this.name = newCountryName;
    }

    @Override
    public String toString() {
        return (this.name);
    }


}
