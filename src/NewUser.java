import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.Statement;

class NewUser {
    private Statement statement;
    private int pid;
    private int bid;
    NewUser(Statement statement)throws IOException{
        this.statement = statement;
        pid=enterDetails();
        if(pid != -1){
            callBank();
        }
        else {
            System.out.println("Request could not be processed...");
        }
    }
    private int enterDetails() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\nEnter your name :");
        String name = br.readLine();
        System.out.println("Enter father\'s name :");
        String father = br.readLine();
        System.out.println("Enter mother\'s name :");
        String mother = br.readLine();
        System.out.println("Enter date of birth (yyyy-mm-dd) :");
        String dob = br.readLine();
        System.out.println("Enter gender (male/female/other) :");
        Gender gender = Gender.valueOf(br.readLine());
        System.out.println("Enter marital status (single/married) :");
        MaritalStatus maritalStatus = MaritalStatus.valueOf(br.readLine());
        System.out.println("Enter aadhar number :");
        String aadhar = br.readLine();
        System.out.println("Enter phone number :");
        String phone = br.readLine();
        System.out.println("Enter email id :");
        String email = br.readLine();
        System.out.println("Enter street :");
        String street = br.readLine();
        System.out.println("Enter city :");
        String city = br.readLine();
        System.out.println("Enter pin code :");
        String pincode = br.readLine();
        System.out.println("Enter state :");
        String state = br.readLine();

        int pid=new Person(statement,name,father,mother,dob,gender,maritalStatus,aadhar,
                phone,email,street,city,pincode,state).createPerson();
        return pid;
    }

    private void callBank()throws IOException{
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
