package com.zhang.core.persistence.model;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class TaskRewardsPreferenceID implements Serializable {

    @Column( name = "task_id")
    private Long taskID;
    @Column( name = "task_reward_id")
    private Long taskRewardID;
    public TaskRewardsPreferenceID() {
    }

    public Long getTaskID() {
        return taskID;
    }

    public void setTaskID(Long taskID) {
        this.taskID = taskID;
    }

    public Long getTaskRewardID() {
        return taskRewardID;
    }

    public void setTaskRewardID(Long taskRewardID) {
        this.taskRewardID = taskRewardID;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TaskRewardsPreferenceID that = (TaskRewardsPreferenceID) o;
        return Objects.equals(getTaskID(), that.getTaskID()) && Objects.equals(getTaskRewardID(), that.getTaskRewardID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTaskID(), getTaskRewardID());
    }

    @Override
    public String toString() {
        return "TaskRewardsPreferenceID{" +
                "taskID=" + taskID +
                ", taskRewardID=" + taskRewardID +
                '}';
    }
}
