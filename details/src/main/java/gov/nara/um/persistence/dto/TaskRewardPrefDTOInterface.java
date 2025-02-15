package gov.nara.um.persistence.dto;

import gov.nara.um.persistence.model.Task;
import gov.nara.um.persistence.model.TaskReward;

public interface TaskRewardPrefDTOInterface {

    Task getTaskID();
    TaskReward getTaskRewardID();
    String getTaskRewardPoints();
}
