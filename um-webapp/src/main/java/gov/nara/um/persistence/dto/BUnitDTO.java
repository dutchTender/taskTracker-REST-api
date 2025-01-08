package gov.nara.um.persistence.dto;
import gov.nara.common.interfaces.IDto;
import lombok.Data;
import java.util.List;


@Data
public class BUnitDTO implements IDto {
    private Integer Id;
    private String name;
    private String taskTime;
    private String taskDescription;
    private List<Long> taskConfigurationIDs;
}
