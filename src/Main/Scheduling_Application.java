package Main;

import Controllers.login_controller;
import DBAccess.*;
import Model.Appointments;
import Model.Users;
import Utilities.JDBC;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class that initiates the Scheduling Application and defines several application-wide methods.
 */
public class Scheduling_Application extends Application {

    public static String rbLocation = "Utilities/Nat";
    public static Users activeUser;
    public static ZoneId localZone = ZoneId.systemDefault();
    public static ZoneId easternZone = ZoneId.of("US/Eastern");
    public static ZoneId utcZone = ZoneId.of("UTC");


    /**
     * Method that runs at application startup, calling necessary methods for the application and loading the initial login page.
     * @param stage The application stage.
     * @throws Exception Exception for page change.
     */
    @Override
    public void start(Stage stage) throws Exception{

        //Locale.setDefault(new Locale("fr", "FR"));
        Appointments.convertedToZoneTime(Appointments.unconvertedTime);

        ResourceBundle rb = ResourceBundle.getBundle(rbLocation, Locale.getDefault());
        login_controller.getResourceBundle(rb);
        DBCustomers.getAllCustomers();
        DBAppointments.getWeeklyAppointments();
        DBAppointments.getMonthlyAppointments();
        DBAppointments.getAllAppointments();
        DBCountries.getAllCountries();
        DBContacts.getAllContacts();
        DBDivisions.getAllDivisions();
        DBUsers.getAllUsers();

        FXMLLoader fxmlLoader = new FXMLLoader(Scheduling_Application.class.getResource("../JavaFXML/login_page.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(rb.getString("Login") + " " + rb.getString("Page"));
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Method used to display an error message in the UI.
     * @param errorMessage Error message to be displayed.
     */
    public static void displayError(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage, ButtonType.OK);
        alert.showAndWait();
    }

    /**
     * Method to change page to specified page.
     * @param actionEvent Action to trigger method.
     * @param pageChange the directory of the page to be changed to.
     * @param title Title to set for the new page.
     * @throws IOException Exception for page change.
     */
    public static void changePage(ActionEvent actionEvent, String pageChange, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Scheduling_Application.class.getResource(pageChange));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main method to make database connection, launch application, and close the login controller.
     * @param args Unused parameter.
     */
    public static void main(String[] args) {
        //System.out.println(Locale.getDefault().getLanguage());
        JDBC.makeConnection();

        launch(args);

        JDBC.closeConnection();
        login_controller.closePrinter();
    }
}
