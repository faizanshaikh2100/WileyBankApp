import java.sql.*;
import java.util.Scanner;

public class Login {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/wileybankapp";
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Get user input for login credentials
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter customer ID: ");
            String id = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();


            String loginQuery = "SELECT * FROM customers WHERE id = ? AND password = ?";
            stmt = conn.prepareStatement(loginQuery);

            stmt.setString(1, id);
            stmt.setString(2, password);

            rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Login successful!");
                // Perform further actions or display user information
            } else {
                System.out.println("Invalid login credentials!");
                // Handle unsuccessful login
            }


            rs.close();
            stmt.close();
            conn.close();
            scanner.close();
        } catch (SQLException se) {

            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
 
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
