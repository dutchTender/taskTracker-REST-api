package com.zhang.core.persistence.dto;
import com.zhang.common.base.rest.response.AbstractAPIResponse;
import com.zhang.common.interfaces.dto.ILongNameableDto;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;


public class TaskDTO extends AbstractAPIResponse<TaskDTO> implements ILongNameableDto {
    private Long Id;
    @NotNull
    private String name;
    private String taskTime;
    @NotNull
    private String taskDescription;
    private HashSet<TaskRewardDTO> taskRewards;

    @Override
    public Long getId() {
        return this.Id;
    }

    @Override
    public void setId(Long id) {
        this.Id = id;
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

    public HashSet<TaskRewardDTO> getTaskRewards() {
        return taskRewards;
    }

    public void setTaskRewards(HashSet<TaskRewardDTO> taskRewards) {
        this.taskRewards = taskRewards;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TaskDTO taskDTO = (TaskDTO) o;
        return Objects.equals(getId(), taskDTO.getId()) && Objects.equals(getName(), taskDTO.getName()) && Objects.equals(getTaskTime(), taskDTO.getTaskTime()) && Objects.equals(getTaskDescription(), taskDTO.getTaskDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getTaskTime(), getTaskDescription());
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", taskTime='" + taskTime + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                '}';
    }
}
