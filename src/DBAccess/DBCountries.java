package DBAccess;

import Model.Divisions;
import Utilities.JDBC;
import Model.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/**
 * This class defines the methods querying the database data for the Countries class.
 */
public class DBCountries {

    public static ObservableList<Countries> countryList = FXCollections.observableArrayList();

    /**
     * This method queries the database for all data in the countries table and adds
     * them to the countryList ObservableList to be used throughout the application.
     */
    public static void getAllCountries() {

        try {
            String sql = "SELECT * FROM COUNTRIES";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int countryId = rs.getInt("COUNTRY_ID");
                String countryName = rs.getString("COUNTRY");
                Countries newCountry = new Countries(countryId, countryName);
                countryList.add(newCountry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method searches for the Country in which the given Division is found
     * in the CountryList.
     * @param searchDiv The division whose country is being searched for.
     * @return Returns the country that corresponds to the given Division.
     */
    public static Countries getCountry(Divisions searchDiv) {
        for (Countries c : countryList) {
            if (c.getCountryId() == searchDiv.getCountryId()) {
                return c;
            }
        }
        return null;
    }

}
