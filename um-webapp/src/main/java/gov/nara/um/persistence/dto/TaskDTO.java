package gov.nara.um.persistence.dto;
import gov.nara.common.interfaces.IDto;
import lombok.Data;
import javax.validation.constraints.NotNull;
import java.util.List;


@Data
public class TaskDTO implements IDto {
    private Integer Id;
    @NotNull
    private String name;
    private String taskTime;
    @NotNull
    private String taskDescription;
    private List<TaskRewardDTO> taskRewards;
}
