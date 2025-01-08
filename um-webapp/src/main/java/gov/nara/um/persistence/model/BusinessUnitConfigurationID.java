package gov.nara.um.persistence.model;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class BusinessUnitConfigurationID implements Serializable {

    @Column( name = "task_id")
    private Integer taskID;
    @Column( name = "business_unit_config_id")
    private Long businessUnitConfigID;
    public BusinessUnitConfigurationID(Integer taskID, Long businessUnitConfigID) {
        this.taskID = taskID;
        this.businessUnitConfigID = businessUnitConfigID;
    }
    public BusinessUnitConfigurationID() {
    }


}
