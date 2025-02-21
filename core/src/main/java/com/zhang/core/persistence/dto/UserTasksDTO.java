package com.zhang.core.persistence.dto;


import java.util.Objects;

public class UserTasksDTO {
    private Long user_id;
    private Long task_id;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getTask_id() {
        return task_id;
    }

    public void setTask_id(Long task_id) {
        this.task_id = task_id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserTasksDTO that = (UserTasksDTO) o;
        return Objects.equals(getUser_id(), that.getUser_id()) && Objects.equals(getTask_id(), that.getTask_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser_id(), getTask_id());
    }

    @Override
    public String toString() {
        return "UserTasksDTO{" +
                "user_id=" + user_id +
                ", task_id=" + task_id +
                '}';
    }
}
