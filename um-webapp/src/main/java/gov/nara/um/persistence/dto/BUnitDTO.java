package gov.nara.um.persistence.dto;
import gov.nara.common.interfaces.IDto;
import lombok.Data;
import java.util.List;


@Data
public class BUnitDTO implements IDto {
    private Integer Id;
    private String name;
    private String org_code;
    private String ldapName;
    private List<Long> BUnitConfigurationIDs;
}
