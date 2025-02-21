package com.zhang.core.persistence.dto;
import com.zhang.common.interfaces.ILongNameableDto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;


public class UserDTO implements ILongNameableDto {
    private Long Id;
    @NotNull
    private String name;
    @NotNull
    private String user_type;
    @Email
    private String email;
    private HashSet<TaskDTO> tasks;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(getId(), userDTO.getId()) && Objects.equals(getName(), userDTO.getName()) && Objects.equals(getUser_type(), userDTO.getUser_type()) && Objects.equals(getEmail(), userDTO.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getUser_type(), getEmail());
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HashSet<TaskDTO> getTasks() {
        return tasks;
    }

    public void setTasks(HashSet<TaskDTO> tasks) {
        this.tasks = tasks;
    }
}
