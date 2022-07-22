package pt.ulisboa.tecnico.museumapp.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="schedule_id")
    private Integer id;

    @Column(name = "begining_date")
    private Date beginingDate;

    @Column(name = "ending_date")
    private Date endingDate;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "time_slots_duration")
    private Integer timeSlotsDuration;

    @OneToMany(mappedBy = "schedule", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    List<TimeSlotEntity> timeSlots;

    public ScheduleEntity(){}

    public ScheduleEntity(Date beginingDate, Date endingDate, Integer capacity, Integer timeSlotsDuration){
        this.beginingDate=beginingDate;
        this.endingDate=endingDate;
        this.capacity=capacity;
        this.timeSlotsDuration=timeSlotsDuration;
    }

    public Integer getId() {
        return id;
    }

    public Date getBeginingDate() {
        return beginingDate;
    }

    public void setBeginingDate(Date beginingDate) {
        this.beginingDate = beginingDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
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

    @Override
    public String toString() {
        return "ScheduleEntity{" +
                "id=" + id +
                ", beginingDate=" + beginingDate +
                ", endingDate=" + endingDate +
                ", capacity=" + capacity +
                ", timeSlotsDuration=" + timeSlotsDuration +
                '}';
    }
}
