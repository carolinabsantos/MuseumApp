package pt.ulisboa.tecnico.museumapp.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class VisitEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "timeMachine_id")
    protected TimeMachineEntity timeMachine;

    @Column(name = "start_time", nullable = false)
    protected String startTime;

    @Column(name = "end_time", nullable = false)
    protected String endTime;

    @Column(name = "state", nullable = false)
    protected State state;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "visitor_id", nullable = false)
    protected VisitorEntity visitor;

    @Column(name = "visit_date", nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String visitDate;
    @Column(name = "observations", nullable = false)
    protected String observations;

    public VisitEntity(TimeMachineEntity timeMachine, String startTime, String endTime, State state, VisitorEntity visitor, String observations, String visitDate){
        this.timeMachine=timeMachine;
        this.startTime=startTime;
        this.endTime=endTime;
        this.state=state;
        this.visitor=visitor;
        this.observations=observations;
        this.visitDate=visitDate;

    }

    public VisitEntity(TimeMachineEntity timeMachine, VisitorEntity visitor, String observations, String visitDate){
        this.timeMachine=timeMachine;
        this.startTime="Not_started";
        this.endTime="Not_started";
        this.state=State.TO_START;
        this.visitor=visitor;
        this.observations=observations;
        this.visitDate=visitDate;
    }

    protected VisitEntity() {
    }

    public Integer getId() {
        return id;
    }

    public TimeMachineEntity getTimeMachine() {
        return timeMachine;
    }

    public void setTimeMachine(TimeMachineEntity timeMachine) {
        this.timeMachine = timeMachine;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public VisitorEntity getVisitor() {
        return visitor;
    }

    public void setVisitor(VisitorEntity visitor) {
        this.visitor = visitor;
    }

    public String getObservations(){
        return observations;
    }
    public void setObservations(String observations){
        this.observations=observations;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    @Override
    public String toString() {
        return "VisitEntity{" +
                "id=" + id +
                ", timeMachine=" + timeMachine +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", state=" + state + '\'' +
                ", visitor=" + visitor + '\'' +
                ", visit date=" + visitDate + '\'' +
                ", observations=" + observations +
                '}';
    }
}
