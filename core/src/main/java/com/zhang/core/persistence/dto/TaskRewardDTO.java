package com.zhang.core.persistence.dto;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class TaskRewardDTO {
    private Long Id;
    @NotNull
    private String name;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TaskRewardDTO that = (TaskRewardDTO) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "TaskRewardDTO{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                '}';
    }
}
