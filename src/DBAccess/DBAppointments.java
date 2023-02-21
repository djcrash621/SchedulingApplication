package DBAccess;

import Main.Scheduling_Application;
import Model.Appointments;
import Model.Customers;
import Utilities.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DBAppointments {
    public static ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();
    public static ObservableList<Appointments> weeklyAppointments = FXCollections.observableArrayList();
    public static ObservableList<Appointments> monthlyAppointments = FXCollections.observableArrayList();

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

    public static void deleteApt(Appointments appointment) {
        try {
            String sql = "DELETE FROM CLIENT_SCHEDULE.APPOINTMENTS WHERE APPOINTMENT_ID = " + appointment.getAppointmentId();
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    public static void updateApt(Appointments appointment) {
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
}
