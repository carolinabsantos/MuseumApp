package pt.ulisboa.tecnico.museumapp.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
public class VisitEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "timeMachine_id")
    protected TimeMachineEntity timeMachine;

    @Column(name = "start_time", nullable = false)
    protected String startTime;

    @Column(name = "end_time", nullable = false)
    protected String endTime;

    @Column(name = "state", nullable = false)
    protected State state;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "visitor_id", nullable = false)
    protected VisitorEntity visitor;

    @Column(name = "visit_date", nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String visitDate;

    @Column(name = "time_slot_id")
    private Integer timeSlotId;

    @Column(name = "observations", nullable = false)
    protected String observations;

    @Column(name = "exhibitors_names")
    protected String exhibitors_names;

    @Column(name = "exhibitors_counter")
    private Integer exhibitors_counter;
    public VisitEntity(TimeMachineEntity timeMachine, VisitorEntity visitor, Integer timeSlotId, String observations, String visitDate){
        this.timeMachine=timeMachine;
        this.startTime="Not_started";
        this.endTime="Not_started";
        this.state=State.TO_START;
        this.visitor=visitor;
        this.timeSlotId=timeSlotId;
        this.observations=observations;
        this.visitDate=visitDate;
        this.exhibitors_names="";
        this.exhibitors_counter=0;
    }

    public VisitEntity() {
    }

    public VisitEntity(TimeMachineEntity timeMachine, VisitorEntity visitor, String observations, String visitDate) {
        this.timeMachine=timeMachine;
        this.startTime="Not_started";
        this.endTime="Not_started";
        this.state=State.TO_START;
        this.visitor=visitor;
        this.observations=observations;
        this.visitDate=visitDate;
        this.timeSlotId=0;
        this.exhibitors_names="";
        this.exhibitors_counter=0;
    }

    public Integer getId() {
        return id;
    }

    public TimeMachineEntity getTimeMachine() {
        return timeMachine;
    }

    public void setTimeMachine(TimeMachineEntity timeMachine) {
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

    public VisitorEntity getVisitor() {
        return visitor;
    }

    public void setVisitor(VisitorEntity visitor) {
        this.visitor = visitor;
    }

    public String getObservations(){
        return observations;
    }
    public void setObservations(String observations){
        this.observations=observations;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public Integer getTimeSlotId() {
        return timeSlotId;
    }

    public void setTimeSlotId(Integer timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    public String getExhibitors() {
        return exhibitors_names;
    }

    public void setExhibitors() {
        String exhibitorsList = "";
        String result = "";
        Integer exhibitors_count = 0;
        for (ArtifactEntity a : this.getTimeMachine().getArtifacts()){
            System.out.println("Time Machine: " + this.getTimeMachine().getName() + "; Artifact name: " + a.getName() + ";  Exhibitor: " + a.getExhibitor());
            String ex = a.getExhibitor();
            if(exhibitorsList.equals("")){
                result = exhibitorsList.concat(ex);
                exhibitors_count+=1;
            }
            else if(!(exhibitorsList.contains(ex))){
                result = exhibitorsList.concat("-" + ex);
                exhibitors_count+=1;
            }
            exhibitorsList = result;
        }
        System.out.println("Exhibitors List: " + result);
        System.out.println("Exhibitors Counter: " + exhibitors_count);
        this.exhibitors_names = result;
    }

    public Integer getExhibitors_counter(){
        return this.exhibitors_counter;
    }
    public void setExhibitors_counter(Integer exhibitors_counter) {
        this.exhibitors_counter = exhibitors_counter;
    }

    @Override
    public String toString() {
        return "VisitEntity{" +
                "id=" + id +
                ", timeMachine=" + timeMachine +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", state=" + state + '\'' +
                ", visitor=" + visitor + '\'' +
                ", visit date=" + visitDate + '\'' +
                ", observations=" + observations + '\'' +
                ", timeSlotId=" + timeSlotId + '\'' +
                ", exhibitors visited=" + exhibitors_names + '\'' +
                ", exhibitors counter=" + exhibitors_counter +
                '}';
    }
}
