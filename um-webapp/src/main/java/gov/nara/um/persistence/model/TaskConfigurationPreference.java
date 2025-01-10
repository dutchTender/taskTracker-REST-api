package gov.nara.um.persistence.model;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
//@Table(name = "business_unit_config_values", schema = "oif_ods")
public class TaskConfigurationPreference {
    @EmbeddedId
    private TaskConfigurationID id = new TaskConfigurationID();

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("taskID")
    @JoinColumn(name="task_id", nullable=false)
    private Task taskID;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("taskConfigID")
    @JoinColumn(name="task_config_id", nullable=false)
    private TaskConfiguration taskConfigID;

    @Column(name = "configuration_value")
    private String configurationValue;

    public TaskConfigurationPreference() {
    }
    public TaskConfigurationPreference(Task taskID, TaskConfiguration businessUnitConfigID) {
        this.taskID = taskID;
        this.taskConfigID = businessUnitConfigID;
    }
}
