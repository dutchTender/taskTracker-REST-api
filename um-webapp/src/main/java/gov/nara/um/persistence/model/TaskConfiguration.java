package gov.nara.um.persistence.model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import gov.nara.common.interfaces.ILongNameableDto;
import gov.nara.common.persistence.model.ILongNameableEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//@Table(name = "business_unit_configuration", schema = "oif_ods")
@Table(name = "task_configuration")
public class TaskConfiguration implements ILongNameableEntity, ILongNameableDto {
    @Id
    @Column(name = "configuration_id")
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bu_conf_seq_gen")
    @SequenceGenerator(name = "bu_conf_seq_gen", sequenceName = "oif_ods.business_unit_configuration_configuration_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(hidden = true)
    private Long id;

    @Column(name = "configuration_name", unique = true, nullable = false)
    private String name;

    @OneToMany(
            mappedBy = "taskConfigID",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<TaskConfigurationPreference> tasksConfigurationPreferences = new ArrayList<>();
    public TaskConfigurationPreference addTasksConfigurationPreference(TaskConfigurationPreference taskConfigurationPreference){
        tasksConfigurationPreferences.add(taskConfigurationPreference);
        return taskConfigurationPreference;
    }
    public void removeTasksConfigurationPreference( TaskConfigurationPreference taskConfigurationPreference){
        for(Iterator<TaskConfigurationPreference> iterBUCP = tasksConfigurationPreferences.iterator(); iterBUCP.hasNext(); ) {
            TaskConfigurationPreference current = iterBUCP.next();
            if(current.equals(taskConfigurationPreference)){
                iterBUCP.remove();
            }
        }
    }
    public TaskConfiguration(){
    }

}
