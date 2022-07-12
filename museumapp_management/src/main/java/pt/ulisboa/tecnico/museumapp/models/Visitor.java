package pt.ulisboa.tecnico.museumapp.models;

import java.io.Serializable;

public class Visitor implements Serializable {
    private String fName;
    private String lName;
    private String email_address;
    private Integer contact;
    private Integer noVisitors;

    public Visitor(String fName, String lName, String email_address, Integer contact, Integer noVisitors) {
        this.fName=fName;
        this.lName=lName;
        this.email_address=email_address;
        this.contact=contact;
        this.noVisitors=noVisitors;

    }
    public Visitor(){}

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getEmail_address() {
        return email_address;
    }

    public Integer getContact() {
        return contact;
    }

    public Integer getNoVisitors() {
        return noVisitors;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public void setContact(Integer contact) {
        this.contact = contact;
    }

    public void setNoVisitors(Integer noVisitors) {
        this.noVisitors = noVisitors;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", email_address='" + email_address + '\'' +
                ", contact=" + contact +
                ", noVisitors=" + noVisitors +
                '}';
    }
}
