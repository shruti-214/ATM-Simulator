import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Statement;

class Atm {
    private Statement statement;
    Atm(){
        statement = new Conn().getStatement();
        //atmSimulator();
    }
    void atmSimulator()throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\nWelcome to ATM\n" +
                "Enter choice\n" +
                "1) Login into existing account\n" +
                "2) Create new account\n" +
                "3) Exit");
        char ch = br.readLine().charAt(0);
        switch (ch){
            case '1': new Login(statement).login();
                break;
            case '2': System.out.println("\nEnter option\n" +
                    "1) Existing user\n" +
                    "2) New user\n" +
                    "3) Back");
                char c = br.readLine().charAt(0);
                switch (c){
                    case '1':
                        System.out.println("\nEnter aadhar number :");
                        String aadhar = br.readLine();
                        new ExistingUser(statement,aadhar);
                        break;
                    case '2': new NewUser(statement);
                        break;
                    default: atmSimulator();
                }
                break;
            default: System.exit(0);
        }
    }
}
