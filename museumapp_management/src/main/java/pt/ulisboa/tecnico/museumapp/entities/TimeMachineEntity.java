package pt.ulisboa.tecnico.museumapp.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
public class TimeMachineEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected Integer id;

    @Column(name = "type", nullable = false)
    protected TypeOfTimeMachine type;

    @Column(name = "name", nullable = false)
    protected String name;

    @ManyToMany(cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "artifacts",
            joinColumns = @JoinColumn(name = "timeMachine_id"),
            inverseJoinColumns = @JoinColumn(name = "artifact_id"))
    List<ArtifactEntity> artifacts;

    @Column(name = "artifactsCount")
    protected Integer artifactsCount;

    @Column(name = "capacity", nullable = false)
    protected Integer capacity;

    @Column(name = "visitTime", nullable = false)
    protected Integer visitTime;

    @Column(name="image")
    private String image;

    public TimeMachineEntity(TypeOfTimeMachine type, String name, Integer capacity){
        this.type=type;
        this.name=name;
        this.capacity=capacity;
        this.visitTime=0;
    }
    public TimeMachineEntity(TypeOfTimeMachine type, String name, Integer capacity, String image){
        this.type=type;
        this.name=name;
        this.capacity=capacity;
        this.visitTime=0;
        this.image=image;
    }

    protected TimeMachineEntity() {

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeOfTimeMachine getType() {
        return type;
    }

    public void setType(TypeOfTimeMachine type) {
        this.type = type;
    }

    public List<ArtifactEntity> getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(List<ArtifactEntity> artifacts) {
        this.artifacts = artifacts;
    }

    public void addArtifact(ArtifactEntity a){
        List<ArtifactEntity> artifactEntities = new ArrayList<>();
        if(this.artifacts!=null){
            artifactEntities.addAll(this.artifacts);
        }
        artifactEntities.add(a);
        this.setArtifacts(artifactEntities);
    }

    public Integer getArtifactsCount() {
        return artifactsCount;
    }

    public void setArtifactsCount(Integer artifactsCount) {
        this.artifactsCount = artifactsCount;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Integer visitTime) {
        this.visitTime = visitTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "TimeMachineEntity{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", artifacts=" + artifacts + '\'' +
                ", capacity=" + capacity + '\'' +
                ", visitTime=" + visitTime + '\'' +
                ", image=" + image +
                '}';
    }
}
