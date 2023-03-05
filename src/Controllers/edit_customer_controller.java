package Controllers;

import DBAccess.DBAppointments;
import DBAccess.DBCountries;
import DBAccess.DBCustomers;
import DBAccess.DBDivisions;
import Main.Scheduling_Application;
import Model.Countries;
import Model.Customers;
import Model.Divisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class defines the methods and serves as the controller for the edit customer page of the application.
 */
public class edit_customer_controller implements Initializable {
    private static Customers passedInCustomer;
    public TextField customerNameField;
    public TextField addressField;
    public TextField postalCodeField;
    public TextField phoneNumField;
    public Button saveNewCustomer;
    public Button cancelBtn;
    public ComboBox<Divisions> divisionComboBox;
    public ComboBox<Countries> countryComboBox;
    public TextField custIdField;

    /**
     * This method initializes the page, populating the combo box with the country and division observable lists,
     * and populates the text fields with the values from the passed in customer.
     * @param url Unused parameter.
     * @param resourceBundle Unused parameter.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        custIdField.setText(String.valueOf(passedInCustomer.getCustomerId()));
        customerNameField.setText(passedInCustomer.getCustomerName());
        addressField.setText(passedInCustomer.getAddress());
        postalCodeField.setText(passedInCustomer.getPostalCode());
        phoneNumField.setText(passedInCustomer.getPhoneNum());
        divisionComboBox.setItems(DBDivisions.allDivisions);
        divisionComboBox.setValue(DBDivisions.getDivision(passedInCustomer.getDivisionId()));
        countryComboBox.setItems(DBCountries.countryList);
        countryComboBox.setValue(DBCountries.getCountry(DBDivisions.getDivision(passedInCustomer.getDivisionId())));
    }

    /**
     * Method that saves the updated customer to the database and local observable list.
     * @param actionEvent Action taken to trigger method.
     * @throws IOException Exception thrown from page change.
     */
    public void saveCustomer(ActionEvent actionEvent) throws IOException {
        if (customerNameField.getText().isBlank()) {
            Scheduling_Application.displayError("Customer Name must not be blank.");
            return;
        }
        else if (addressField.getText().isBlank()) {
            Scheduling_Application.displayError("Address must not be be blank.");
            return;
        }
        else if (postalCodeField.getText().isBlank()) {
            Scheduling_Application.displayError("Postal code must not be blank");
            return;
        }
        else if (phoneNumField.getText().isBlank()) {
            Scheduling_Application.displayError("Phone number must not be blank");
            return;
        }
        else if (countryComboBox.getSelectionModel().isEmpty()) {
            Scheduling_Application.displayError("Country must be selected.");
            return;
        }
        else if (divisionComboBox.getSelectionModel().isEmpty()) {
            Scheduling_Application.displayError("Division must be selected.");
            return;
        }

        DBCustomers.allCustomers.removeAll(passedInCustomer);
        DBCustomers.updateCustomer(new Customers(passedInCustomer.getCustomerId(), customerNameField.getText(), addressField.getText(), postalCodeField.getText(), phoneNumField.getText(), divisionComboBox.getSelectionModel().getSelectedItem().getDivisionId()));
        Scheduling_Application.changePage(actionEvent,"../JavaFXML/welcome_page.fxml", "Welcome Page");
    }

    /**
     * Method that changes the scene from the current scene to the welcome page.
     * @param actionEvent Action taken to trigger method.
     * @throws IOException Exception thrown from page change.
     */
    public void returnToWelcome(ActionEvent actionEvent) throws IOException {
        Scheduling_Application.changePage(actionEvent,"../JavaFXML/welcome_page.fxml", "Welcome Page");
    }

    /**
     * Method used by other pages to pass customers into this page to be edited.
     * @param customer Customer passed in from the customer page.
     */
    public static void customerToModify(Customers customer) {
        passedInCustomer = customer;
    }

    /**
     * This method runs when a value in the country dropdown is selected, and filters the values in the division dropdown to the values
     * matching within the country values.
     * @param actionEvent Action taken to trigger method.
     */
    public void filterByCountry(ActionEvent actionEvent) {
        ObservableList<Divisions> divisionsInCountry = FXCollections.observableArrayList();
        for (Divisions d: DBDivisions.allDivisions) {
            if (d.getCountryId() == countryComboBox.getValue().getCountryId()) {
                divisionsInCountry.add(d);
            }
        }
        divisionComboBox.setItems(divisionsInCountry);
    }

}
