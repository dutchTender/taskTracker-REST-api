package com.zhang.details.persistence.dto;

import com.zhang.details.persistence.model.Task;
import com.zhang.details.persistence.model.TaskReward;

public interface TaskRewardPrefDTOInterface {

    Task getTaskID();
    TaskReward getTaskRewardID();
    String getTaskRewardPoints();
}
