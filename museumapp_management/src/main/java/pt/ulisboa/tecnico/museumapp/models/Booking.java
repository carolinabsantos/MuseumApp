package pt.ulisboa.tecnico.museumapp.models;

import javax.persistence.Column;
import java.util.Date;

public class Booking {
    private Integer id;
    private Integer visitId;
    private Integer timeMachineId;
    private Integer visitorId;

    private Integer noVisitors;
    private Date date;

    public Booking(Integer visitId, Integer timeMachineId, Integer visitorId, Integer noVisitors, Date date){
        this.visitId=visitId;
        this.visitorId=visitorId;
        this.timeMachineId=timeMachineId;
        this.noVisitors=noVisitors;
        this.date=date;
    }
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

    public Integer getNoVisitors() {
        return noVisitors;
    }

    public void setNoVisitors(Integer noVisitors) {
        this.noVisitors = noVisitors;
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
