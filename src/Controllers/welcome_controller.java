package Controllers;

import DBAccess.DBAppointments;
import DBAccess.DBCustomers;
import DBAccess.DBDivisions;
import Main.Scheduling_Application;
import Model.Appointments;
import Model.Customers;
import Model.Divisions;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class welcome_controller implements Initializable {
    public Button sign_out_btn;
    public TextField customerSearch;
    public Button edit_customer_btn;
    public TableView<Customers> customerTbl;
    public TableColumn<Customers, String> customerNameCol;
    public TableColumn<Customers, String> phoneNumCol;
    public TableColumn<Customers, String> addressCol;
    public TableColumn<Customers, String> postCodeCol;
    public ComboBox<Divisions> divisionDropdown;
    public TableColumn<Customers, String> divisionCol;
    public Label welcomeLbl;
    public Button view_Apt_Lbl;
    public Button add_customer_btn;
    public Button resetTableBtn;
    public Button deleteCustomerBtn;
    public TableColumn<Customers, Integer> customerIdCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boolean isApt = false;
        welcomeLbl.setText("Welcome: " + Scheduling_Application.activeUser.getUserName());
        divisionDropdown.setItems(DBDivisions.getAllDivisions());
        customerTbl.setItems(DBCustomers.allCustomers);
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        phoneNumCol.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        postCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        divisionCol.setCellValueFactory(
                cellData -> {
                    Customers format = cellData.getValue();
                    return new SimpleStringProperty(Objects.requireNonNull(DBDivisions.getDivision(format.getDivisionId())).getDivision());
                });
    }

    public void sign_out(ActionEvent actionEvent) throws IOException {
        ResourceBundle rb = ResourceBundle.getBundle(Scheduling_Application.rbLocation, Locale.getDefault());
        Scheduling_Application.changePage(actionEvent, "../JavaFXML/login_page.fxml", rb.getString("Login") + " " + rb.getString("Page"));
    }

    public void searchCustomers(ActionEvent actionEvent) {
        String searchVal = customerSearch.getText();
        ObservableList<Customers> searchResult = FXCollections.observableArrayList();

        if (Objects.equals(searchVal, "")) {
            customerTbl.setItems(DBCustomers.allCustomers);
            return;
        }
        try {
            searchResult.add(DBCustomers.lookupCustomer(Integer.parseInt(searchVal)));
        } catch (Exception noInt){
            searchResult = DBCustomers.lookupCustomer(searchVal);
        }
        customerTbl.setItems(searchResult);
    }

    public void addCustomer(ActionEvent actionEvent) throws IOException {
        Scheduling_Application.changePage(actionEvent, "../JavaFXML/add_customer_page.fxml", "Add Customer Form");

    }

    public void editCustomer(ActionEvent actionEvent) throws IOException {
        Customers selected = customerTbl.getSelectionModel().getSelectedItem();
        if (selected == null) Scheduling_Application.displayError("No user selected.");
        edit_customer_controller.customerToModify(selected);
        Scheduling_Application.changePage(actionEvent, "../JavaFXML/edit_customer_page.fxml", "Edit Customer Form");
    }

    public void viewAppointments(ActionEvent actionEvent) throws IOException {
        Scheduling_Application.changePage(actionEvent, "../JavaFXML/appointments_page.fxml", "Edit Customer Page");
    }

    public void filterTable(ActionEvent actionEvent) {
        ObservableList<Customers> filterCustomers = FXCollections.observableArrayList();

        for (Customers c: DBCustomers.allCustomers) {
            if (c.getDivisionId() == divisionDropdown.getValue().getDivisionId()) {
                filterCustomers.add(c);
            }
        }
        customerTbl.setItems(filterCustomers);
    }

    public void resetTable(ActionEvent actionEvent) {
        customerTbl.setItems(DBCustomers.allCustomers);
        customerSearch.setText("");
        divisionDropdown.getSelectionModel().clearSelection();
    }

    public void deleteCustomer(ActionEvent actionEvent) {
        DBCustomers.deleteCustomer(customerTbl.getSelectionModel().getSelectedItem());
        customerTbl.setItems(DBCustomers.allCustomers);
    }

}
