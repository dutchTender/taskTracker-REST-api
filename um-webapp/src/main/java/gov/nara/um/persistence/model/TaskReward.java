package gov.nara.um.persistence.model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import gov.nara.common.persistence.model.ILongNameableEntity;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//@Table(name = "business_unit_configuration", schema = "oif_ods")
@Table(name = "task_rewards")
public class TaskReward implements Serializable, ILongNameableEntity{
    @Id
    @Column(name = "reward_id")
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bu_conf_seq_gen")
    @SequenceGenerator(name = "bu_conf_seq_gen", sequenceName = "oif_ods.business_unit_configuration_configuration_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@ApiModelProperty(hidden = true)
    private Long id;

    @Column(name = "reward_name", unique = true, nullable = false)
    private String name;

    @OneToMany(
            mappedBy = "taskRewardID",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<TaskRewardPreference> taskRewardPreferences = new HashSet<>();

    public TaskRewardPreference addTaskRewardConfig(TaskRewardPreference taskRewardPreference){
        taskRewardPreferences.add(taskRewardPreference);
        return taskRewardPreference;
    }
    public void removeTaskRewardConfig( TaskRewardPreference taskRewardPreference){
        for(Iterator<TaskRewardPreference> iterBUCP = taskRewardPreferences.iterator(); iterBUCP.hasNext(); ) {
            TaskRewardPreference current = iterBUCP.next();
            if(current.equals(taskRewardPreference)){
                iterBUCP.remove();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TaskReward that = (TaskReward) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
