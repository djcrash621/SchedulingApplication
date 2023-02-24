package Controllers;

import DBAccess.DBAppointments;
import DBAccess.DBUsers;
import Main.Scheduling_Application;
import Model.Appointments;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

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

    static {
        try {
            fWriter = new FileWriter(fileName, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PrintWriter output;

    static {
        try {
            output = new PrintWriter(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

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
    
    public void attempt_login(ActionEvent actionEvent) throws IOException {

        boolean verify = DBUsers.loginCheck(username_input.getText(), password_input.getText(), rb);
        boolean isApt = false;

        if (verify) {
            output.println(LocalDateTime.now() + ": LOGIN ATTEMPT SUCCESSFUL.");
            output.close();
            Scheduling_Application.changePage(actionEvent,"../JavaFXML/welcome_page.fxml", "Welcome Page");
            for (Appointments apt : DBAppointments.allAppointments) {
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

    public static void getResourceBundle (ResourceBundle resourceBundle) {
        rb = resourceBundle;
    }

    public static void closePrinter() {
        output.println(LocalDateTime.now() + ": Application Closed.");
        output.close();
    }

}
