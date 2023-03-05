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

    public static ObservableList<Customers> allCustomers = FXCollections.observableArrayList();

    /**
     * This method queries the database for all data in the Customers table and
     * adds the values into the AllCustomers ObservableList used throughout the application.
     */
    public static void getAllCustomers() {

        allCustomers.clear();

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
            allCustomers.add(new Customers(updatedCustomer.getCustomerId(), updatedCustomer.getCustomerName(), updatedCustomer.getAddress(), updatedCustomer.getPostalCode(), updatedCustomer.getPhoneNum(), updatedCustomer.getDivisionId()));
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
            allCustomers.add(new Customers(getCustomerID(newCustomer.getCustomerName()), newCustomer.getCustomerName(), newCustomer.getAddress(), newCustomer.getPostalCode(), newCustomer.getPhoneNum(), newCustomer.getDivisionId()));
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
   public static Customers lookupCustomer(int searchCustomerId) {
        for (Customers c : allCustomers) {
            if (searchCustomerId == c.getCustomerId()) {
                return  c;
            }
        }
       Scheduling_Application.displayError("Customer with ID " + searchCustomerId + " not found.");
        return null;
    }

    /**
     * Searches the allCustomers for all values with names that start with the given String.
     * @param customerName The string passed in to be searched for.
     * @return A list of all customers with names that start with the given String.
     */
    public static ObservableList<Customers> lookupCustomer(String customerName) {
       ObservableList<Customers> searchedCustomers = FXCollections.observableArrayList();
       for (Customers c : allCustomers) {
           if (c.getCustomerName().contains(customerName)) {
               searchedCustomers.add(c);
           }
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
            DBAppointments.allAppointments.forEach((apt) -> {
                if (apt.getCustomerId() == customer.getCustomerId()) {
                    DBAppointments.deleteApt(apt);
                }
            });

            System.out.println("Deleting customer from database.");
            String sql = "DELETE FROM CUSTOMERS WHERE CUSTOMER_ID = " + customer.getCustomerId() + ";";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.executeUpdate();
            System.out.println("Printing from local table.");
            allCustomers.remove(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getCustomerID(String customerName) throws SQLException {
        String sql = "SELECT CUSTOMER_ID FROM CUSTOMERS WHERE CUSTOMER_NAME = '" + customerName + "';";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        int result = 0;
        while (rs.next()){
            result = rs.getInt("CUSTOMER_ID");
        }
        return result;
    }



}
