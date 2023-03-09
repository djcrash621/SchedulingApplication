package DBAccess;

import Main.Scheduling_Application;
import Model.Appointments;
import Model.Customers;
import Utilities.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/**
 * This class defines the method querying the database for data for the Customer class.
 */
public class DBCustomers {


    /**
     * This method queries the database for all data in the Customers table and
     * adds the values into the AllCustomers ObservableList used throughout the application.
     */
    public static ObservableList<Customers> getAllCustomers() {
        ObservableList<Customers> allCustomers = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM CUSTOMERS";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int customerId = rs.getInt("CUSTOMER_ID");
                String customerName = rs.getString("CUSTOMER_NAME");
                String address = rs.getString("ADDRESS");
                String postalCode = rs.getString("POSTAL_CODE");
                String phoneNum = rs.getString("PHONE");
                int divisionID = rs.getInt("DIVISION_ID");
                allCustomers.add(new Customers(customerId, customerName, address, postalCode, phoneNum, divisionID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCustomers;
    }

    /**
     * This method updates the database Customer table with the given customer object matching the Customer ID
     * found in the database.
     * @param updatedCustomer The updated values of the customer to be pushed to the database.
     */
    public static void updateCustomer(Customers updatedCustomer) {
        try {
            String sql = "UPDATE CUSTOMERS\n" +
                    "SET CUSTOMER_NAME = '" + updatedCustomer.getCustomerName() + "', ADDRESS = '" + updatedCustomer.getAddress() +
                    "', POSTAL_CODE = '" + updatedCustomer.getPostalCode() + "', PHONE = '" + updatedCustomer.getPhoneNum() + "', DIVISION_ID = " +
                    updatedCustomer.getDivisionId() + "\nWHERE CUSTOMER_ID = " + updatedCustomer.getCustomerId();
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method updates the database by inserting into the customer table the given customer value.
     * @param newCustomer The customer object to be inserted into the database.
     */
    public static void addCustomer(Customers newCustomer) {
        try {
            String sql = "INSERT INTO CUSTOMERS (CUSTOMER_NAME, ADDRESS, POSTAL_CODE, PHONE, DIVISION_ID)\n" +
                    "VALUES ( '" + newCustomer.getCustomerName() + "', '" + newCustomer.getAddress() + "', '" +
                    newCustomer.getPostalCode() + "', '" + newCustomer.getPhoneNum() + "', " + newCustomer.getDivisionId() + ")";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method searches the allCustomers list for the customer value with the given CustomerID integer, or returns no value and
     * displays an error indicating that no such customer was found.
     * @param searchCustomerId The ID of the customer to be found in the list.
     * @return Returns the customer object, unless no value is found, then returns null.
     */
   public static Customers lookupCustomer(int searchCustomerId) throws SQLException {
        Customers foundCustomer = null;
        try {
            String sql = "SELECT * FROM CUSTOMERS WHERE CUSTOMER_ID = ?;";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, searchCustomerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int customerId = rs.getInt("CUSTOMER_ID");
                String customer= rs.getString("CUSTOMER_NAME");
                String address = rs.getString("ADDRESS");
                String postalCode = rs.getString("POSTAL_CODE");
                String phoneNum = rs.getString("PHONE");
                int divisionID = rs.getInt("DIVISION_ID");
                foundCustomer = new Customers(customerId, customer, address, postalCode, phoneNum, divisionID);
            }
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        if (foundCustomer == null) {
            Scheduling_Application.displayError("Customer with ID " + searchCustomerId + " not found.");
        }
        return foundCustomer;
    }

    /**
     * Searches the allCustomers for all values with names that start with the given String.
     * @param customerName The string passed in to be searched for.
     * @return A list of all customers with names that start with the given String.
     */
    public static ObservableList<Customers> lookupCustomer(String customerName) {
       ObservableList<Customers> searchedCustomers = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM CUSTOMERS WHERE CUSTOMER_NAME = ?;";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, customerName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int customerId = rs.getInt("CUSTOMER_ID");
                String customer= rs.getString("CUSTOMER_NAME");
                String address = rs.getString("ADDRESS");
                String postalCode = rs.getString("POSTAL_CODE");
                String phoneNum = rs.getString("PHONE");
                int divisionID = rs.getInt("DIVISION_ID");
                searchedCustomers.add(new Customers(customerId, customer, address, postalCode, phoneNum, divisionID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return searchedCustomers;
    }

    /**
     * Updates the database by deleting first the appointments from the appointment table,
     * then the customer itself from the customer table, of the customer passed to the method.
     * Also removes it from the local allCustomers ObservableList.
     * @param customer The customer to be deleted.
     */
    public static void deleteCustomer(Customers customer) {
        try {
            String sql = "DELETE FROM APPOINTMENTS WHERE CUSTOMER_ID = ?;";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, customer.getCustomerId());
            ps.executeUpdate();
            sql = "DELETE FROM CUSTOMERS WHERE CUSTOMER_ID = ?;";
            PreparedStatement ps2 = JDBC.getConnection().prepareStatement(sql);
            ps2.setInt(1, customer.getCustomerId());
            ps2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
