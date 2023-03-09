package DBAccess;

import Main.Scheduling_Application;
import Model.Appointments;
import Model.Contacts;
import Utilities.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * This class defines the Database Methods querying and updating data for the Appointment class.
 */
public class DBAppointments {

    /**
     * This method queries the database for all Appointments and loads the value into the static allAppointments ObservableList
     */
    public static ObservableList<Appointments> getAllAppointments() {
        ObservableList<Appointments> allApts = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM APPOINTMENTS";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentId = rs.getInt("APPOINTMENT_ID");
                String title = rs.getString("TITLE");
                String description = rs.getString("DESCRIPTION");
                String location = rs.getString("LOCATION");
                String type = rs.getString("TYPE");
                LocalDateTime start = rs.getTimestamp("START").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                int customerId = rs.getInt("CUSTOMER_ID");
                int userId = rs.getInt("USER_ID");
                int contactId = rs.getInt("CONTACT_ID");
                Appointments newAppointment = new Appointments(appointmentId, title, description, location, type, start, end, customerId, userId, contactId);
                allApts.add(newAppointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allApts;
    }


    /**
     * This method queries the database for appointments during the next 7 days and loads the value into the static weeklyAppointments ObservableList
     */
    public static ObservableList<Appointments> getWeeklyAppointments() {
        ObservableList<Appointments> weeklyApts = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM APPOINTMENTS WHERE START > NOW() and START < DATE_ADD(START, INTERVAL 7 DAY)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentId = rs.getInt("APPOINTMENT_ID");
                String title = rs.getString("TITLE");
                String description = rs.getString("DESCRIPTION");
                String location = rs.getString("LOCATION");
                String type = rs.getString("TYPE");
                LocalDateTime start = rs.getTimestamp("START").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                int customerId = rs.getInt("CUSTOMER_ID");
                int userId = rs.getInt("USER_ID");
                int contactId = rs.getInt("CONTACT_ID");
                Appointments newAppointment = new Appointments(appointmentId, title, description, location, type, start, end, customerId, userId, contactId);
                weeklyApts.add(newAppointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return weeklyApts;
    }


    /**
     * This method queries the database for appointments during the next 30 days and loads the value into the static monthlyAppointments ObservableList
     */
    public static ObservableList<Appointments> getMonthlyAppointments() {
        ObservableList<Appointments> monthlyApts = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM APPOINTMENTS WHERE MONTH(START) = MONTH(NOW())";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentId = rs.getInt("APPOINTMENT_ID");
                String title = rs.getString("TITLE");
                String description = rs.getString("DESCRIPTION");
                String location = rs.getString("LOCATION");
                String type = rs.getString("TYPE");
                LocalDateTime start = rs.getTimestamp("START").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                int customerId = rs.getInt("CUSTOMER_ID");
                int userId = rs.getInt("USER_ID");
                int contactId = rs.getInt("CONTACT_ID");
                Appointments newAppointment = new Appointments(appointmentId, title, description, location, type, start, end, customerId, userId, contactId);
                monthlyApts.add(newAppointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return monthlyApts;
    }

    /**
     * Method that adds the given appointment to the database and all, weekly, and monthly observable lists
     * @param appointment Appointment to be added to lists ands database
     * @throws SQLException Exception caught if there is an error in the sql statement.
     */
    public static void addApt(Appointments appointment) throws SQLException {
        try {
            String sql = "INSERT INTO CLIENT_SCHEDULE.APPOINTMENTS(TITLE, DESCRIPTION, LOCATION, TYPE, START, END, CUSTOMER_ID, USER_ID, CONTACT_ID)\n" +
                    "VALUES ( '" + appointment.getTitle() + "', '" + appointment.getDescription() + "', '" + appointment.getLocation() + "', '" + appointment.getType() + "', '" +
                    Timestamp.valueOf(appointment.getStart().atZone(Scheduling_Application.localZone).withZoneSameInstant(Scheduling_Application.utcZone).toLocalDateTime()) + "', '" +
                    Timestamp.valueOf(appointment.getEnd().atZone(Scheduling_Application.localZone).withZoneSameInstant(Scheduling_Application.utcZone).toLocalDateTime()) + "', " +
                    appointment.getCustomerId() + ", " + appointment.getUserId() + ", " + appointment.getContactId() + ")";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Method to update the appointment in the database and all Observable tables
     * @param appointment Appointment data to update into the table
     * @throws SQLException Exception caught if there is an error in the sql statement.
     */
    public static void updateApt(Appointments appointment) throws SQLException {
        try {
            String sql = "UPDATE CLIENT_SCHEDULE.APPOINTMENTS\n" +
                    "SET TITLE = '" + appointment.getTitle() + "', DESCRIPTION = '" + appointment.getDescription() + "', LOCATION = '" + appointment.getLocation() +
                    "', TYPE = '" + appointment.getType() + "', START = '" + Timestamp.valueOf(appointment.getStart().atZone(Scheduling_Application.localZone).withZoneSameInstant(Scheduling_Application.utcZone).toLocalDateTime()) + "', END ='" +
                    Timestamp.valueOf(appointment.getEnd().atZone(Scheduling_Application.localZone).withZoneSameInstant(Scheduling_Application.utcZone).toLocalDateTime()) + "', CUSTOMER_ID = " + appointment.getCustomerId() +
                    ", USER_ID = " + appointment.getUserId() + ", CONTACT_ID = " + appointment.getContactId() +
                    "\nWHERE APPOINTMENT_ID = " + appointment.getAppointmentId();
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();;
        }
    }

    /**
     * Gets the apt id for the item with the given title
     * @param aptTitle The title of the appointment of the appointment ID to be returned.
     * @return returns the int Appointment ID of the Appointment being searched for.
     * @throws SQLException Exception caught if there is an error in the sql statement.
     */
    public static int getAptId(String aptTitle) throws SQLException {
        String sql = "SELECT APPOINTMENT_ID FROM APPOINTMENTS WHERE TITLE = '" + aptTitle + "'";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        int result = 0;
        while (rs.next()){
            result = rs.getInt("APPOINTMENT_ID");
        }
        return result;
    }

    public static void deleteApt(int appointmentId) {
        try {
            String sql = "DELETE FROM APPOINTMENTS WHERE APPOINTMENT_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1,appointmentId);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
