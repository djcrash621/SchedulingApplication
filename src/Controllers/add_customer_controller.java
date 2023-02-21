package Controllers;

import DBAccess.DBCustomers;
import DBAccess.DBDivisions;
import Main.Scheduling_Application;
import Model.Customers;
import Model.Divisions;
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

public class add_customer_controller implements Initializable {
    public TextField customerNameField;
    public TextField addressField;
    public TextField postalCodeField;
    public TextField phoneNumField;
    public Button saveNewCustomer;
    public Button cancelBtn;
    public ComboBox<Divisions> divisionComboBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        divisionComboBox.setItems(DBDivisions.getAllDivisions());
    }

    public void saveCustomer(ActionEvent actionEvent) throws IOException {
        if (customerNameField.getText().isBlank()) { Scheduling_Application.displayError("Customer Name must not be blank."); }
        else if (addressField.getText().isBlank()) { Scheduling_Application.displayError("Address must not be be blank."); }
        else if (postalCodeField.getText().isBlank()) { Scheduling_Application.displayError("Postal code must not be blank"); }
        else if (phoneNumField.getText().isBlank()) { Scheduling_Application.displayError("Phone number must not be blank"); }

        DBCustomers.addCustomer(new Customers(0, customerNameField.getText(), addressField.getText(), postalCodeField.getText(), phoneNumField.getText(), divisionComboBox.getSelectionModel().getSelectedItem().getDivisionId()));
        Scheduling_Application.changePage(actionEvent, "../JavaFXML/welcome_page.fxml", "Edit Customer Form");
    }

    //DONE
    //For cancel button
    public void returnToWelcome(ActionEvent actionEvent) throws IOException {
        Scheduling_Application.changePage(actionEvent,"../JavaFXML/welcome_page.fxml", "Edit Customer Form");
    }

}
