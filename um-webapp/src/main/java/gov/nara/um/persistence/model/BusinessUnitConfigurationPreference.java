package gov.nara.um.persistence.model;
import gov.nara.common.interfaces.IWithName;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
@Entity
@Data
//@Table(name = "business_unit_config_values", schema = "oif_ods")
public class BusinessUnitConfigurationPreference {
    @EmbeddedId
    private BusinessUnitConfigurationID id = new BusinessUnitConfigurationID();

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("businessUnitID")
    @JoinColumn(name="business_unit_catalog_id", nullable=false)
    private BusinessUnit businessUnitID;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("businessUnitConfigID")
    @JoinColumn(name="business_unit_config_id", nullable=false)
    private BusinessUnitConfiguration businessUnitConfigID;

    @Column(name = "configuration_value")
    private String configurationValue;

    public BusinessUnitConfigurationPreference() {
    }
    public BusinessUnitConfigurationPreference(BusinessUnit businessUnitID, BusinessUnitConfiguration businessUnitConfigID) {
        this.businessUnitID = businessUnitID;
        this.businessUnitConfigID = businessUnitConfigID;
    }
}
