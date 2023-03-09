package Controllers;

import DBAccess.DBAppointments;
import Main.Scheduling_Application;
import Model.Appointments;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * This class controls the main Appointments page in the application, and defines its methods
 */
public class appointments_page_controller implements Initializable {
    public TableView<Appointments> monthlyAptTbl;
    public Tab weeklyAptTable;
    public Button addAptBtn;
    public Button backBtn;
    public Button deleteAptBtn;
    public Button editAptBtn;
    public TableView<Appointments> weeklyAptTbl;
    public TableView<Appointments> allAptTbl;
    public TableColumn<Appointments, Integer> allAptId;
    public TableColumn<Appointments, String> allAptTitle;
    public TableColumn<Appointments, String> allAptDescription;
    public TableColumn<Appointments, String> allAptLocation;
    public TableColumn<Appointments, Integer> allAptContactId;
    public TableColumn<Appointments, String> allAptType;
    public TableColumn<Appointments, DateTimeFormatter> allAptStart;
    public TableColumn<Appointments, DateTimeFormatter> allAptEnd;
    public TableColumn<Appointments, Integer> allAptCustomerId;
    public TableColumn<Appointments, Integer> allAptUserId;
    public TableColumn<Appointments, Integer> monthlyAptId;
    public TableColumn<Appointments, String> monthlyTitle;
    public TableColumn<Appointments, String> monthlyDescription;
    public TableColumn<Appointments, String> monthlyLocation;
    public TableColumn<Appointments, Integer> monthlyContactId;
    public TableColumn<Appointments, String> monthlyType;
    public TableColumn<Appointments, DateTimeFormatter> monthlyStart;
    public TableColumn<Appointments, DateTimeFormatter> monthlyEnd;
    public TableColumn<Appointments, Integer> monthlyCustomerId;
    public TableColumn<Appointments, Integer> monthlyUserId;
    public TableColumn<Appointments, Integer> weeklyAptId;
    public TableColumn<Appointments, String> weeklyTitle;
    public TableColumn<Appointments, String> weeklyDescription;
    public TableColumn<Appointments, String> weeklyLocation;
    public TableColumn<Appointments, DateTimeFormatter> weeklyContactId;
    public TableColumn<Appointments, String> weeklyType;
    public TableColumn<Appointments, DateTimeFormatter> weeklyStart;
    public TableColumn<Appointments, DateTimeFormatter> weeklyEnd;
    public TableColumn<Appointments, Integer> weeklyCustomerId;
    public TableColumn<Appointments, Integer> weeklyUserId;
    public AnchorPane pageWindow;
    public Tab allAptsTab;
    public Tab monthAptTab;
    public Tab weeklyAptTab;
    public Button reportingBtn;

    /**
     * This method initializes the Appointment page, populating the tab tables and their respective columns with the appropriate observable lists and subsequent appointment member values.
     * @param url Unused parameter.
     * @param resourceBundle Unused parameter.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allAptId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        allAptTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        allAptDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        allAptLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        allAptContactId.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        allAptType.setCellValueFactory(new PropertyValueFactory<>("type"));
        allAptStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        allAptEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        allAptCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        allAptUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        weeklyAptId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        weeklyTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        weeklyDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        weeklyLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        weeklyContactId.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        weeklyType.setCellValueFactory(new PropertyValueFactory<>("type"));
        weeklyStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        weeklyEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        weeklyCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        weeklyUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        monthlyAptId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        monthlyTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        monthlyDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        monthlyLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        monthlyContactId.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        monthlyType.setCellValueFactory(new PropertyValueFactory<>("type"));
        monthlyStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        monthlyEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        monthlyCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        monthlyUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }

    /**
     * Method that changes scene from the current page to the Add Appointment Page.
     * @param actionEvent Action Taken to trigger method.
     * @throws IOException Exception from Page Change.
     */
    public void addAppointment(ActionEvent actionEvent) throws IOException {
        Scheduling_Application.changePage(actionEvent, "../JavaFXML/add_apt_page.fxml", "New Appointment Page");
    }

    /**
     * Method that changes scene from current page to the Welcome Page.
     * @param actionEvent Action taken to trigger method.
     * @throws IOException Exception from Page Change.
     */
    public void returnToWelcome(ActionEvent actionEvent) throws IOException {
        Scheduling_Application.changePage(actionEvent,"../JavaFXML/welcome_page.fxml", "Welcome Page");
    }

    /**
     * This method deletes the selected appointment from any of the three tables, if selected; otherwise the method returns.
     * @param actionEvent Action taken to trigger method.
     */
    public void deleteAppointment(ActionEvent actionEvent) {
        Appointments SA = null;
        try {
            if (allAptsTab.isSelected()) {
                SA = allAptTbl.getSelectionModel().getSelectedItem();
                if (SA == null) {
                    throw new Exception("No Appointment Selected.") ;
                }
                DBAppointments.deleteApt(SA.getAppointmentId());
                loadAllApts(actionEvent);
                Alert deleted = new Alert(Alert.AlertType.INFORMATION, "Appointment: " + SA.getAppointmentId() + " of Type " + SA.getType() + " cancelled.", ButtonType.OK);
                deleted.showAndWait();
            } else if (monthAptTab.isSelected()) {
                SA = monthlyAptTbl.getSelectionModel().getSelectedItem();
                if (SA == null) {
                    throw new Exception("No Appointment Selected.") ;
                }
                DBAppointments.deleteApt(SA.getAppointmentId());
                loadMonthlyApts(actionEvent);
                Alert deleted = new Alert(Alert.AlertType.INFORMATION, "Appointment: " + SA.getAppointmentId() + " of Type " + SA.getType() + " cancelled.", ButtonType.OK);
                deleted.showAndWait();
            } else if (weeklyAptTab.isSelected()) {
                SA = weeklyAptTbl.getSelectionModel().getSelectedItem();
                if (SA == null) {
                    throw new Exception("No Appointment Selected.") ;
                }
                DBAppointments.deleteApt(SA.getAppointmentId());
                loadWeeklyApts(actionEvent);
                Alert deleted = new Alert(Alert.AlertType.INFORMATION, "Appointment: " + SA.getAppointmentId() + " of Type " + SA.getType() + " cancelled.", ButtonType.OK);
                deleted.showAndWait();
            }
        } catch (Exception e){
            Scheduling_Application.displayError(e.getMessage());
        }
    }

    /**
     * This method will get the selection from any of the three appointment table, then will pass the value to the edit page as it changes the scene to the edit page.
     * @param actionEvent Action taken to trigger method.
     * @throws IOException Exception for page change.
     */
    public void editAppointment(ActionEvent actionEvent) throws IOException {
        Appointments SA = null;
        try {
            if (allAptsTab.isSelected()) {
                SA = allAptTbl.getSelectionModel().getSelectedItem();
                if (SA == null) {
                    throw new Exception("No Appointment Selected.") ;
                }
            } else if (monthAptTab.isSelected()) {
                SA = monthlyAptTbl.getSelectionModel().getSelectedItem();
                if (SA == null) {
                    throw new Exception("No Appointment Selected.") ;
                }
            } else if (weeklyAptTab.isSelected()) {
                SA = weeklyAptTbl.getSelectionModel().getSelectedItem();
                if (SA == null) {
                    throw new Exception("No Appointment Selected.") ;
                }
            }
        } catch (Exception e){
            Scheduling_Application.displayError(e.getMessage());
            return;
        }
        assert SA != null;
        edit_apt_controller.handOffAppointment(SA);
        Scheduling_Application.changePage(actionEvent, "../JavaFXML/edit_apt_page.fxml", "Edit Appointment Page");
    }


    /**
     * This method will change the scene to the Report Page of the application.
     * @param actionEvent Action taken to trigger method.
     * @throws IOException Exception for page change.
     */
    public void goToReporting(ActionEvent actionEvent) throws IOException {
        Scheduling_Application.changePage(actionEvent, "../JavaFXML/report_page.fxml", "Reports");
    }

    public void loadMonthlyApts(Event event) {
        monthlyAptTbl.setItems(DBAppointments.getMonthlyAppointments());
    }

    public void loadWeeklyApts(Event event) {
        weeklyAptTbl.setItems(DBAppointments.getWeeklyAppointments());
    }

    public void loadAllApts(Event event) {
        allAptTbl.setItems(DBAppointments.getAllAppointments());

    }
}
