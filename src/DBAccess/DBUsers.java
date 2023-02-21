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

public class DBUsers {
    public static ObservableList<Users> allUsers = FXCollections.observableArrayList();

    public static ObservableList<Users> getAllUsers() {
        try {
            String sql = "SELECT * FROM USERS";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("USER_ID");
                String userName = rs.getString("USER_NAME");
                String password = rs.getString("PASSWORD");
                Timestamp createDate = rs.getTimestamp("CREATE_DATE");
                String createdBy = rs.getString("CREATED_BY");
                Timestamp lastUpdate = rs.getTimestamp("LAST_UPDATE");
                String lastUpdatedBy = rs.getString("LAST_UPDATED_BY");
                Users newUser = new Users(userId, userName, password, createDate, createdBy, lastUpdate, lastUpdatedBy);
                allUsers.add(newUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

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
