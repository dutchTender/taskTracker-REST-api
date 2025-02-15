package com.zhang.core.persistence.dto;
import com.zhang.common.interfaces.ILongNameableDto;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.HashSet;

@Data
public class UserDTO implements ILongNameableDto {
    private Long Id;
    @NotNull
    private String name;
    @NotNull
    private String user_type;
    @Email
    private String email;
    private HashSet<TaskDTO> tasks;
}
