package DBAccess;

import Main.Scheduling_Application;
import Model.Users;
import Utilities.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

/**
 * This class defines the method querying the database for the users class, and methods searching the allUsers ObservableList.
 */
public class DBUsers {
    public static ObservableList<Users> allUsers = FXCollections.observableArrayList();

    /**
     * This method queries the database for all data in the users table and adds them
     * to the local allUsers ObservableList.
     */
    public static void getAllUsers() {
        try {
            String sql = "SELECT * FROM USERS";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("USER_ID");
                String userName = rs.getString("USER_NAME");
                String password = rs.getString("PASSWORD");
                Users newUser = new Users(userId, userName, password);
                allUsers.add(newUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method checks the provided username and password values with the allUsers list
     * and verifies if the information matches that of any of the users in the list. If so, true is return. Else,
     * error messages are displayed for the first value that does not have a corresponding user.
     * @param checkUsername The inputted username value to be searched for in the database.
     * @param checkPassword The inputted password value to verify in the database for the user who's username was provided.
     * @param rb The resource bundle used to print the errors in the given language.
     * @return True or false that the values were found.
     */
    public static boolean loginCheck(String checkUsername, String checkPassword, ResourceBundle rb) {
        for (Users a : allUsers) {
            if (checkUsername.equals(a.getUserName())) {
                if (checkPassword.equals(a.getPassword())) {
                    Scheduling_Application.activeUser = a;
                    return true;
                } else {
                    Scheduling_Application.displayError(rb.getString("Incorrect") + " " + rb.getString("password") + ".");
                }
            }
        }
        Scheduling_Application.displayError(rb.getString("User") + " " + rb.getString("not")+ " " + rb.getString("found") + ".");
        return false;
    }


}
