package pt.ulisboa.tecnico.museumapp.models;

import pt.ulisboa.tecnico.museumapp.entities.TypeOfTimeMachine;

import java.sql.Timestamp;
import java.util.List;

public class TimeMachine {
    private TypeOfTimeMachine type;
    private String name;
    private List<Artifact> artifacts;
    private Integer artifactsCount;
    private Integer capacity;

    public TimeMachine(){}

    public TimeMachine(TypeOfTimeMachine type, String name,List<Artifact> artifacts, Integer capacity){
        this.type=type;
        this.name=name;
        this.artifacts=artifacts;
        this.capacity=capacity;
    }

    public TimeMachine(TypeOfTimeMachine type, String name, Integer capacity){
        this.type=type;
        this.name=name;
        this.capacity=capacity;
    }

    public TypeOfTimeMachine getType() {
        return type;
    }

    public void setType(TypeOfTimeMachine type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Artifact> getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(List<Artifact> artifacts) {
        this.artifacts = artifacts;
    }

    public Integer getArtifactsCount() {
        return artifactsCount;
    }

    public void setArtifactCount(){
        this.artifactsCount=this.artifacts.size();
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "TimeMachine{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", artifacts=" + artifacts + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
