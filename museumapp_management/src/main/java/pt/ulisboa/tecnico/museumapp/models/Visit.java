package pt.ulisboa.tecnico.museumapp.models;

import pt.ulisboa.tecnico.museumapp.entities.State;
import pt.ulisboa.tecnico.museumapp.entities.TimeMachineEntity;
import pt.ulisboa.tecnico.museumapp.entities.VisitorEntity;

import java.util.Date;


public class Visit {
    private Integer id;
    private TimeMachine timeMachine;
    private String startTime;
    private String endTime;
    private State state;
    private Integer visitorId;
    private String visitDate;
    private String observations;
    private Integer timeSlotId;

    public Visit(){}

    public Visit(Integer visitorId){
        this.visitorId=visitorId;
    }

    public Visit(TimeMachine timeMachine, String startTime, String endTime, State state, Integer visitorId, String observations, String visitDate){
        this.timeMachine=timeMachine;
        this.startTime=startTime;
        this.endTime=endTime;
        this.state=state;
        this.visitorId=visitorId;
        this.observations=observations;
        this.visitDate=visitDate;
    }
    public Visit(Integer id, TimeMachine timeMachine, String startTime, String endTime, State state, Integer visitorId, String observations, String visitDate){
        this.id=id;
        this.timeMachine=timeMachine;
        this.startTime=startTime;
        this.endTime=endTime;
        this.state=state;
        this.visitorId=visitorId;
        this.observations=observations;
        this.visitDate=visitDate;
    }

    public Visit(TimeMachine timeMachine){
        this.timeMachine=timeMachine;
    }

    public Visit(Integer id, Integer visitorId){
        this.id=id;
        this.visitorId=visitorId;
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
    public String getObservations(){
        return observations;
    }
    public void setObservations(String observations){
        this.observations=observations;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public Integer getTimeSlot() {
        return timeSlotId;
    }

    public void setTimeSlot(Integer timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", timeMachine=" + timeMachine +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", state=" + state +
                ", visitorId=" + visitorId +
                ", visitDate='" + visitDate + '\'' +
                ", observations='" + observations + '\'' +
                ", timeSlotId=" + timeSlotId +
                '}';
    }

}
