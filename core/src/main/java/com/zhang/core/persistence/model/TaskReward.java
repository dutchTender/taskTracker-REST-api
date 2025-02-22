package com.zhang.core.persistence.model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import com.zhang.common.persistence.model.ILongNameableEntity;
//import io.swagger.annotations.ApiModelProperty;


import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//@Table(name = "business_unit_configuration", schema = "oif_ods")
@Table(name = "task_rewards")
public class TaskReward implements Serializable, ILongNameableEntity{
    @Id
    @Column(name = "reward_id")
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bu_conf_seq_gen")
    @SequenceGenerator(name = "bu_conf_seq_gen", sequenceName = "oif_ods.business_unit_configuration_configuration_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@ApiModelProperty(hidden = true)
    private Long id;

    @Column(name = "reward_name", unique = true, nullable = false)
    private String name;

    @OneToMany(
            mappedBy = "taskRewardID",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<TaskRewardPreference> taskRewardPreferences = new HashSet<>();

    public void addTaskRewardConfig(TaskRewardPreference taskRewardPreference){
        taskRewardPreferences.add(taskRewardPreference);
    }
    public void removeTaskRewardConfig( TaskRewardPreference taskRewardPreference){
     taskRewardPreferences.remove(taskRewardPreference);
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

    public Set<TaskRewardPreference> getTaskRewardPreferences() {
        return taskRewardPreferences;
    }

    public void setTaskRewardPreferences(Set<TaskRewardPreference> taskRewardPreferences) {
        this.taskRewardPreferences = taskRewardPreferences;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TaskReward that = (TaskReward) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "TaskReward{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
