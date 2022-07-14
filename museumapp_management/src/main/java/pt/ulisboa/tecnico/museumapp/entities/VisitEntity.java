package pt.ulisboa.tecnico.museumapp.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

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


    public VisitEntity(TimeMachineEntity timeMachine, String startTime, String endTime, State state, VisitorEntity visitor){
        this.timeMachine=timeMachine;
        this.startTime=startTime;
        this.endTime=endTime;
        this.state=state;
        this.visitor=visitor;

    }
    public VisitEntity(TimeMachineEntity timeMachine, VisitorEntity visitor){
        this.timeMachine=timeMachine;
        this.startTime="Not_started";
        this.endTime="Not_started";
        this.state=State.TO_START;
        this.visitor=visitor;

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

    @Override
    public String toString() {
        return "VisitEntity{" +
                "id=" + id +
                ", timeMachine=" + timeMachine +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", state=" + state + '\'' +
                ", visitor=" + visitor +
                '}';
    }
}
