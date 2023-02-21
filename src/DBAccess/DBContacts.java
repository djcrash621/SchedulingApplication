package DBAccess;

import Model.Contacts;
import Utilities.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBContacts {
    public static ObservableList<Contacts> allContacts = FXCollections.observableArrayList();

    public static void getAllContacts() {
        try {
            String sql = "SELECT * FROM CONTACTS";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int contactId = rs.getInt("CONTACT_ID");
                String contactName = rs.getString("CONTACT_NAME");
                String email = rs.getString("EMAIL");
                Contacts newContact = new Contacts(contactId, contactName, email);
                allContacts.add(newContact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Contacts findContact(int searchContactId) {
        Contacts result = null;
        try {
            String sql = "SELECT * FROM CONTACTS WHERE CONTACT_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, searchContactId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int contactId = rs.getInt("CONTACT_ID");
                String contactName = rs.getString("CONTACT_NAME");
                String email = rs.getString("EMAIL");
                result = new Contacts(contactId, contactName, email);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
