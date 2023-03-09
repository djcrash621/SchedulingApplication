package Controllers;

import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import Main.Scheduling_Application;
import Model.Appointments;
import Model.Contacts;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Class defines controls and defines the methods for the edit appointment page of the application.
 */
public class edit_apt_controller implements Initializable {
    public TextField titleField;
    public TextField locationField;
    public TextField typeField;
    public DatePicker DateChoice;
    public TextArea descriptionField;
    public Button saveAptBtn;
    public Button cancelBtn;
    private static Appointments passedInAppointment;
    public Label customerLbl;
    public ChoiceBox<LocalTime> startTimePicker;
    public ChoiceBox<LocalTime > endTimePicker;
    public TextField aptIdField;
    public ComboBox<Contacts> contactDropDown;


    /**
     * Method run on initial page initialization, populating the cells with the data from the given appointment being editted.
     * @param url Unused Parameter
     * @param resourceBundle Unused Parameter
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            customerLbl.setText("Customer: " + Objects.requireNonNull(DBCustomers.lookupCustomer(passedInAppointment.getCustomerId())).getCustomerName());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        aptIdField.setText(String.valueOf(passedInAppointment.getAppointmentId()));
        titleField.setText(passedInAppointment.getTitle());
        locationField.setText(passedInAppointment.getLocation());
        typeField.setText(passedInAppointment.getType());
        descriptionField.setText(passedInAppointment.getDescription());
        startTimePicker.setItems(Appointments.zonedTime);
        endTimePicker.setItems(Appointments.zonedTime);
        startTimePicker.setValue(passedInAppointment.getStart().toLocalTime());
        endTimePicker.setValue(passedInAppointment.getEnd().toLocalTime());
        DateChoice.setValue(passedInAppointment.getStart().toLocalDate());
        contactDropDown.setItems(DBContacts.allContacts);
        contactDropDown.setValue(DBContacts.findContact(passedInAppointment.getContactId()));
    }


    /**
     * Function used by other controllers handing off the appointment to be edited in this page.
     * @param appointment Appointment to be edited, handed off from the appointment page.
     */
    public static void handOffAppointment(Appointments appointment) {
        passedInAppointment = appointment;
    }


    /**
     * Saves the edited appointment to the table after first verifying the inputted values. Returns to appointment page on valid completion.
     * @param actionEvent Action taken to run method.
     * @throws IOException Exception for page change.
     * @throws SQLException Exception for database commands.
     */
    public void saveAppointment(ActionEvent actionEvent) throws IOException, SQLException {
        if (titleField.getText().isBlank()) {
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
        if (Appointments.errorCheckDates(DateChoice.getValue(), startTimePicker.getValue(), endTimePicker.getValue(), DBCustomers.lookupCustomer(passedInAppointment.getCustomerId()))) {
            return;
        }

        Appointments newAppointment = new Appointments(
                passedInAppointment.getAppointmentId(),
                titleField.getText(),
                descriptionField.getText(),
                locationField.getText(),
                typeField.getText(),
                LocalDateTime.of(DateChoice.getValue(), startTimePicker.getValue()),
                LocalDateTime.of(DateChoice.getValue(), endTimePicker.getValue()),
                passedInAppointment.getCustomerId(),
                passedInAppointment.getUserId(),
                passedInAppointment.getUserId()
                );

        DBAppointments.updateApt(newAppointment);
        Scheduling_Application.changePage(actionEvent, "../JavaFXML/appointments_page.fxml", "Appointments");
    }

    /**
     * Method to cancel editing of appointment and return to appointment page.
     * @param actionEvent Action taken to run method.
     * @throws IOException Exception for page change.
     */
    public void cancelAddAppointment(ActionEvent actionEvent) throws IOException {
        Scheduling_Application.changePage(actionEvent, "../JavaFXML/appointments_page.fxml", "Appointments");
    }


}
