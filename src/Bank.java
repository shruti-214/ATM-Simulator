import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

class Bank{
    private Statement statement;
    private int pid;
    private long cno;
    private int pin;
    private double bal;
    //private int bid;
    Bank(Statement statement,int pid)throws IOException{
        this.statement=statement;
        this.pid=pid;
        generateCardNo();
        generatePin();
        initialDeposit();
    }

    int createBank(){
        int bid=-1;
        String query = "insert into bank values(null,"+cno+","+pin+","+bal+")";
        try {
            statement.executeUpdate(query);
            query = "select id from bank where card_no="+cno;
            ResultSet rs=statement.executeQuery(query);
            if(rs.next()){
                bid=rs.getInt("id");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return bid;
    }

    private void generateCardNo(){
        int count=0;
        String query="select count(id) from bank";
        try {
            ResultSet rs=statement.executeQuery(query);
            rs.next();
            count=rs.getInt(1);
        }catch (Exception e){
            System.out.println(e);
        }
        long x=7*(count+10)+3;
        cno=((long) Math.pow(10,12))+x;
    }

    private void generatePin(){
        Random random=new Random();
        pin=1000+random.nextInt(8999);
    }

    private void initialDeposit()throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\nEnter amount to deposit (minimum required amount is 1500) :");
        double b=Double.parseDouble(br.readLine());
        if(b>=1500){
            bal=b;
        }
        else {
            System.out.println("Insufficient amount!!!");
            initialDeposit();
        }
    }


}
