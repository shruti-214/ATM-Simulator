import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.Statement;

class BankInterface {
    private Statement statement;
    private int bid;
    BankInterface(int bid,Statement statement){
        this.bid = bid;
        this.statement = statement;
        //options();
    }
    void options() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\n\nEnter your choice : ");
        System.out.println("1) Cash Deposit");
        System.out.println("2) Cash Withdrawal");
        System.out.println("3) Fast Cash");
        System.out.println("4) Pin Change");
        System.out.println("5) Balance Enquiry");
        System.out.println("6) Exit");
        char ch = br.readLine().charAt(0);
        switch (ch){
            case '1':
                cashDeposit();
                break;
            case '2':
                cashWithdrawal();
                break;
            case '3':
                fastCash();
                break;
            case '4':
                pinChange();
                break;
            case '5':
                balanceEnquiry();
                break;
            default:
                System.exit(0);
        }
    }

    private void cashDeposit()throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\nEnter amount to deposit :");
        double amount = Double.parseDouble(br.readLine());
        String query = "select balance from bank where id = "+bid;
        ResultSet rs;
        try{
            rs = statement.executeQuery(query);
            rs.next();
            double bal = rs.getDouble("balance");
            bal += amount;
            query = "update bank set balance = "+bal+" where id = "+bid;
            statement.executeUpdate(query);
            System.out.println("Amount deposited successfully.");
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    private void cashWithdrawal()throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\nEnter amount to withdraw :");
        double amount = Double.parseDouble(br.readLine());
        withdraw(amount);
    }

    private void fastCash()throws IOException{
        System.out.println("\nChoose amount :\n100\n200\n500\n1000\n2000");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int amount = Integer.parseInt(br.readLine());
        withdraw(amount);
    }

    private void withdraw(double amount){
        String query = "select balance from bank where id = "+bid;
        ResultSet rs;
        try{
            rs = statement.executeQuery(query);
            rs.next();
            double bal = rs.getDouble("balance");
            if(amount > bal-1500){
                System.out.println("Not enough balance!!");
            }
            else{
                bal -= amount;
                query = "update bank set balance = "+bal+" where id = "+bid;
                statement.executeUpdate(query);
                System.out.println("Amount withdrawn = "+amount);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    private void balanceEnquiry(){
        String query = "select balance from bank where id = "+bid;
        ResultSet rs;
        try {
            rs = statement.executeQuery(query);
            rs.next();
            System.out.println("Available Balance : Rs."+rs.getDouble("balance"));
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    private void pinChange() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\nEnter new pin :");
        int pin1 = Integer.parseInt(br.readLine());
        System.out.println("\nRe-enter new pin :");
        int pin2 = Integer.parseInt(br.readLine());
        if(pin1 != pin2){
            System.out.println("Pin Mismatch!!");
        }
        else {
            String query = "update bank set pin = "+pin1+" where id = "+bid;
            try {
                statement.executeUpdate(query);
                System.out.println("Pin changed successfully.");
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
    }


}
