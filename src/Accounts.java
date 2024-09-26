import java.sql.*;
import java.util.Scanner;

public class Accounts {
    private Connection conn;
    private Scanner sc;

    public Accounts(Connection conn, Scanner sc) {
        this.conn = conn;
        this.sc = sc;
    }
    public long open_account(String Email) throws SQLException {
       if(!account_exist(Email)){
           String query = "INSERT INTO accounts (account_number, full_name, email, balance, security_pin) VALUES (?, ?, ?, ?, ?)";

           sc.nextLine();
           System.out.print("Enter Full Name: ");
           String fullName = sc.nextLine();

           System.out.print("Enter Balance: ");
           double balance = sc.nextDouble();

           sc.nextLine();
           System.out.print("Enter Security Pin: ");
           String securityPin = sc.nextLine();

           long account_num = generate_account_number();
           PreparedStatement ps = conn.prepareStatement(query);
           ps.setString(3, Email);
           ps.setString(2, fullName);
           ps.setDouble(4, balance);
           ps.setString(5, securityPin);
           ps.setLong(1, account_num);
           int affected_row = ps.executeUpdate();
           if(affected_row > 0){
               return account_num;
           }
           else {
               System.out.println("Account creation failed!");
               throw new SQLException();
           }
       }
       System.out.print("Account Already Exists: ");
        return 0;
    }

    public long get_account_number(String Email) throws SQLException {
        String query = "SELECT account_number FROM accounts WHERE email = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, Email);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getLong("account_number");
        }
        else {
            System.out.println("Account not found");
        }
        return 0;
    }

    public long generate_account_number() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select account_number from accounts order by account_number desc limit 1");
        if(rs.next()){
            long long_account_number = rs.getLong("account_number");
            return long_account_number+1;
        }
        else {
            return 1000000;
        }
    }

    boolean account_exist(String email) throws SQLException {
        String sql = "select account_number from Accounts where email = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }
}
