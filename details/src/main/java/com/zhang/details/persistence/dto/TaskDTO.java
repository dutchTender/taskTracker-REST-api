package com.zhang.details.persistence.dto;
import com.zhang.common.interfaces.ILongNameableDto;

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
