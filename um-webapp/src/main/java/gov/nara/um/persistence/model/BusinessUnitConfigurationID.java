package gov.nara.um.persistence.model;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class BusinessUnitConfigurationID implements Serializable {

    @Column( name = "business_unit_id")
    private Integer businessUnitID;
    @Column( name = "business_unit_config_id")
    private Long businessUnitConfigID;
    public BusinessUnitConfigurationID(Integer businessUnitID, Long businessUnitConfigID) {
        this.businessUnitID = businessUnitID;
        this.businessUnitConfigID = businessUnitConfigID;
    }
    public BusinessUnitConfigurationID() {
    }


}
