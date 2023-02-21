package DBAccess;

import Utilities.JDBC;
import Model.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DBCountries {

    static ObservableList<Countries> countryList = FXCollections.observableArrayList();

    public static ObservableList<Countries> getAllCountries() {

        try {
            String sql = "SELECT * FROM COUNTRIES";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int countryId = rs.getInt("COUNTRY_ID");
                String countryName = rs.getString("COUNTRY");
                Timestamp createDate = rs.getTimestamp("CREATE_DATE");
                String createdBy = rs.getString("CREATED_BY");
                Timestamp lastUpdate = rs.getTimestamp("LAST_UPDATE");
                String lastUpdatedBy = rs.getString("LAST_UPDATED_BY");
                Countries newCountry = new Countries(countryId, countryName, createDate, createdBy, lastUpdate, lastUpdatedBy);
                countryList.add(newCountry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countryList;
    }

    public static void checkDateConversion() { //Probably not necessary but just in case
        System.out.println("Create Date Test");
        String sql = "SELECT CREATE_DATE FROM COUNTRIES";
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Timestamp ts = rs.getTimestamp("CREATE DATE");
                System.out.println("CD: " + ts.toLocalDateTime().toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
