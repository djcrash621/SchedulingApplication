package DBAccess;

import Main.Scheduling_Application;
import Model.Customers;
import Utilities.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DBCustomers {

    public static ObservableList<Customers> allCustomers = FXCollections.observableArrayList();

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

   public static Customers lookupCustomer(int searchCustomerId) {
        for (Customers c : allCustomers) {
            if (searchCustomerId == c.getCustomerId()) {
                return  c;
            }
        }
       Scheduling_Application.displayError("Customer with ID " + searchCustomerId + " not found.");
        return null;
    }

    public static ObservableList<Customers> lookupCustomer(String customerName) {
       ObservableList<Customers> searchedCustomers = FXCollections.observableArrayList();
       for (Customers c : allCustomers) {
           if (c.getCustomerName().contains(customerName)) {
               searchedCustomers.add(c);
           }
       }
       return searchedCustomers;
    }

    public static void deleteCustomer(Customers customer) {
        try {
            String sql = "DELETE FROM APPOINTMENTS WHERE CUSTOMER_ID = " + customer.getCustomerId();
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.executeUpdate();
            sql = "DELETE FROM CUSTOMERS WHERE CUSTOMER_ID = " + customer.getCustomerId();
            ps = JDBC.getConnection().prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
