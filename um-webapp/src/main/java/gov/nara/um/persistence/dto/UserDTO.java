package gov.nara.um.persistence.dto;
import lombok.Data;

@Data
public class UserDTO {
    private String user_name;
    private String user_type;
    private Integer[] business_unitIDs;
}
