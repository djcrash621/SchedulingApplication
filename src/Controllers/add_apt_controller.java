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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerDropdown.setItems(DBCustomers.allCustomers);
        startTimePicker.setItems(Appointments.zonedTime);
        endTimePicker.setItems(Appointments.zonedTime);
        contactDropDown.setItems(DBContacts.allContacts);
    }

    public void saveAppointment(ActionEvent actionEvent) throws IOException, SQLException {
        if (Appointments.errorCheckDates(DateChoice.getValue(), startTimePicker.getValue(), endTimePicker.getValue())) {
            return;
        }
        for (Appointments a : DBAppointments.allAppointments) {
            if (a.getCustomerId() == customerDropdown.getValue().getCustomerId()) {
                if (DateChoice.getValue().equals(a.getStart().toLocalDate())){
                    if (startTimePicker.getValue().isAfter(a.getStart().toLocalTime()) && endTimePicker.getValue().isBefore(a.getEnd().toLocalTime()) ||
                    endTimePicker.getValue().isAfter(a.getStart().toLocalTime()) && endTimePicker.getValue().isBefore(a.getEnd().toLocalTime()) ||
                    startTimePicker.getValue().isAfter(a.getStart().toLocalTime()) && startTimePicker.getValue().isBefore(a.getEnd().toLocalTime())) {
                        Scheduling_Application.displayError("Customer already has existing appointment during " + startTimePicker.getValue().toString() + " and " + endTimePicker.getValue().toString() + " time window.");
                        return;
                    }
                }
            }
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

    public void cancelAddAppointment(ActionEvent actionEvent) throws IOException {
        Scheduling_Application.changePage(actionEvent, "../JavaFXML/appointments_page.fxml", "Appointments");
    }


}
