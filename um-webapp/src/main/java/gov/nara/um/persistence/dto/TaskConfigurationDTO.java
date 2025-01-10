package gov.nara.um.persistence.dto;
import gov.nara.common.interfaces.ILongDto;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class TaskConfigurationDTO implements ILongDto {
    private Long Id;
    @NotNull
    private String name;
}
