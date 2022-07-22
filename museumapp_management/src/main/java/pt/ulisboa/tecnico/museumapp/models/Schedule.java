package pt.ulisboa.tecnico.museumapp.models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Schedule {
    private Integer id;
    private String beginningDate;

    private String beginningHour;

    private String endingHour;

    private String endingDate;
    private List<TimeSlot> timeSlots;

    private Integer capacity;

    private Integer timeSlotsDuration;

    public Schedule(){}

    public Schedule(String beginningDate, String endingDate, String beginningHour, String endingHour, Integer capacity, Integer timeSlotsDuration){
        this.beginningDate=beginningDate;
        this.endingDate=endingDate;
        this.beginningHour=beginningHour;
        this.endingHour=endingHour;
        this.capacity=capacity;
        this.timeSlotsDuration=timeSlotsDuration;
    }
    public Schedule(Integer id, String beginningDate, String endingDate, String beginningHour, String endingHour, Integer capacity, Integer timeSlotsDuration){
        this.id=id;
        this.beginningDate=beginningDate;
        this.endingDate=endingDate;
        this.beginningHour=beginningHour;
        this.endingHour=endingHour;
        this.capacity=capacity;
        this.timeSlotsDuration=timeSlotsDuration;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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

    public String getBeginningHour() {
        return beginningHour;
    }

    public void setBeginningHour(String beginningHour) {
        this.beginningHour = beginningHour;
    }

    public String getEndingHour() {
        return endingHour;
    }

    public void setEndingHour(String endingHour) {
        this.endingHour = endingHour;
    }

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getTimeSlotsDuration() {
        return timeSlotsDuration;
    }

    public void setTimeSlotsDuration(Integer timeSlotsDuration) {
        this.timeSlotsDuration = timeSlotsDuration;
    }
    
    public List<Date> getDatesOfSchedule(){
        LocalDate bDate = LocalDate.parse(this.beginningDate);
        LocalDate eDate = LocalDate.parse(this.endingDate);
        List<LocalDate> localDatesList =  bDate.datesUntil(eDate).collect(Collectors.toList());
        localDatesList.add(eDate);
        List<Date> datesOfSchedule =  new ArrayList<>();
        for(LocalDate ld : localDatesList){
            Date date = java.sql.Date.valueOf(ld);
            datesOfSchedule.add(date);
        }
        return datesOfSchedule;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", beginningDate='" + beginningDate + '\'' +
                ", beginningHour='" + beginningHour + '\'' +
                ", endingHour='" + endingHour + '\'' +
                ", endingDate='" + endingDate + '\'' +
                ", timeSlots=" + timeSlots +
                ", capacity=" + capacity +
                ", timeSlotsDuration=" + timeSlotsDuration +
                '}';
    }
}
