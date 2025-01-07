package gov.nara.um.persistence.dto;
import lombok.Data;
@Data
public class BUnitDTO {
    private String name;
    private String org_code;
    private String ldapName;
    private Long[] BUnitConfigurationIDs;
}
