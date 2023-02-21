package Utilities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Fruits_Query {
    public static int insert(String fruitName, int colorId) throws SQLException {
        String sql = "INSERT INTO FRUITS (FRUIT_NAME, COLOR_ID) VALUES (?, ?)";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, fruitName);
        ps.setInt(2, colorId);
        return ps.executeUpdate(); // Rows Affected
    }

    public static int update(int fruitId, String fruitName) throws SQLException {
        String sql = "UPDATE FRUITS SET FRUIT_NAME = ? WHERE FRUIT_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, fruitName);
        ps.setInt(2, fruitId);
        return ps.executeUpdate(); // Rows Affected
    }

    public static int delete(int fruitId) throws SQLException {
        String sql = "DELETE FROM FRUITS WHERE FRUIT_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, fruitId);
        return ps.executeUpdate();
    }

    public static void select() throws SQLException {
        String sql = "SELECT * FROM FRUITS";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int fruitId = rs.getInt("FRUIT_ID");
            String fruitName = rs.getString("FRUIT_NAME");
            int ColorID = rs.getInt("COLOR_ID");
            System.out.println(fruitId + " | " + fruitName + " | " + ColorID);
        }
    }

    public static void select(int colorId) throws SQLException {
        String sql = "SELECT * FROM FRUITS WHERE COLOR_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, colorId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int fruitId = rs.getInt("FRUIT_ID");
            String fruitName = rs.getString("FRUIT_NAME");
            System.out.println(fruitId + " | " + fruitName);
        }
    }
}
