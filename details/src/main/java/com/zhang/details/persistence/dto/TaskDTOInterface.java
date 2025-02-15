package com.zhang.details.persistence.dto;

import java.util.Set;

public interface TaskDTOInterface {
     Long getId();
     String getName();
     String getTaskTime();
     void setName(String name);
     void setTaskTime(String taskTime);
     Set<TaskRewardPrefDTOInterface> getTaskRewardPreferences();
     void setTaskRewardPreferences(Set<TaskRewardPrefDTOInterface> rewards);

}
