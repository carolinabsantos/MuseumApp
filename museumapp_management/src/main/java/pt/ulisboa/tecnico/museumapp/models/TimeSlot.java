package pt.ulisboa.tecnico.museumapp.models;

import pt.ulisboa.tecnico.museumapp.entities.TimeSlotState;

import java.util.Date;

public class TimeSlot {
    private Integer id;
    private Integer timeSlotDuration;
    private Integer capacity;
    private Integer scheduleId;
    private TimeSlotState state;

    private String name;

    public TimeSlot(){}


    public TimeSlot(Integer timeSlotDuration, Integer capacity){
        this.timeSlotDuration=timeSlotDuration;
        this.capacity=capacity;
    }

    public TimeSlot(Integer id, TimeSlotState state) {
        this.id=id;
        this.state=state;
    }

    public TimeSlot(Integer id, String name) {
        this.id=id;
        this.name=name;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id=id;
    }

    public Integer getTimeSlotDuration() {
        return timeSlotDuration;
    }

    public void setTimeSlotDuration(Integer timeSlotDuration) {
        this.timeSlotDuration = timeSlotDuration;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }
    public void setScheduleId(Integer scheduleId){
        this.scheduleId=scheduleId;
    }

    public TimeSlotState getState() {
        return state;
    }

    public void setState(TimeSlotState state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TimeSlot{" +
                "id=" + id +
                ", timeSlotDuration=" + timeSlotDuration +
                ", capacity=" + capacity +
                ", scheduleId=" + scheduleId +
                ", state=" + state +
                ", name='" + name + '\'' +
                '}';
    }
}
