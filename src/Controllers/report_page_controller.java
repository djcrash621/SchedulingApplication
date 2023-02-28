package Controllers;

import DBAccess.*;
import Main.Scheduling_Application;
import Model.Appointments;
import Model.Contacts;
import Model.Users;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;

public class report_page_controller implements Initializable {

    public Button backBtn;
    public AnchorPane pageWindow;
    public Tab typeMonthAptsTab;
    public ComboBox<String> aptTypeDropdown;
    public ComboBox<String> aptMonthDropdown;
    public Label countLbl;
    public TableView<Appointments> typeMonthTbl;
    public TableColumn<Appointments, Integer> typeMonthAptIdCol;
    public TableColumn<Appointments, String> typeMonthTitleCol;
    public TableColumn<Appointments, String> typeMonthDescriptionCol;
    public TableColumn<Appointments, String> typeMonthLocationCol;
    public TableColumn<Appointments, DateTimeFormatter> typeMonthStartCol;
    public TableColumn<Appointments, DateTimeFormatter> typeMonthEndCol;
    public TableColumn<Appointments, String> typeMonthCustomerNameCol;
    public Tab contactScheduleTab;
    public ComboBox<Contacts> contactDropdown;
    public Label aptCountLbl;
    public TableView<Appointments> userTbl;
    public TableColumn<Appointments, Integer> contactAptIdCol;
    public TableColumn<Appointments, String> contactTitleCol;
    public TableColumn<Appointments, String> contactDescriptionCol;
    public TableColumn<Appointments, String> contactLocationCol;
    public TableColumn<Appointments, DateTimeFormatter> contactStartCol;
    public TableColumn<Appointments, DateTimeFormatter> contactEndCol;
    public TableColumn<Appointments, String> contactCustomerNameCol;
    public Tab userScheduleTab;
    public ComboBox<Users> userDropdown;
    public Label userAptCountLbl;
    public TableView<Appointments> contactTbl;
    public TableColumn<Appointments, Integer> userAptIdCol;
    public TableColumn<Appointments, String> userTitleCol;
    public TableColumn<Appointments, String> userDescriptionCol;
    public TableColumn<Appointments, String> userLocationCol;
    public TableColumn<Appointments, DateTimeFormatter> userStartCol;
    public TableColumn<Appointments, DateTimeFormatter> userEndCol;
    public TableColumn<Appointments, String> userCustomerNameCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            aptTypeDropdown.setItems(DBReporting.getTypes());
            aptMonthDropdown.setItems(DBReporting.getMonths());
            contactDropdown.setItems(DBContacts.allContacts);
            userDropdown.setItems(DBUsers.allUsers);
            typeMonthAptIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
            typeMonthTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            typeMonthDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            typeMonthLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
            typeMonthStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
            typeMonthEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
            typeMonthCustomerNameCol.setCellValueFactory(cellData -> {
                Appointments format = cellData.getValue();
                return new SimpleStringProperty(Objects.requireNonNull(DBCustomers.lookupCustomer(format.getCustomerId())).getCustomerName());
            });
            contactAptIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
            contactTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            contactDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            contactLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
            contactStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
            contactEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
            contactCustomerNameCol.setCellValueFactory(cellData -> {
                Appointments format = cellData.getValue();
                return new SimpleStringProperty(Objects.requireNonNull(DBCustomers.lookupCustomer(format.getCustomerId())).getCustomerName());
            });
            userAptIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
            userTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            userDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            userLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
            userStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
            userEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
            userCustomerNameCol.setCellValueFactory(cellData -> {
                Appointments format = cellData.getValue();
                return new SimpleStringProperty(Objects.requireNonNull(DBCustomers.lookupCustomer(format.getCustomerId())).getCustomerName());
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void returnToWelcome(ActionEvent actionEvent) throws IOException {
        Scheduling_Application.changePage(actionEvent, "../JavaFXML/appointments_page.fxml", "Appointments");
    }

    public void typeMonthFilter(ActionEvent actionEvent) {
        if (aptMonthDropdown.getSelectionModel().isEmpty() || aptTypeDropdown.getSelectionModel().isEmpty()) {
            return;
        }
        ObservableList<Appointments> reported = DBReporting.typeMonthApts(aptTypeDropdown.getValue(), aptMonthDropdown.getValue());
        typeMonthTbl.setItems(reported);
        countLbl.setText("Count: " + reported.size());

    }

    public void contactFilter(ActionEvent actionEvent) {
        ObservableList<Appointments> contactsAppointments = DBReporting.getContactApts(contactDropdown.getSelectionModel().getSelectedItem());
        contactTbl.setItems(contactsAppointments);
        contactTbl.getSortOrder().add(contactStartCol);
        aptCountLbl.setText("Count: " + contactsAppointments.size());
    }

    public void userFilter(ActionEvent actionEvent) {
        ObservableList<Appointments> userAppointments = DBReporting.getUserApts(userDropdown.getSelectionModel().getSelectedItem());
        userTbl.setItems(userAppointments);
        userTbl.getSortOrder().add(userStartCol);
        userAptCountLbl.setText("Count: " + userAppointments.size());
    }
}



