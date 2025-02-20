package com.zhang.core.persistence.dto;
import com.zhang.common.interfaces.ILongNameableDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.HashSet;



@Getter @Setter
@ToString
public class TaskDTO implements ILongNameableDto {
    private Long Id;
    @NotNull
    private String name;
    private String taskTime;
    @NotNull
    private String taskDescription;
    private HashSet<TaskRewardDTO> taskRewards;

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
}
