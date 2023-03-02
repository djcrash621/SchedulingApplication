package Utilities;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This class defines the methods used for the database connection functions.
 */
public class JDBC {
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "sqlUser"; // Username
    private static final String password = "Passw0rd!"; // Password
    private static Connection connection = null;  // Connection Interface
    private static PreparedStatement preparedStatement;

    /**
     * This method establishes the connection to the database, printing a stacktrace if an exception is thrown.
     */
    public static void makeConnection() {
        try {
            Class.forName(driver); // Locate Driver
            //password = Details.getPassword(); // Assign password
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // reference Connection object
            System.out.println("Connection successful!");
        }
        catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method returns the connection object for the current database connection.
     * @return Database Connection.
     */
    public static Connection getConnection() {
        return connection;
    }

    /**
     * This method closes the connection to the database, printing a stacktrace error if an exception occurs.
     */
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method makes a prepared statement to the provided connection.
     * @param sqlStatement Prepared Statement to be prepared.
     * @param conn Database connection in which to run the statement.
     * @throws SQLException Error may result from prepared statement execution.
     */
    public static void makePreparedStatement(String sqlStatement, Connection conn) throws SQLException {
        if (conn != null)
            preparedStatement = conn.prepareStatement(sqlStatement);
        else
            System.out.println("Prepared Statement Creation Failed!");
    }

    /**
     * Returns the prepared statement of the JDBC Object.
     * @return Prepared Statement if exists, else returns null.
     * @throws SQLException Exception may result from prepared statement handling.
     */
    public static PreparedStatement getPreparedStatement() throws SQLException {
        if (preparedStatement != null)
            return preparedStatement;
        else System.out.println("Null reference to Prepared Statement");
           return null;
    }



}