package pt.ulisboa.tecnico.museumapp.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class VisitEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected Integer id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "visitor_id", nullable = false)
    protected VisitorEntity visitor_id;

    @Column(name = "start_time", nullable = false)
    protected String start_time;

    @Column(name = "end_time", nullable = false)
    protected String end_time;

    @Column(name = "state", nullable = false)
    protected StateEntity state;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "timeMachine_id", nullable = false)
    protected TimeMachineEntity timeMachine;

    public VisitEntity(String start_time, String end_time, StateEntity state,TimeMachineEntity timeMachine) {
        this.start_time=start_time;
        this.end_time=end_time;
        this.state=state;
        this.timeMachine=timeMachine;
    }

    public VisitEntity(TimeMachineEntity timeMachine) {
        this.start_time=start_time;
        this.end_time=end_time;
        this.state=StateEntity.TO_START;
        this.timeMachine=timeMachine;
    }

    public VisitEntity() {

    }

    public Integer getId() {
        return id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public StateEntity getState() {
        return state;
    }

    public void setState(StateEntity state) {
        this.state = state;
    }

    public TimeMachineEntity getTimeMachine() {
        return timeMachine;
    }

    public void setTimeMachine(TimeMachineEntity timeMachine) {
        this.timeMachine = timeMachine;
    }

    @Override
    public String toString() {
        return "VisitEntity{" +
                "id=" + id +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", state='" + state + '\'' +
                ", timeMachine=" + timeMachine +
                '}';
    }
}
