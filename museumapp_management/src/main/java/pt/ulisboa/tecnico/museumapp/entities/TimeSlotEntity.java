package pt.ulisboa.tecnico.museumapp.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TimeSlotEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="slot_id")
    private Integer id;
    @Column(name = "time_slot_duration")
    private Integer timeSlotDuration;

    @Column(name = "capacity")
    private Integer capacity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "schedule_id", nullable = false)
    private ScheduleEntity schedule;

    public TimeSlotEntity(){}

    public TimeSlotEntity(Integer timeSlotDuration, Integer capacity){
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
        return "TimeSlotEntity{" +
                "id=" + id +
                ", timeSlotDuration=" + timeSlotDuration +
                ", capacity=" + capacity +
                '}';
    }
}
