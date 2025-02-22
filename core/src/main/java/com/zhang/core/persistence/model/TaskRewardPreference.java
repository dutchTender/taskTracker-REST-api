package com.zhang.core.persistence.model;


import javax.persistence.*;
import java.util.Objects;

@Entity

//@Table(name = "business_unit_config_values", schema = "oif_ods")
public class TaskRewardPreference {
    @EmbeddedId
    private TaskRewardsPreferenceID id = new TaskRewardsPreferenceID();

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("taskID")
    @JoinColumn(name="task_id", nullable=false)
    private Task taskID;


    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("taskRewardID")
    @JoinColumn(name="task_reward_id", nullable=false)
    private TaskReward taskRewardID;

    @Column(name = "task_reward_points")
    private String taskRewardPoints;

    public TaskRewardPreference() {
    }

    public TaskRewardsPreferenceID getId() {
        return id;
    }

    public void setId(TaskRewardsPreferenceID id) {
        this.id = id;
    }

    public Task getTaskID() {
        return taskID;
    }

    public void setTaskID(Task taskID) {
        this.taskID = taskID;
    }

    public TaskReward getTaskRewardID() {
        return taskRewardID;
    }

    public void setTaskRewardID(TaskReward taskRewardID) {
        this.taskRewardID = taskRewardID;
    }

    public String getTaskRewardPoints() {
        return taskRewardPoints;
    }

    public void setTaskRewardPoints(String taskRewardPoints) {
        this.taskRewardPoints = taskRewardPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TaskRewardPreference that = (TaskRewardPreference) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getTaskID(), that.getTaskID()) && Objects.equals(getTaskRewardID(), that.getTaskRewardID()) && Objects.equals(getTaskRewardPoints(), that.getTaskRewardPoints());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTaskID(), getTaskRewardID(), getTaskRewardPoints());
    }

    @Override
    public String toString() {
        return "TaskRewardPreference{" +
                "id=" + id +
                ", taskID=" + taskID +
                ", taskRewardID=" + taskRewardID +
                ", taskRewardPoints='" + taskRewardPoints + '\'' +
                '}';
    }
}
