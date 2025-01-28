package gov.nara.um.persistence.model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import gov.nara.common.persistence.model.ILongNameableEntity;
import gov.nara.um.persistence.dto.TaskDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//@Table(name = "business_unit_catalog", schema = "oif_ods")
@Table(name = "tasks")
public class Task implements  Serializable, ILongNameableEntity{

    @Id
    @Column(name = "id")
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bu_seq_gen")
    @SequenceGenerator(name = "bu_seq_gen", sequenceName = "oif_ods.business_unit_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(hidden = true)
    private Long id;

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
    private List<TaskRewardPreference> taskRewardPreferences = new ArrayList<>();
    public Task(){
    }
    public Task(TaskDTO taskDTO){
             name = taskDTO.getName();
             taskTime = taskDTO.getTaskTime();
             taskDescription = taskDTO.getTaskDescription();
             id = taskDTO.getId();
    }
    public TaskRewardPreference addTaskRewardConfiguration(TaskRewardPreference taskRewardPreference){
        taskRewardPreferences.add(taskRewardPreference);
        return taskRewardPreference;
    }
    public void removeTaskRewardConfiguration( TaskRewardPreference taskRewardPreference){
        for(Iterator<TaskRewardPreference> iterBUCP = taskRewardPreferences.iterator(); iterBUCP.hasNext(); ) {
            TaskRewardPreference current = iterBUCP.next();
            if(current.equals(taskRewardPreference)){
                iterBUCP.remove();
            }
        }
    }
}
