package Controllers;

import DBAccess.DBAppointments;
import DBAccess.DBCountries;
import DBAccess.DBCustomers;
import DBAccess.DBDivisions;
import Main.Scheduling_Application;
import Model.Appointments;
import Model.Countries;
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

/**
 * This class defines the methods and serves as the controller for the welcome/customer page.
 */
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
    public ComboBox<Countries> CountryDropdown;

    /**
     * This method initializes the page, populating the table with all the customer data.
     * The lambda function converts the division id value for each row to the matchingdivision name.
     * @param url Unused parameter.
     * @param resourceBundle Unused parameter.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boolean isApt = false;
        welcomeLbl.setText("Welcome: " + Scheduling_Application.activeUser.getUserName());
        divisionDropdown.setItems(DBDivisions.allDivisions);
        CountryDropdown.setItems(DBCountries.countryList);
        customerTbl.setItems(DBCustomers.getAllCustomers());
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

    /**
     * This method logs the user out of the application, going back to the login poge. Collects the resource bundle
     * information to name the login page in the current language. Prints to login_activity log that the user signed out.
     * @param actionEvent Action taken to trigger method.
     * @throws IOException Exception thrown from page change.
     */
    public void sign_out(ActionEvent actionEvent) throws IOException {
        ResourceBundle rb = ResourceBundle.getBundle(Scheduling_Application.rbLocation, Locale.getDefault());
        Scheduling_Application.changePage(actionEvent, "../JavaFXML/login_page.fxml", rb.getString("Login") + " " + rb.getString("Page"));
        login_controller.output.println(LocalDateTime.now() + ": USER SIGNED OUT");
    }

    /**
     * Control for the text field search input. If field is not null, searches the customers lists for the customer with the
     * matching name or ID.
     * @param actionEvent Action taken to trigger method.
     */
    public void searchCustomers(ActionEvent actionEvent) {
        String searchVal = customerSearch.getText();
        ObservableList<Customers> searchResult = FXCollections.observableArrayList();

        if (Objects.equals(searchVal, "")) {
            customerTbl.setItems(DBCustomers.lookupCustomer(searchVal));
            return;
        }
        try {
            searchResult.add(DBCustomers.lookupCustomer(Integer.parseInt(searchVal)));
        } catch (Exception noInt){
            searchResult = DBCustomers.lookupCustomer(searchVal);
        }
        customerTbl.setItems(searchResult);
    }

    /**
     * This method changes the scene from the current page to the add customer page.
     * @param actionEvent Action taken to trigger method.
     * @throws IOException Exception thrown for page change.
     */
    public void addCustomer(ActionEvent actionEvent) throws IOException {
        Scheduling_Application.changePage(actionEvent, "../JavaFXML/add_customer_page.fxml", "Add Customer Form");

    }

    /**
     * This method takes the value selected in the table, (or returns if null) and passes the value to the edit page
     * as it initialized.
     * @param actionEvent Action taken to trigger method.
     * @throws IOException Exception thrown by page change.
     */
    public void editCustomer(ActionEvent actionEvent) throws IOException {
        Customers selected = customerTbl.getSelectionModel().getSelectedItem();
        if (selected == null) Scheduling_Application.displayError("No user selected.");
        edit_customer_controller.customerToModify(selected);
        Scheduling_Application.changePage(actionEvent, "../JavaFXML/edit_customer_page.fxml", "Edit Customer Form");
    }

    /**
     * This method changes the scene from the current page to the appointments page.
     * @param actionEvent Action taken to trigger method.
     * @throws IOException Exception thrown by page change.
     */
    public void viewAppointments(ActionEvent actionEvent) throws IOException {
        Scheduling_Application.changePage(actionEvent, "../JavaFXML/appointments_page.fxml", "Appointments Page");
    }

    /**
     * This method filters the table by the selected division dropdown.
     * @param actionEvent Action taken to trigger method.
     */
    public void filterByDivisions(ActionEvent actionEvent) {
        ObservableList<Customers> filterCustomers = FXCollections.observableArrayList();

        for (Customers c: DBCustomers.getAllCustomers()) {
            if (c.getDivisionId() == divisionDropdown.getValue().getDivisionId()) {
                filterCustomers.add(c);
            }
        }
        customerTbl.setItems(filterCustomers);
    }

    /**
     * This method resets all search and dropdown UI elements and repopulates the table with
     * the all customers observable list.
     * @param actionEvent Action taken to trigger event.
     */
    public void resetTable(ActionEvent actionEvent) {
        customerTbl.setItems(DBCustomers.getAllCustomers());
        customerSearch.setText("");
        divisionDropdown.getSelectionModel().clearSelection();
        CountryDropdown.getSelectionModel().clearSelection();
        divisionDropdown.setItems(DBDivisions.allDivisions);
    }

    /**
     * This method deletes the selected customer from the database and local observable list, or returns
     * if null.
     * @param actionEvent Action taken to trigger method.
     */
    public void deleteCustomer(ActionEvent actionEvent) {
        Customers SA = customerTbl.getSelectionModel().getSelectedItem();
        DBCustomers.deleteCustomer(SA);
        customerTbl.setItems(DBCustomers.getAllCustomers());
        Alert deleted = new Alert(Alert.AlertType.INFORMATION, SA.getCustomerName() + " deleted from records.", ButtonType.OK);
        deleted.showAndWait();
    }

    /**
     * This method filters the values in the division dropdown, and the customers in the table,
     * by the value selected in the country dropdown based on whether the division if found in the given
     * country value.
     * @param actionEvent Action taken to trigger method.
     */
    public void filterByCountry(ActionEvent actionEvent) {
        divisionDropdown.getSelectionModel().clearSelection();

        ObservableList<Divisions> divisionsInCountry = FXCollections.observableArrayList();
        ObservableList<Customers> customersInCountry  = FXCollections.observableArrayList();
        for (Divisions d: DBDivisions.allDivisions) {
            if (d.getCountryId() == CountryDropdown.getValue().getCountryId()) {
                divisionsInCountry.add(d);
            }
        }

        divisionDropdown.setItems(divisionsInCountry);

        for (Customers c: DBCustomers.getAllCustomers()) {
            for (Divisions d : divisionsInCountry) {
                if (c.getDivisionId() == d.getDivisionId()) {
                    customersInCountry.add(c);
                }
            }
        }
        customerTbl.setItems(customersInCountry);

    }
}
