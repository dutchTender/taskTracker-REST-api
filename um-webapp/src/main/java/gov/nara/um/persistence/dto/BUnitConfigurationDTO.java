package gov.nara.um.persistence.dto;
import gov.nara.common.interfaces.ILongDto;
import lombok.Data;
@Data
public class BUnitConfigurationDTO implements ILongDto {
    private Long Id;
    private String name;
}
