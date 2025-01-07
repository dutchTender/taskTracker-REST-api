package gov.nara.um.persistence.dto;
import lombok.Data;
import java.util.HashSet;

@Data
public class UserDTO {
    private String user_name;
    private String user_type;
    private HashSet<Integer> business_unitIDs;
}
