import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
//import com.mysql.jdbc.Driver;

class Conn {
    private Connection connection;
    private Statement statement;

    Conn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/atm", "root", "");
            statement = connection.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    Statement getStatement() {
        return statement;
    }

}