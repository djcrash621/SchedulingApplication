package Controllers;

import DBAccess.DBAppointments;
import DBAccess.DBCustomers;
import Main.Scheduling_Application;
import Model.Appointments;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

public class edit_apt_controller implements Initializable {
    public TextField titleField;
    public TextField locationField;
    public TextField typeField;
    public DatePicker startDateChoice;
    public DatePicker endDateChoice;
    public TextArea descriptionField;
    public Button saveAptBtn;
    public Button cancelBtn;
    private static Appointments passedInAppointment;
    public Label customerLbl;
    public ChoiceBox<LocalTime> startTimePicker;
    public ChoiceBox<LocalTime > endTimePicker;
    public TextField aptIdField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerLbl.setText("Customer: " + Objects.requireNonNull(DBCustomers.lookupCustomer(passedInAppointment.getCustomerId())).getCustomerName());
        aptIdField.setText(String.valueOf(passedInAppointment.getAppointmentId()));
        titleField.setText(passedInAppointment.getTitle());
        locationField.setText(passedInAppointment.getLocation());
        typeField.setText(passedInAppointment.getType());
        descriptionField.setText(passedInAppointment.getDescription());
        startTimePicker.setItems(Appointments.zonedTime);
        endTimePicker.setItems(Appointments.zonedTime);
        startTimePicker.setValue(passedInAppointment.getStart().toLocalTime());
        endTimePicker.setValue(passedInAppointment.getEnd().toLocalTime());
        startDateChoice.setValue(passedInAppointment.getStart().toLocalDate());
        endDateChoice.setValue(passedInAppointment.getEnd().toLocalDate());
    }

    public static void handOffAppointment(Appointments appointment) {
        passedInAppointment = appointment;
    }

    public void saveAppointment(ActionEvent actionEvent) throws IOException {
        if (Appointments.checkDate(startDateChoice.getValue(), endDateChoice.getValue())) {
            Scheduling_Application.displayError("Start Date must not be be greater than End Date.");
            return;
        }
        else if (Appointments.checkTime(startDateChoice.getValue(), endDateChoice.getValue(), startTimePicker.getValue(), endTimePicker.getValue())) {
            Scheduling_Application.displayError("Start Time must not be greater than End Time on the same calendar day.");
            return;
        }

        Appointments newAppointment = new Appointments(
                passedInAppointment.getAppointmentId(),
                titleField.getText(),
                descriptionField.getText(),
                locationField.getText(),
                typeField.getText(),
                LocalDateTime.of(startDateChoice.getValue(), startTimePicker.getValue()),
                LocalDateTime.of(endDateChoice.getValue(), endTimePicker.getValue()),
                passedInAppointment.getCustomerId(),
                passedInAppointment.getUserId(),
                passedInAppointment.getUserId()
                );
        DBAppointments.updateApt(newAppointment);
        Scheduling_Application.changePage(actionEvent, "../JavaFXML/appointments_page.fxml", "Appointments");
    }

    public void cancelAddAppointment(ActionEvent actionEvent) throws IOException {
        Scheduling_Application.changePage(actionEvent, "../JavaFXML/appointments_page.fxml", "Appointments");
    }


}
