package model;

public class Owner {
    private String name;
    private String email ;
    private String phoneNumber ;
    public Owner(String name,String email ,String phoneNumber){
        this.name = name ;
        this.email = email ;
        this.phoneNumber = phoneNumber ;
    }

    //Getters
    public String getName(){
        return  name ;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    //Setters

    public void setEmail(String email) {
        this.email = email;
    }
    public void setName(String name){
        this.name = name ;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber=phoneNumber;
    }
    @Override
    public String toString(){
        return "Owner = { "+" name = '"+ name +'\''+" , email = '" + email +'\''
                + ", phoneNumber = '"+phoneNumber+'\''+"}";
    }
}
