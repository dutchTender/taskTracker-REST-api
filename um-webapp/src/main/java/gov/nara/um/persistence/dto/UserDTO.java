package gov.nara.um.persistence.dto;
import gov.nara.common.interfaces.ILongDto;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.HashSet;

@Data
public class UserDTO implements ILongDto {
    private Long Id;
    @NotNull
    private String user_name;
    @NotNull
    private String user_type;
    @Email
    private String email;
    private HashSet<TaskDTO> tasks;
}
