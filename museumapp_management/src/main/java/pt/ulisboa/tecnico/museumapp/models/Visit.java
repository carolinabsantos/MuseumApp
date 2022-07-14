package pt.ulisboa.tecnico.museumapp.models;

import pt.ulisboa.tecnico.museumapp.entities.State;
import pt.ulisboa.tecnico.museumapp.entities.VisitorEntity;


public class Visit {
    private TimeMachine timeMachine;
    private String startTime;
    private String endTime;
    private State state;
    private Integer visitorId;

    public Visit(){}

    public Visit(Integer visitorId){
        this.visitorId=visitorId;
    }

    public Visit(TimeMachine timeMachine, String startTime, String endTime, State state, Integer visitorId){
        this.timeMachine=timeMachine;
        this.startTime=startTime;
        this.endTime=endTime;
        this.state=state;
        this.visitorId=visitorId;
    }

    public Visit(TimeMachine timeMachine){
        this.timeMachine=timeMachine;
    }

    public TimeMachine getTimeMachine() {
        return timeMachine;
    }

    public void setTimeMachine(TimeMachine timeMachine) {
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

    public Integer getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(Integer visitorId) {
        this.visitorId = visitorId;
    }

    @Override
    public String toString() {
        return "Visit{" +
                ", timeMachine=" + timeMachine +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", state=" + state + '\'' +
                ", visitorId=" + visitorId +
                '}';
    }
}
