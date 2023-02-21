package Controllers;

import DBAccess.DBUsers;
import Main.Scheduling_Application;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class login_controller implements Initializable {

    public Button login_btn;
    public TextField username_input;
    public PasswordField password_input;
    public Label login_lbl;
    public Label zoneId_lbl;
    public Label location_lbl;
    public static ResourceBundle rb;


    @Override
    public void initialize(URL url, ResourceBundle resourcebundle) {
        username_input.setPromptText(rb.getString("Enter") + " " + rb.getString("username"));
        password_input.setPromptText(rb.getString("Enter") + " " + rb.getString("password"));
        login_lbl.setText(rb.getString("Welcome"));
        login_btn.setText(rb.getString("Login"));
        location_lbl.setText(rb.getString("Location") + ":");
        zoneId_lbl.setText(Scheduling_Application.localZone.toString());

    }
    
    public void attempt_login(ActionEvent actionEvent) throws IOException {

        boolean verify = DBUsers.loginCheck(username_input.getText(), password_input.getText(), rb);

        if (verify) {
            Scheduling_Application.changePage(actionEvent,"../JavaFXML/welcome_page.fxml", "Welcome Page");
        }

    }

    public static void getResourceBundle (ResourceBundle resourceBundle) {
        rb = resourceBundle;
    }
}
