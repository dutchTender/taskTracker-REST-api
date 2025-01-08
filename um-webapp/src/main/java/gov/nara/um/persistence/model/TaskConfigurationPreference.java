package gov.nara.um.persistence.model;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
//@Table(name = "business_unit_config_values", schema = "oif_ods")
public class TaskConfigurationPreference {
    @EmbeddedId
    private BusinessUnitConfigurationID id = new BusinessUnitConfigurationID();

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("taskID")
    @JoinColumn(name="task_id", nullable=false)
    private Task taskID;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("businessUnitConfigID")
    @JoinColumn(name="task_config_id", nullable=false)
    private BusinessUnitConfiguration taskConfigID;

    @Column(name = "configuration_value")
    private String configurationValue;

    public TaskConfigurationPreference() {
    }
    public TaskConfigurationPreference(Task taskID, BusinessUnitConfiguration businessUnitConfigID) {
        this.taskID = taskID;
        this.taskConfigID = businessUnitConfigID;
    }
}
