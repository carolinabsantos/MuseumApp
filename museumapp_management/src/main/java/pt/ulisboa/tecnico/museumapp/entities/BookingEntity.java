package pt.ulisboa.tecnico.museumapp.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="booking_id")
    private Integer id;
    @Column(name="visit_id")
    private Integer visitId;
    @Column(name="timeMachine_id")
    private Integer timeMachineId;
    @Column(name="visitor_id")
    private Integer visitorId;
    @Column(name="booking_date")
    private Date date;

    public Integer getId() {
        return id;
    }

    public Integer getVisitId() {
        return visitId;
    }

    public void setVisitId(Integer visitId) {
        this.visitId = visitId;
    }

    public Integer getTimeMachineId() {
        return timeMachineId;
    }

    public void setTimeMachineId(Integer timeMachineId) {
        this.timeMachineId = timeMachineId;
    }

    public Integer getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(Integer visitorId) {
        this.visitorId = visitorId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "BookingEntity{" +
                "id=" + id +
                ", visitId=" + visitId +
                ", visitorId=" + visitorId +
                ", date=" + date +
                '}';
    }
}
