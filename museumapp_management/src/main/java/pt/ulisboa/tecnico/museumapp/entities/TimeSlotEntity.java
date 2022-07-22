package pt.ulisboa.tecnico.museumapp.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class TimeSlotEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="slot_id")
    private Integer id;
    @Column(name = "time_slot_start")
    private Date startTime;

    @Column(name = "time_slot_end")
    private Date endTime;

    @Column(name = "date")
    private Date date;

    @Column(name = "capacity")
    private Integer capacity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private ScheduleEntity schedule;

    @OneToMany(mappedBy = "timeSlot", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<BookingEntity> bookedVisits;

    public TimeSlotEntity(){}

    public TimeSlotEntity(Date startTime, Date endTime, Date date, Integer capacity, ScheduleEntity schedule){
        this.startTime=startTime;
        this.endTime=endTime;
        this.date=date;
        this.capacity=capacity;
        this.schedule=schedule;
    }
    public TimeSlotEntity(Date startTime, Date endTime, Date date, Integer capacity){
        this.startTime=startTime;
        this.endTime=endTime;
        this.date=date;
        this.capacity=capacity;
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

    public ScheduleEntity getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleEntity schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "TimeSlotEntity{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", date=" + date +
                ", capacity=" + capacity +
                ", schedule=" + schedule +
                ", bookedVisits=" + bookedVisits +
                '}';
    }
}
