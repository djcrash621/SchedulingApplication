package DBAccess;

import Main.Scheduling_Application;
import Model.Appointments;
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
    public static ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();
    public static ObservableList<Appointments> weeklyAppointments = FXCollections.observableArrayList();
    public static ObservableList<Appointments> monthlyAppointments = FXCollections.observableArrayList();


    /**
     * This method queries the database for all Appointments and loads the value into the static allAppointments ObservableList
     */
    public static void getAllAppointments() {
        allAppointments.clear();
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
                allAppointments.add(newAppointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * This method queries the database for appointments during the next 7 days and loads the value into the static weeklyAppointments ObservableList
     */
    public static void getWeeklyAppointments() {
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
                weeklyAppointments.add(newAppointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * This method queries the database for appointments during the next 30 days and loads the value into the static monthlyAppointments ObservableList
     */
    public static void getMonthlyAppointments() {
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
                monthlyAppointments.add(newAppointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that deletes the given appointment from the database and all observable lists
     * @param appointment The appointment passed in from the tables selectionModel
     */
    public static void deleteApt(Appointments appointment) {
        try {
            String sql = "DELETE FROM CLIENT_SCHEDULE.APPOINTMENTS WHERE APPOINTMENT_ID = " + appointment.getAppointmentId();
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.executeQuery();
            allAppointments.removeAll(appointment);
            if (appointment.getStart().isAfter(LocalDateTime.now()) && appointment.getStart().plusDays(7).isAfter(appointment.getStart())) {
                weeklyAppointments.removeAll(appointment);
            }
            if (appointment.getStart().getMonth() == LocalDateTime.now().getMonth()) {
                monthlyAppointments.removeAll(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        DBAppointments.allAppointments.add(new Appointments(getAptId(appointment.getTitle()), appointment.getTitle(), appointment.getDescription(), appointment.getLocation(), appointment.getType(), appointment.getStart(), appointment.getEnd(), appointment.getCustomerId(), appointment.getUserId(), appointment.getContactId()));
        if (appointment.getStart().isAfter(LocalDateTime.now()) && appointment.getStart().plusDays(7).isAfter(appointment.getStart())) {
            DBAppointments.weeklyAppointments.add(new Appointments(getAptId(appointment.getTitle()), appointment.getTitle(), appointment.getDescription(), appointment.getLocation(), appointment.getType(), appointment.getStart(), appointment.getEnd(), appointment.getCustomerId(), appointment.getUserId(), appointment.getContactId()));
        }
        if (appointment.getStart().getMonth() == LocalDateTime.now().getMonth()) {
            DBAppointments.monthlyAppointments.add(new Appointments(getAptId(appointment.getTitle()), appointment.getTitle(), appointment.getDescription(), appointment.getLocation(), appointment.getType(), appointment.getStart(), appointment.getEnd(), appointment.getCustomerId(), appointment.getUserId(), appointment.getContactId()));
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

        DBAppointments.allAppointments.removeAll(appointment);
        DBAppointments.allAppointments.add(new Appointments(getAptId(appointment.getTitle()), appointment.getTitle(), appointment.getDescription(), appointment.getLocation(), appointment.getType(), appointment.getStart(), appointment.getEnd(), appointment.getCustomerId(), appointment.getUserId(), appointment.getContactId()));
        if (appointment.getStart().isAfter(LocalDateTime.now()) && appointment.getStart().plusDays(7).isAfter(appointment.getStart())) {
            DBAppointments.weeklyAppointments.removeAll(appointment);
            DBAppointments.weeklyAppointments.add(new Appointments(getAptId(appointment.getTitle()), appointment.getTitle(), appointment.getDescription(), appointment.getLocation(), appointment.getType(), appointment.getStart(), appointment.getEnd(), appointment.getCustomerId(), appointment.getUserId(), appointment.getContactId()));
        }
        if (appointment.getStart().getMonth() == LocalDateTime.now().getMonth()) {
            DBAppointments.monthlyAppointments.removeAll(appointment);
            DBAppointments.monthlyAppointments.add(new Appointments(getAptId(appointment.getTitle()), appointment.getTitle(), appointment.getDescription(), appointment.getLocation(), appointment.getType(), appointment.getStart(), appointment.getEnd(), appointment.getCustomerId(), appointment.getUserId(), appointment.getContactId()));
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


    /*
    public static ObservableList getTypeMonthReport() throws SQLException {
        ObservableList typeMonthReport = FXCollections.observableArrayList();
        try {
            String sql = "SELECT COUNT(appointments.Appointment_ID) as Appointment_Count, appointments.type as Type, MONTHNAME(appointments.Start) as Month\n" +
                    "FROM APPOINTMENTS\n" +
                    "GROUP BY appointments.type, Month(appointments.Start);";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int count = rs.getInt("Count");
                String type = rs.getString("Type");
                String month = rs.getString("Month");


            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     */
}
