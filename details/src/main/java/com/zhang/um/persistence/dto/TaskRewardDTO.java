package com.zhang.um.persistence.dto;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class TaskRewardDTO {
    private Long Id;
    @NotNull
    private String name;
}
