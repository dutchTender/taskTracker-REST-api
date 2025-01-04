package gov.nara.um.persistence.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class BusinessUnitDTO {

    private String name;
    private String org_code;
    private String ldapName;
    private Long[] BUnitConfigurationIDs;

}
