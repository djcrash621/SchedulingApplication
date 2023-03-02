package Controllers;

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

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class is the controller for the add_customer FXML Page of the Application, also providing definitions for member methods.
 */
public class add_customer_controller implements Initializable {
    public TextField customerNameField;
    public TextField addressField;
    public TextField postalCodeField;
    public TextField phoneNumField;
    public Button saveNewCustomer;
    public Button cancelBtn;
    public ComboBox<Divisions> divisionComboBox;
    public ComboBox<Countries> countryComboBox;

    /**
     * This method initializes the page and populates the two combo boxes with their corresponding Observable Lists.
     * @param url Unused Parameter.
     * @param resourceBundle Unused Parameter.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //divisionComboBox.setItems(DBDivisions.allDivisions);
        countryComboBox.setItems(DBCountries.countryList);
    }

    /**
     * This method save's the added customer to the database, after first checking for input errors.
     * @param actionEvent Action Taken to trigger method.
     * @throws IOException Exception for page change.
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
        DBCustomers.addCustomer(new Customers(0, customerNameField.getText(), addressField.getText(), postalCodeField.getText(), phoneNumField.getText(), divisionComboBox.getSelectionModel().getSelectedItem().getDivisionId()));
        Scheduling_Application.changePage(actionEvent, "../JavaFXML/welcome_page.fxml", "Edit Customer Form");
    }

    /**
     * This method changes the scene to the Application Welcome/Customer Page.
     * @param actionEvent Action Taken to trigger method.
     * @throws IOException Exception for page change.
     */
    public void returnToWelcome(ActionEvent actionEvent) throws IOException {
        Scheduling_Application.changePage(actionEvent,"../JavaFXML/welcome_page.fxml", "Edit Customer Form");
    }

    /**
     * This method filters the Division ComboBox options by the Country Selected with the Country Dropdown.
     * @param actionEvent Action Taken to trigger method.
     */
    public void filterByCountry(ActionEvent actionEvent) {
        //divisionComboBox.getSelectionModel().clearSelection();
        ObservableList<Divisions> divisionsInCountry = FXCollections.observableArrayList();
        for (Divisions d: DBDivisions.allDivisions) {
            if (d.getCountryId() == countryComboBox.getValue().getCountryId()) {
                divisionsInCountry.add(d);
            }
        }
        divisionComboBox.setItems(divisionsInCountry);
    }

}
