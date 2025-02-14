package gov.nara.um.persistence.dto;
import gov.nara.um.persistence.model.TaskRewardPreference;

import java.util.Set;

public interface TaskDTOInterface {
     Long getId();
     String getName();
     String getTaskTime();
     void setName(String name);
     void setTaskTime(String taskTime);
     Set<TaskRewardPreference> getRewards();
     void setRewards(Set<TaskRewardPreference> rewards);

}
