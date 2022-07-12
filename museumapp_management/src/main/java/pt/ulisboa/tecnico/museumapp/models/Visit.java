package pt.ulisboa.tecnico.museumapp.models;

import pt.ulisboa.tecnico.museumapp.entities.StateEntity;

import java.io.Serializable;

public class Visit implements Serializable {
    private String start_time;
    private String end_time;
    private StateEntity state;
    private TimeMachine timeMachine;

    public Visit(String start_time, String end_time, StateEntity state, TimeMachine timeMachine) {
        this.start_time=start_time;
        this.end_time=end_time;
        this.state=state;
        this.timeMachine=timeMachine;

    }
    public Visit(){}

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public StateEntity getState() {
        return state;
    }

    public TimeMachine getTimeMachine() {
        return timeMachine;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public void setState(StateEntity state) {
        this.state = state;
    }

    public void setTimeMachine(TimeMachine timeMachine) {
        this.timeMachine = timeMachine;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", state=" + state +
                ", timeMachine=" + timeMachine +
                '}';
    }
}
