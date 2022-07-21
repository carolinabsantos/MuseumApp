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

    @OneToMany(mappedBy = "schedule", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<TimeSlotEntity> timeSlots;

    public ScheduleEntity(){}

    public ScheduleEntity(Date beginingDate, Date endingDate, List<TimeSlotEntity> timeSlots){
        this.beginingDate=beginingDate;
        this.endingDate=endingDate;
        this.timeSlots=timeSlots;
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

    public List<TimeSlotEntity> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<TimeSlotEntity> timeSlots) {
        this.timeSlots = timeSlots;
    }

    @Override
    public String toString() {
        return "ScheduleEntity{" +
                "id=" + id +
                ", beginingDate=" + beginingDate +
                ", endingDate=" + endingDate +
                ", timeSlots=" + timeSlots +
                '}';
    }
}
