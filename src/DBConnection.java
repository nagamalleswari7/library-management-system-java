import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {

        Connection conn = null;

        try {

            System.out.println("Trying to connect...");

            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library_db",
                "root",
                "amma$7"
            );

            System.out.println("Database Connected!");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }
}