import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.Statement;

class Login {
    private Statement statement;
    Login(Statement statement){
        this.statement = statement;
        //login();
    }
    void login()throws IOException {
        int bid;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\nEnter card number :");
        long cno = Long.parseLong(br.readLine());
        System.out.println("\nEnter pin :");
        int pin = Integer.parseInt(br.readLine());
        String query = "select * from bank where card_no = "+cno+" and pin = "+pin;
        ResultSet rs;
        try{
            rs = statement.executeQuery(query);
            if(rs.next()){
                bid = rs.getInt("id");
                // call bank interface...
                new BankInterface(bid,statement).options();
            }
            else {
                System.out.println("Incorrect card number or pin");
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
