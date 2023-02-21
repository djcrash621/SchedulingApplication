package DBAccess;

import Utilities.JDBC;
import Model.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DBCountries {

    public static ObservableList<Countries> countryList = FXCollections.observableArrayList();

    public static ObservableList<Countries> getAllCountries() {

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
        return countryList;
    }

}
