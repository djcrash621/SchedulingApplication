package Main;

import Controllers.login_controller;
import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import DBAccess.DBUsers;
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
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

public class Scheduling_Application extends Application {

    public static String rbLocation = "Utilities/Nat";
    public static Users activeUser;
    public static ZoneId localZone = ZoneId.systemDefault();
    public static ZoneId easternZone = ZoneId.of("US/Eastern");
    public static ZoneId utcZone = ZoneId.of("UTC");

    @Override
    public void start(Stage stage) throws Exception{

        //Locale.setDefault(new Locale("fr", "FR"));
        DBUsers.allUsers = DBUsers.getAllUsers();
        Appointments.convertedToZoneTime(Appointments.unconvertedTime);

        ResourceBundle rb = ResourceBundle.getBundle(rbLocation, Locale.getDefault());
        login_controller.getResourceBundle(rb);
        DBCustomers.getAllCustomers();
        DBAppointments.getWeeklyAppointments();
        DBAppointments.getMonthlyAppointments();
        DBAppointments.getAllAppointments();
        DBContacts.getAllContacts();

        FXMLLoader fxmlLoader = new FXMLLoader(Scheduling_Application.class.getResource("../JavaFXML/login_page.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(rb.getString("Login") + " " + rb.getString("Page"));
        stage.setScene(scene);
        stage.show();
    }

    public static void displayError(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage, ButtonType.OK);
        alert.showAndWait();
    }

    public static void changePage(ActionEvent actionEvent, String pageChange, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Scheduling_Application.class.getResource(pageChange));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        //System.out.println(Locale.getDefault().getLanguage());
        JDBC.makeConnection();

        launch(args);

        JDBC.closeConnection();
    }
}
