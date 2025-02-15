package com.zhang.um.persistence.dto;

import com.zhang.um.persistence.model.Task;
import com.zhang.um.persistence.model.TaskReward;

public interface TaskRewardPrefDTOInterface {

    Task getTaskID();
    TaskReward getTaskRewardID();
    String getTaskRewardPoints();
}
