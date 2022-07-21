package pt.ulisboa.tecnico.museumapp.models;

import java.util.List;

public class Schedule {
    private Integer id;
    private String beginningDate;

    private String endingDate;
    private List<TimeSlot> timeSlots;

    public Schedule(){}

    public Schedule(String beginningDate, String endingDate, List<TimeSlot> timeSlots){
        this.beginningDate=beginningDate;
        this.endingDate=endingDate;
        this.timeSlots=timeSlots;
    }
    public Integer getId() {
        return id;
    }

    public String getBeginningDate() {
        return beginningDate;
    }

    public void setBeginningDate(String beginningDate) {
        this.beginningDate = beginningDate;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", beginningDate='" + beginningDate + '\'' +
                ", endingDate='" + endingDate + '\'' +
                ", timeSlots=" + timeSlots +
                '}';
    }
}
