package pt.ulisboa.tecnico.museumapp.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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

    @ManyToMany(targetEntity = ArtifactEntity.class, cascade = CascadeType.ALL)
    protected List<ArtifactEntity> artifacts;


    public TimeMachineEntity(TypeOfTimeMachine type, String name ){
        this.type=type;
        this.name=name;
        this.artifacts=artifacts;
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
        this.artifacts.add(a);
    }
    @Override
    public String toString() {
        return "TimeMachineEntity{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", artifacts=" + artifacts +
                '}';
    }
}
