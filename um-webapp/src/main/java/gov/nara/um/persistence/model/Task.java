package gov.nara.um.persistence.model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import gov.nara.common.interfaces.INameableDto;
import gov.nara.common.persistence.model.INameableEntity;
import gov.nara.um.persistence.dto.TaskDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//@Table(name = "business_unit_catalog", schema = "oif_ods")
@Table(name = "tasks")
public class Task implements INameableEntity, INameableDto {

    @Id
    @Column(name = "id")
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bu_seq_gen")
    @SequenceGenerator(name = "bu_seq_gen", sequenceName = "oif_ods.business_unit_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(hidden = true)
    private Integer id;

    @Column(unique = true)
    private String name;

    @Column
    private String taskTime;

    @Column
    private String taskDescription;

    @OneToMany(
            mappedBy = "taskID",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<TaskRewardConfig> taskRewardConfigs = new ArrayList<>();
    public Task(){
    }
    public Task(TaskDTO taskDTO){
             name = taskDTO.getName();
             taskTime = taskDTO.getTaskTime();
             taskDescription = taskDTO.getTaskDescription();
             id = taskDTO.getId();
    }
    public TaskRewardConfig addTaskRewardConfiguration(TaskRewardConfig taskRewardConfig){
        taskRewardConfigs.add(taskRewardConfig);
        return taskRewardConfig;
    }
    public void removeTaskRewardConfiguration( TaskRewardConfig taskRewardConfig){
        for(Iterator<TaskRewardConfig> iterBUCP = taskRewardConfigs.iterator(); iterBUCP.hasNext(); ) {
            TaskRewardConfig current = iterBUCP.next();
            if(current.equals(taskRewardConfig)){
                iterBUCP.remove();
            }
        }
    }
}
