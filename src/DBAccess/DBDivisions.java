package DBAccess;

import Model.Divisions;
import Utilities.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * This class defines the methods querying the database for the Divisions class
 * and method returning the Division searched for in the local divisions ObservableList.
 */
public class DBDivisions {
    public static ObservableList<Divisions> allDivisions = FXCollections.observableArrayList();

    /**
     * This method queries the database for the values of the Divisions table and adds the value to the
     * allDivisions ObservableList for use throughout the application.
     */
    public static void getAllDivisions() {
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
    }

    /**
     * This method searches the allDivision ObservableList for the Division object with the
     * given Division ID. Returns null if not found.
     * @param searchId The Division ID to search for.
     * @return Returns the matching division, or null.
     */
    public static Divisions getDivision(int searchId) {
        for (Divisions a : allDivisions) {
            if (a.getDivisionId() == searchId) {
                return a;
            }
        }
        return null;
    }
}
