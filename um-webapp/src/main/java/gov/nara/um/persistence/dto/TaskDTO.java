package gov.nara.um.persistence.dto;
import gov.nara.common.interfaces.ILongNameableDto;

import lombok.Data;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


@Data
public class TaskDTO implements ILongNameableDto, Serializable {
    private Long Id;
    @NotNull
    private String name;
    private String taskTime;
    @NotNull
    private String taskDescription;
    private List<TaskRewardDTO> taskRewards;
}
