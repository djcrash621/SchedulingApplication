package Controllers;

import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import DBAccess.DBUsers;
import Main.Scheduling_Application;
import Model.Appointments;
import Model.Contacts;
import Model.Users;
import Utilities.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
    public TableColumn<Appointments, Integer> typeMonthCustomerIdCol;
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
    public TableColumn<Appointments, Integer> contactCustomerIdCol;
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
    public TableColumn<Appointments, Integer> userCustomerIdCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            aptTypeDropdown.setItems(DBAppointments.getTypes());
            aptMonthDropdown.setItems(DBAppointments.getMonths());
            contactDropdown.setItems(DBContacts.allContacts);
            userDropdown.setItems(DBUsers.allUsers);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void returnToWelcome(ActionEvent actionEvent) throws IOException {
        Scheduling_Application.changePage(actionEvent, "../JavaFXML/appointments_page.fxml", "Appointments");
    }

    public void typeMonthFilter(ActionEvent actionEvent) {
        if (aptMonthDropdown.getSelectionModel().isEmpty() || aptTypeDropdown.getSelectionModel().isEmpty()) { }
        else {
            typeMonthTbl.setItems(DBAppointments.typeMonthApts(aptTypeDropdown.getValue(), aptMonthDropdown.getValue()));
        }
    }
}



