package com.zhang.core.persistence.dto;
import com.zhang.common.interfaces.ILongNameableDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;

@Getter @Setter
@ToString
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
}
