package Controllers;

import DBAccess.DBAppointments;
import DBAccess.DBUsers;
import Main.Scheduling_Application;
import Model.Appointments;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * This class defines the methods and serves as the controller for the login page.
 */
public class login_controller implements Initializable {

    public Button login_btn;
    public TextField username_input;
    public PasswordField password_input;
    public Label login_lbl;
    public Label zoneId_lbl;
    public Label location_lbl;
    public static ResourceBundle rb;
    public static String fileName = "login_activity.txt";
    public static FileWriter fWriter;
    public static PrintWriter output;

    static {
        try {
            fWriter = new FileWriter(fileName, true);
            output =  new PrintWriter(new FileOutputStream(new File(fileName), true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * This method initializes the page, setting the text to the correct license via the resource bundle.
     * Prints to the login_activity record that the application has been opened.
     * Sets the zone id to the system default time zone.
     * @param url Unused parameter.
     * @param resourcebundle Unused parameter.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourcebundle) {
        username_input.setPromptText(rb.getString("Enter") + " " + rb.getString("username"));
        password_input.setPromptText(rb.getString("Enter") + " " + rb.getString("password"));
        login_lbl.setText(rb.getString("Welcome"));
        login_btn.setText(rb.getString("Login"));
        location_lbl.setText(rb.getString("Location") + ":");
        zoneId_lbl.setText(Scheduling_Application.localZone.toString());
        output.println(LocalDateTime.now() + ": APPLICATION OPENED");
    }

    /**
     * This method will check the username and password inputs and verifies if they exist in the database. If so, access is granted, and the page is changed
     * to the welcome/customer page. Successful login recording in login_activity log. If the information is not found, an error message is thrown, and the
     * access denied is logged in the login_activity log. Shows message for any upcoming, or no upcoming, appointments.
     * @param actionEvent Action taken to trigger method.
     * @throws IOException Exception thrown from page change.
     */
    public void attempt_login(ActionEvent actionEvent) throws IOException {

        boolean verify = DBUsers.loginCheck(username_input.getText(), password_input.getText(), rb);
        boolean isApt = false;

        if (verify) {
            output.println(LocalDateTime.now() + ": LOGIN ATTEMPT SUCCESSFUL.");
            output.close();
            Scheduling_Application.changePage(actionEvent,"../JavaFXML/welcome_page.fxml", "Welcome Page");
            for (Appointments apt : DBAppointments.getAllAppointments()) {
                if (LocalDateTime.now().plusMinutes(15).isAfter(apt.getStart()) && LocalDateTime.now().isBefore(apt.getStart())) {
                    Alert upComingApt = new Alert(Alert.AlertType.INFORMATION, "Appointment: " + apt.getAppointmentId() + " at " +
                            apt.getStart().format(DateTimeFormatter.ofPattern("HH:mm")) + " on " + apt.getStart().format(DateTimeFormatter.ofPattern("MM-dd-yyyy")),
                            ButtonType.OK);
                    upComingApt.setTitle("Upcoming Appointment");
                    upComingApt.showAndWait();
                    isApt = true;
                }
            }
            if (!isApt) {
                Alert noUpcomingApt = new Alert(Alert.AlertType.INFORMATION, "No upcoming appointments.", ButtonType.OK);
                noUpcomingApt.setTitle("Upcoming Appointment");
                noUpcomingApt.showAndWait();
            }
        }
        output.println(LocalDateTime.now() + ": LOGIN ATTEMPT UNSUCCESSFUL.");

    }

    /**
     * Methods receives the resource bundle from another page to be used to adjust language in this page.
     * @param resourceBundle Resource bundle passed in from the function call.
     */
    public static void getResourceBundle (ResourceBundle resourceBundle) {
        rb = resourceBundle;
    }

    /**
     * Closes the login_activity printer writer.
     */
    public static void closePrinter() {
        output.println(LocalDateTime.now() + ": Application Closed.");
        output.close();
    }

}
