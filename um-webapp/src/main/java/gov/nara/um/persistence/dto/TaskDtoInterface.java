package gov.nara.um.persistence.dto;

import javax.validation.constraints.NotNull;

public interface TaskDtoInterface {
     String getName();
     String getTaskTime();
     void setName(String name);
     void setTaskTime(String taskTime);

}
