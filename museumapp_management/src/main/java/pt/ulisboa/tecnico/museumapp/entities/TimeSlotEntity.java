package pt.ulisboa.tecnico.museumapp.entities;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
public class TimeSlotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "slot_id")
    private Integer id;
    @Column(name = "time_slot_start")
    private Date startTime;

    @Column(name = "time_slot_end")
    private Date endTime;

    @Column(name = "date")
    private Date date;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "schedule_id")
    private Integer scheduleId;

    @OneToMany(mappedBy = "timeSlot", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<BookingEntity> bookedVisits;

    @Column(name = "time_slot_state")
    private TimeSlotState state;

    @Column(name = "name", nullable = false)
    private String name;

    public TimeSlotEntity() {
    }

    public TimeSlotEntity(Date startTime, Date endTime, Date date, Integer capacity) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.capacity = capacity;
        this.state = TimeSlotState.AVAILABLE;
        this.name=dateToName(date, startTime, endTime);
    }

    public TimeSlotEntity(Date startTime, Date endTime, Date date, Integer scheduleId, Integer capacity, TimeSlotState state) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.scheduleId=scheduleId;
        this.capacity = capacity;
        this.state = state;
        this.name=dateToName(date, startTime, endTime);
    }

    public TimeSlotEntity(Date startTime, Date endTime, Date date, Integer scheduleId, Integer capacity, TimeSlotState state, String name) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.scheduleId=scheduleId;
        this.capacity = capacity;
        this.state = state;
        this.name=name;
    }

    public Integer getId() {
        return id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }


    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
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
    public String dateToName(Date date, Date startTime, Date endTime ){
        String patternDate = "MM/dd/yyyy";
        String patternTime = "HH:mm";
        DateFormat dfDate = new SimpleDateFormat(patternDate);
        DateFormat dfTime = new SimpleDateFormat(patternTime);
        String dateAsString = dfDate.format(date);
        String startTimeAsString = dfTime.format(startTime);
        String endTimeAsString = dfTime.format(endTime);
        String name = dateAsString + ": " + startTimeAsString + " - " + endTimeAsString;
        return name;
    }

    @Override
    public String toString() {
        return "TimeSlotEntity{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", date=" + date +
                ", capacity=" + capacity +
                ", scheduleId=" + scheduleId +
                ", bookedVisits=" + bookedVisits +
                ", state=" + state +
                ", name='" + name + '\'' +
                '}';
    }
}


