package DBAccess;

import Model.Appointments;
import Model.Contacts;
import Model.Users;
import Utilities.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * This method defines the methods querying the database for the information
 * needed for the reporting page of the application.
 */
public class DBReporting {

    /**
     * This method queries the database for the distinct type values found in
     * the appointments table.
     * @return An observable list of all queried types.
     * @throws SQLException Exception thrown from database command.
     */
    public static ObservableList<String> getTypes() throws SQLException {
        ObservableList<String> typeList = FXCollections.observableArrayList();
        String sql = "SELECT DISTINCT TYPE FROM APPOINTMENTS";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            typeList.add(rs.getString("TYPE"));
        }
        return typeList;
    }

    /**
     * This method queries the database for the distinct month values found in
     * the appointments table.
     * @return An observable list of all queried months.
     * @throws SQLException Exception thrown from database command.
     */
    public static ObservableList<String> getMonths() throws SQLException {
        ObservableList<String> typeList = FXCollections.observableArrayList();
        String sql = "SELECT DISTINCT MONTHNAME(START) AS MONTHS FROM APPOINTMENTS";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            typeList.add(rs.getString("MONTHS"));
        }
        return typeList;
    }

    /**
     * Queries the database for all appointments with the given type and monthname values of the start date.
     * @param selectedType The type to search for in the appointments table.
     * @param selectedMonth The month to search for in the start column of the appointments table.
     * @return An observable list of all appointments with the matching values.
     */
    public static ObservableList<Appointments> typeMonthApts(String selectedType, String selectedMonth) {
        ObservableList<Appointments> list = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM APPOINTMENTS WHERE TYPE = '" + selectedType + "' AND MONTHNAME(START) = '" + selectedMonth + "';";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentId = rs.getInt("APPOINTMENT_ID");
                String title = rs.getString("TITLE");
                String description = rs.getString("DESCRIPTION");
                String location = rs.getString("LOCATION");
                String type = rs.getString("TYPE");
                LocalDateTime start = rs.getTimestamp("START").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("END").toLocalDateTime();
                int customerId = rs.getInt("CUSTOMER_ID");
                int userId = rs.getInt("USER_ID");
                int contactId = rs.getInt("CONTACT_ID");
                Appointments newAppointment = new Appointments(appointmentId, title, description, location, type, start, end, customerId, userId, contactId);
                list.add(newAppointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * This method queries the database for all appointments with the given contact value.
     * @param givenContact The contact to search for in the customerId column of the appointments table.
     * @return An observable list of all appointments with the matching value.
     */
    public static ObservableList<Appointments> getContactApts(Contacts givenContact) {
        ObservableList<Appointments> list = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM APPOINTMENTS WHERE CONTACT_ID = '" + givenContact.getContactId() + "';";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentId = rs.getInt("APPOINTMENT_ID");
                String title = rs.getString("TITLE");
                String description = rs.getString("DESCRIPTION");
                String location = rs.getString("LOCATION");
                String type = rs.getString("TYPE");
                LocalDateTime start = rs.getTimestamp("START").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("END").toLocalDateTime();
                int customerId = rs.getInt("CUSTOMER_ID");
                int userId = rs.getInt("USER_ID");
                int contactId = rs.getInt("CONTACT_ID");
                Appointments newAppointment = new Appointments(appointmentId, title, description, location, type, start, end, customerId, userId, contactId);
                list.add(newAppointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Method queries the database for all appointments with the userID of the given user value.
     * @param givenUser The user to search for in the appointments table.
     * @return An observableList of the appointments with the matching user value.
     */
    public static ObservableList<Appointments> getUserApts(Users givenUser) {
        ObservableList<Appointments> list = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM APPOINTMENTS WHERE USER_ID = '" + givenUser.getUserId() + "';";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentId = rs.getInt("APPOINTMENT_ID");
                String title = rs.getString("TITLE");
                String description = rs.getString("DESCRIPTION");
                String location = rs.getString("LOCATION");
                String type = rs.getString("TYPE");
                LocalDateTime start = rs.getTimestamp("START").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("END").toLocalDateTime();
                int customerId = rs.getInt("CUSTOMER_ID");
                int userId = rs.getInt("USER_ID");
                int contactId = rs.getInt("CONTACT_ID");
                Appointments newAppointment = new Appointments(appointmentId, title, description, location, type, start, end, customerId, userId, contactId);
                list.add(newAppointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
