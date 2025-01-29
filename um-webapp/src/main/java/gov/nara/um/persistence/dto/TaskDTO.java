package gov.nara.um.persistence.dto;
import gov.nara.common.interfaces.ILongNameableDto;

import lombok.Data;
import javax.validation.constraints.NotNull;
import java.util.HashSet;



@Data
public class TaskDTO implements ILongNameableDto {
    private Long Id;
    @NotNull
    private String name;
    private String taskTime;
    @NotNull
    private String taskDescription;
    private HashSet<TaskRewardDTO> taskRewards;
}
