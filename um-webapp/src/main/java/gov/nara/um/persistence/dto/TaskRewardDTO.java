package gov.nara.um.persistence.dto;
import gov.nara.common.interfaces.ILongNameableDto;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class TaskRewardDTO implements ILongNameableDto {
    private Long Id;
    @NotNull
    private String name;
}
