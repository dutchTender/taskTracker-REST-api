package gov.nara.um.persistence.dto;
import gov.nara.common.interfaces.ILongDto;
import lombok.Data;
@Data
public class TaskConfigurationDTO implements ILongDto {
    private Long Id;
    private String name;
}
