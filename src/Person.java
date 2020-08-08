import java.sql.ResultSet;
import java.sql.Statement;

enum Gender{
    male,female,other
}
enum MaritalStatus{
    single,married
}
class Person {
    private Statement statement;
    private String name;
    private String father;
    private String mother;
    private String dob;
    private Gender gender;
    private MaritalStatus maritalStatus;
    private String aadhar;
    private String phone;
    private String email;
    private String street;
    private String city;
    private String pincode;
    private String state;

    Person(Statement statement,String name,String father,String mother,String dob,Gender gender,MaritalStatus maritalStatus,String aadhar, String phone, String email, String street,String city, String pincode, String state){
        this.statement=statement;
        this.name = name;
        this.father = father;
        this.mother = mother;
        this.dob = dob;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.aadhar = aadhar;
        this.phone = phone;
        this.email = email;
        this.street = street;
        this.city = city;
        this.pincode = pincode;
        this.state = state;
    }

    int createPerson(){
        int pid=-1;
        String query="insert into person values(null,'"+
                name+"','"+father+"','"+mother+"','"+
                dob+"','"+gender+"','"+maritalStatus+"','"+
                aadhar+"','"+phone+"','"+email+"','"+
                street+"','"+city+"','"+pincode+"','"+state+"')";
        try {
            statement.executeUpdate(query);
            query="select id from person where aadhar_no="+aadhar;
            ResultSet rs=statement.executeQuery(query);
            rs.next();
            pid=rs.getInt("id");
        }
        catch (Exception e){
            System.out.println(e);
        }
        return  pid;
    }




}
