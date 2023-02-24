package Controllers;

import DBAccess.DBAppointments;
import Main.Scheduling_Application;
import Model.Appointments;
import Model.Contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class report_page_controller implements Initializable {

    public Button backBtn;
    public AnchorPane pageWindow;
    public Tab typeMonthAptsTab;
    public TableView<Appointments> typeMonthAptsTbl;
    public TableColumn AptTypeCol;
    public TableColumn aptMonthCol;
    public TableColumn countCol;
    public Tab contactScheduleTab;
    public TreeTableView<Appointments> contactsTree;
    public TreeTableColumn aptIdCol;
    public TreeTableColumn titleCol;
    public TreeTableColumn typeCol;
    public TreeTableColumn descriptionCol;
    public TreeTableColumn startCol;
    public TreeTableColumn endCol;
    public TreeTableColumn customerIdCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        typeMonthAptsTbl.setItems();



    }

    public void returnToWelcome(ActionEvent actionEvent) throws IOException {
        Scheduling_Application.changePage(actionEvent,"../JavaFXML/welcome_page.fxml", "Welcome Page");
    }

}



