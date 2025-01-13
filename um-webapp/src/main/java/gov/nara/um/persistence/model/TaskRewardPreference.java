package gov.nara.um.persistence.model;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
//@Table(name = "business_unit_config_values", schema = "oif_ods")
public class TaskRewardPreference {
    @EmbeddedId
    private TaskRewardsPreferenceID id = new TaskRewardsPreferenceID();

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("taskID")
    @JoinColumn(name="task_id", nullable=false)
    private Task taskID;


    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("taskRewardID")
    @JoinColumn(name="task_reward_id", nullable=false)
    private TaskReward taskRewardID;

    @Column(name = "task_reward_points")
    private String taskRewardPoints;

    public TaskRewardPreference() {
    }
}
