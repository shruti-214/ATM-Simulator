import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

class ExistingUser {
    private Statement statement;
    private int pid;
    private String aadhar;
    private int bid;
    ExistingUser(Statement statement,String aadhar)throws IOException{
        this.statement=statement;
        this.aadhar=aadhar;
        pid=retrieveId();
        if(pid==-1){
            System.out.println("Request could not be processed...");
        }
        else {
            callBank();
        }
    }
    private int retrieveId(){
        int id=-1;
        String query="select id from person where aadhar_no="+aadhar;
        try{
            ResultSet rs=statement.executeQuery(query);
            if(rs.next()){
                id=rs.getInt("id");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return id;
    }

    private void callBank()throws IOException {
        //create bank object...
        bid=new Bank(statement,pid).createBank();
        if(bid!=-1){
            String query ="insert into person_bank values(null,"+pid+","+bid+")";
            try {
                statement.executeUpdate(query);
                query="select card_no,pin from bank where id="+bid;
                ResultSet rs = statement.executeQuery(query);
                if(rs.next()){
                    System.out.println("\nAccount successfully created...!!");
                    System.out.println("Your card number : "+rs.getLong("card_no"));
                    System.out.println("Your pin : "+rs.getInt("pin"));
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }
        else{
            System.out.println("Request could not be processed...");
        }
    }
}
