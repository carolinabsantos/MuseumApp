package pt.ulisboa.tecnico.museumapp.entities;

import javax.persistence.*;

@Entity
public class VisitorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected Integer id;

    @Column(name = "fName", nullable = false)
    protected String fName;

    @Column(name = "lName", nullable = false)
    protected String lName;

    @Column(name = "email_address", nullable = false)
    protected String email_address;

    @Column(name = "contact", nullable = false)
    protected int contact;

    @Column(name = "noVisitors", nullable = false)
    protected int noVisitors;

    @OneToOne(mappedBy = "visitor", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    protected VisitEntity visit;


    public VisitorEntity(String fName, String lName, String email_address, int contact, int noVisitors){
        this.fName=fName;
        this.lName=lName;
        this.email_address=email_address;
        this.contact=contact;
        this.noVisitors=noVisitors;
    }

    public VisitorEntity() {

    }

    public Integer getId() {
        return id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public int getNoVisitors() {
        return noVisitors;
    }

    public void setNoVisitors(int noVisitors) {
        this.noVisitors = noVisitors;
    }

    public VisitEntity getVisit() {
        return visit;
    }

    public void setVisit(VisitEntity visit) {
        this.visit = visit;
    }

    @Override
    public String toString() {
        return "VisitorEntity{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", email_address='" + email_address + '\'' +
                ", contact=" + contact +
                ", noVisitors=" + noVisitors +
                '}';
    }
}
