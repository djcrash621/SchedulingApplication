package DBAccess;

import Model.Contacts;
import Utilities.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class defines the methods that pull data from the SQL database for the contacts class.
 */
public class DBContacts {
    public static ObservableList<Contacts> allContacts = FXCollections.observableArrayList();

    /**
     * This method queries the database for all columns from the contact table and adds the values
     * into this classes allContacts ObservableList that is used throughout the rest of the application.
     */
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

    /**
     * This method queries the database for the contact value that contains the given
     * contact ID.
     * @param searchContactId The ID of the contact being searched for.
     * @return Returns the Contact with the given contactId.
     */
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
