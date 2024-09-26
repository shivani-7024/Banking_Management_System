import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Users {
    private Connection conn;
    private Scanner sc;

    public Users(Connection conn, Scanner sc) {
        this.conn = conn;
        this.sc = sc;
    }
    public void register() throws SQLException {
        sc.nextLine();
        System.out.println("Enter your Full name: ");
        String name = sc.nextLine();

        System.out.println("Enter your email: ");
        String email = sc.nextLine();

        System.out.println("Enter your password: ");
        String password = sc.nextLine();

        if(user_exist(email)){
            System.out.println("Email already exists!");
            return;
        }
        String register_sql = "insert into user (full_name, email, password) values (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(register_sql);
        ps.setString(1, name);
        ps.setString(2, email);
        ps.setString(3, password);
        int affected_row = ps.executeUpdate();
        if (affected_row > 0) {
            System.out.println("User registered successfully!");
        }
        else {
            System.out.println("Registration failed!");
        }
    }

    public String login() throws SQLException {
        sc.nextLine();

        System.out.println("Enter your email: ");
        String email = sc.nextLine();

        System.out.println("Enter password: ");
        String password = sc.nextLine();

        String login_sql = "select * from user where email = ? and password = ?";
        PreparedStatement ps = conn.prepareStatement(login_sql);
        ps.setString(1, email);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            return email;
        }
        else {
            return null;
        }
    }

    private boolean user_exist(String email) throws SQLException {
        String sql = "select * from user where email = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            return true;
        }
        else {
            return false;
        }
    }
}
