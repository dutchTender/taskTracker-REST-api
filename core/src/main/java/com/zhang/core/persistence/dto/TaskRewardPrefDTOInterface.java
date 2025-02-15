package com.zhang.core.persistence.dto;

import com.zhang.core.persistence.model.Task;
import com.zhang.core.persistence.model.TaskReward;

public interface TaskRewardPrefDTOInterface {

    Task getTaskID();
    TaskReward getTaskRewardID();
    String getTaskRewardPoints();
}
