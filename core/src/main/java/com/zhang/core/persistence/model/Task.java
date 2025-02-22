package com.zhang.core.persistence.model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.zhang.common.persistence.model.ILongNameableEntity;
import com.zhang.core.persistence.dto.TaskDTO;
//import io.swagger.annotations.ApiModelProperty;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//@Table(name = "business_unit_catalog", schema = "oif_ods")
@Table(name = "tasks")
public class Task implements  Serializable, ILongNameableEntity{

    @Id
    @Column(name = "id")
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bu_seq_gen")
    @SequenceGenerator(name = "bu_seq_gen", sequenceName = "oif_ods.business_unit_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@ApiModelProperty(hidden = true)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column
    private String taskTime;

    @Column
    private String taskDescription;

    @OneToMany(
            mappedBy = "taskID",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<TaskRewardPreference> taskRewardPreferences = new HashSet<>();

    public Task(){
    }

    public Task(TaskDTO taskDTO) {
        this.name = taskDTO.getName();
        this.taskTime = taskDTO.getTaskTime();
        this.taskDescription = taskDTO.getTaskDescription();
        this.taskRewardPreferences = new HashSet<>();
    }

    public void addTaskRewardConfiguration(TaskRewardPreference taskRewardPreference){
        taskRewardPreferences.add(taskRewardPreference);
    }
    public void removeTaskRewardConfiguration( TaskRewardPreference taskRewardPreference){
        taskRewardPreferences.removeIf(current -> current.equals(taskRewardPreference));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(getId(), task.getId()) && Objects.equals(getName(), task.getName()) && Objects.equals(getTaskTime(), task.getTaskTime()) && Objects.equals(getTaskDescription(), task.getTaskDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getTaskTime(), getTaskDescription());
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
            this.id = id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Set<TaskRewardPreference> getTaskRewardPreferences() {
        return taskRewardPreferences;
    }

    public void setTaskRewardPreferences(Set<TaskRewardPreference> taskRewardPreferences) {
        this.taskRewardPreferences = taskRewardPreferences;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", taskTime='" + taskTime + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                '}';
    }
}
