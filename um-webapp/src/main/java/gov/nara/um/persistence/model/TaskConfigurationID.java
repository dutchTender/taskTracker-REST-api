package gov.nara.um.persistence.model;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class TaskConfigurationID implements Serializable {

    @Column( name = "task_id")
    private Integer taskID;
    @Column( name = "task_config_id")
    private Long taskConfigID;
    public TaskConfigurationID() {
    }


}
