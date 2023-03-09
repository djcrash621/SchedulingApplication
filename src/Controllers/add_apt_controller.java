package Controllers;

import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import Main.Scheduling_Application;
import Model.Appointments;
import Model.Contacts;
import Model.Customers;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;


/**
 * This class serves as the controller and defines the methods for the add appointment FXML page of the application.
 */
public class add_apt_controller implements Initializable {
    public TextField titleField;
    public TextField locationField;
    public TextField typeField;
    public DatePicker DateChoice;
    public TextArea descriptionField;
    public Button saveAptBtn;
    public Button cancelBtn;
    public Label customerLbl;
    public ChoiceBox<LocalTime> startTimePicker;
    public ChoiceBox<LocalTime > endTimePicker;
    public TextField aptIdField;
    public ComboBox<Customers> customerDropdown;
    public ComboBox<Contacts> contactDropDown;


    /**
     * Method runs on initialization of the page, setting the dropdowns and pickers to their respective Observable Lists.
     * @param url Unused parameter.
     * @param resourceBundle Unused parameter.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerDropdown.setItems(DBCustomers.allCustomers);
        startTimePicker.setItems(Appointments.zonedTime);
        endTimePicker.setItems(Appointments.zonedTime);
        contactDropDown.setItems(DBContacts.allContacts);
    }


    /**
     * Saves the appointment to the database, after checking all values as necessary, then returns to the appointments page.
     * @param actionEvent Action to run method.
     * @throws IOException Exception for page change.
     * @throws SQLException Exception for SQL command.
     */
    public void saveAppointment(ActionEvent actionEvent) throws IOException, SQLException {
        if (customerDropdown.getSelectionModel().isEmpty()) {
            Scheduling_Application.displayError("Customer must be selected.");
            return;
        }
        else if (titleField.getText().isBlank()) {
            Scheduling_Application.displayError("Title field must not be empty.");
            return;
        }
        else if (locationField.getText().isBlank()) {
            Scheduling_Application.displayError("Location field must not be empty.");
            return;
        }
        else if (typeField.getText().isBlank()) {
            Scheduling_Application.displayError("Type Field must not be empty.");
            return;
        }
        else if (descriptionField.getText().isBlank()) {
            Scheduling_Application.displayError("Description field must not be empty.");
            return;
        }
        else if (DateChoice.getValue() == null) {
            Scheduling_Application.displayError("Date Picker must be selected.");
            return;
        }
        else if (startTimePicker.getValue() == null) {
            Scheduling_Application.displayError("Start time must be selected.");
            return;
        }
        else if (endTimePicker.getValue() == null) {
            Scheduling_Application.displayError("End time must be selected.");
            return;
        }
        else if (contactDropDown.getValue() == null) {
            Scheduling_Application.displayError("Contact must be selected.");
            return;
        }
        if (Appointments.errorCheckDates(DateChoice.getValue(), startTimePicker.getValue(), endTimePicker.getValue(), customerDropdown.getValue())) {
            return;
        }

        Appointments newAppointment = new Appointments(
                0,
                titleField.getText(),
                descriptionField.getText(),
                locationField.getText(),
                typeField.getText(),
                LocalDateTime.of(DateChoice.getValue(), startTimePicker.getValue()),
                LocalDateTime.of(DateChoice.getValue(), endTimePicker.getValue()),
                customerDropdown.getValue().getCustomerId(),
                Scheduling_Application.activeUser.getUserId(),
                contactDropDown.getValue().getContactId()
        );
        DBAppointments.addApt(newAppointment);
        Scheduling_Application.changePage(actionEvent, "../JavaFXML/appointments_page.fxml", "Appointments");
    }


    /**
     * Method to cancel new appointment and return to appointment page.
     * @param actionEvent Action taken to run method.
     * @throws IOException Exception for page change.
     */
    public void cancelAddAppointment(ActionEvent actionEvent) throws IOException {
        Scheduling_Application.changePage(actionEvent, "../JavaFXML/appointments_page.fxml", "Appointments");
    }


}
