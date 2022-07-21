package pt.ulisboa.tecnico.museumapp.models;

import java.util.Date;

public class TimeSlot {
    private Integer id;
    private Integer timeSlotDuration;
    private Integer capacity;

    public TimeSlot(){}


    public TimeSlot(Integer timeSlotDuration, Integer capacity){
        this.timeSlotDuration=timeSlotDuration;
        this.capacity=capacity;
    }
    public Integer getId() {
        return id;
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

    @Override
    public String toString() {
        return "TimeSlot{" +
                "id=" + id +
                ", timeSlotDuration=" + timeSlotDuration +
                ", capacity=" + capacity +
                '}';
    }
}
