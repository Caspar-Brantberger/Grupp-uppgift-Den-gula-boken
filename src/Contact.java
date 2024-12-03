public class Contact {

    private String firstName;
    private String lastName;
    private String number;
    private String age;
    private String address;

    public Contact(String firstName, String lastName, String number, String age, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.age = age;
        this.address = address;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getNumber(){
        return number;
    }

    public String getAge(){
        return age;
    }

    public String getAddress(){
        return address;
    }


    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setNumber(String number){
        this.number = number;
    }

    public void setAge(String age){
        this.age = age;
    }

    public void setAddress(String address){
        this.address = address;
    }


    public void displayContact(){
        System.out.println(firstName + " " + lastName + " " + number + " " + age + " years " + address);
    }
}
