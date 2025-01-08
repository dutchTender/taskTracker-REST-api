package gov.nara.um.persistence.dto;
import gov.nara.common.interfaces.ILongDto;
import lombok.Data;
import java.util.HashSet;

@Data
public class UserDTO implements ILongDto {
    private Long Id;
    private String user_name;
    private String user_type;
    private HashSet<Integer> taskIDs;
}
