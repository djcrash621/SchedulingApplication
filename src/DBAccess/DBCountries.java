package DBAccess;

import Model.Divisions;
import Utilities.JDBC;
import Model.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DBCountries {

    public static ObservableList<Countries> countryList = FXCollections.observableArrayList();

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

    public static Countries getCountry(Divisions searchDiv) {
        for (Countries c : countryList) {
            if (c.getCountryId() == searchDiv.getCountryId()) {
                return c;
            }
        }
        return null;
    }

}
