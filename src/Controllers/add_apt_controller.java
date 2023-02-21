package Controllers;

import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import Main.Scheduling_Application;
import Model.Appointments;
import Model.Contacts;
import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

public class add_apt_controller implements Initializable {
    public TextField titleField;
    public TextField locationField;
    public TextField typeField;
    public DatePicker startDateChoice;
    public DatePicker endDateChoice;
    public TextArea descriptionField;
    public Button saveAptBtn;
    public Button cancelBtn;
    public Label customerLbl;
    public ChoiceBox<LocalTime> startTimePicker;
    public ChoiceBox<LocalTime > endTimePicker;
    public TextField aptIdField;
    public ComboBox<Customers> customerDropdown;
    public ComboBox<Contacts> contactDropDown;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerDropdown.setItems(DBCustomers.allCustomers);
        startTimePicker.setItems(Appointments.zonedTime);
        endTimePicker.setItems(Appointments.zonedTime);
        contactDropDown.setItems(DBContacts.allContacts);
    }

    public void saveAppointment(ActionEvent actionEvent) throws IOException, SQLException {
        if (Appointments.checkDate(startDateChoice.getValue(), endDateChoice.getValue())) {
            Scheduling_Application.displayError("Start Date must not be be greater than End Date.");
            return;
        }
        else if (Appointments.checkTime(startDateChoice.getValue(), endDateChoice.getValue(), startTimePicker.getValue(), endTimePicker.getValue())) {
            Scheduling_Application.displayError("Start Time must not be greater than End Time on the same calendar day.");
            return;
        }
        //Add future date check

        Appointments newAppointment = new Appointments(
                0,
                titleField.getText(),
                descriptionField.getText(),
                locationField.getText(),
                typeField.getText(),
                LocalDateTime.of(startDateChoice.getValue(), startTimePicker.getValue()),
                LocalDateTime.of(endDateChoice.getValue(), endTimePicker.getValue()),
                customerDropdown.getValue().getCustomerId(),
                Scheduling_Application.activeUser.getUserId(),
                contactDropDown.getValue().getContactId()
        );
        DBAppointments.addApt(newAppointment);
        Scheduling_Application.changePage(actionEvent, "../JavaFXML/appointments_page.fxml", "Appointments");
    }

    public void cancelAddAppointment(ActionEvent actionEvent) throws IOException {
        Scheduling_Application.changePage(actionEvent, "../JavaFXML/appointments_page.fxml", "Appointments");
    }


}
