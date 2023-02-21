package DBAccess;

import Model.Divisions;
import Utilities.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class DBDivisions {
    public static ObservableList<Divisions> allDivisions = FXCollections.observableArrayList();

    public static ObservableList<Divisions> getAllDivisions() {
        allDivisions.clear();
        try {
            String sql = "SELECT * FROM FIRST_LEVEL_DIVISIONS";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int divisionId = rs.getInt("DIVISION_ID");
                String division = rs.getString("DIVISION");
                int countryId = rs.getInt("COUNTRY_ID");
                allDivisions.add(new Divisions(divisionId, division, countryId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allDivisions;
    }

    public static Divisions getDivision(int searchId) {
        for (Divisions a : allDivisions) {
            if (a.getDivisionId() == searchId) {
                return a;
            }
        }
        return null;
    }
}
