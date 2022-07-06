package pt.ulisboa.tecnico.museumapp_backend.entities;

public class Visit {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private int contact;
    private int n_visitors;
    private String message;

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getEmailAddress(){
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getContact(){
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public int getN_visitors(){
        return n_visitors;
    }

    public void setN_visitors(int n_visitors) {
        this.n_visitors = n_visitors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
