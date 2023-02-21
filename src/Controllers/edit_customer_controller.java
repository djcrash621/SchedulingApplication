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

public class edit_customer_controller implements Initializable {
    private static Customers passedInCustomer;
    public TextField customerNameField;
    public TextField addressField;
    public TextField postalCodeField;
    public TextField phoneNumField;
    public Button saveNewCustomer;
    public Button cancelBtn;
    public ComboBox<Divisions> divisionComboBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerNameField.setText(passedInCustomer.getCustomerName());
        addressField.setText(passedInCustomer.getAddress());
        postalCodeField.setText(passedInCustomer.getPostalCode());
        phoneNumField.setText(passedInCustomer.getPhoneNum());
        divisionComboBox.setItems(DBDivisions.getAllDivisions());
        divisionComboBox.setValue(DBDivisions.getDivision(passedInCustomer.getDivisionId()));
    }

    public void saveCustomer(ActionEvent actionEvent) throws IOException {
        DBCustomers.updateCustomer(new Customers(passedInCustomer.getCustomerId(), customerNameField.getText(), addressField.getText(), postalCodeField.getText(), phoneNumField.getText(), divisionComboBox.getSelectionModel().getSelectedItem().getDivisionId()));
        Scheduling_Application.changePage(actionEvent,"../JavaFXML/welcome_page.fxml", "Welcome Page");
    }

    public void returnToWelcome(ActionEvent actionEvent) throws IOException {
        Scheduling_Application.changePage(actionEvent,"../JavaFXML/welcome_page.fxml", "Welcome Page");
    }

    public static void customerToModify(Customers customer) {
        passedInCustomer = customer;
    }

}
