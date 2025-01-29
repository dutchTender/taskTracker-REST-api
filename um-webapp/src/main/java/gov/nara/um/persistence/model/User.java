package gov.nara.um.persistence.model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import gov.nara.common.interfaces.ILongNameableDto;
import gov.nara.common.persistence.model.ILongNameableEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
//@Table(name = "user", schema = "oif_ods")
@Table(name = "user")
public class User implements ILongNameableEntity, ILongNameableDto {
    @Id
    @Column(name = "user_id")
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
    @SequenceGenerator(name = "user_seq_gen", sequenceName = "oif_ods.user_user_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(hidden = true)
    private Long id;
    @Column(name = "user_name")
    private String name;
    private String user_type;
    private String email;

    @JoinTable(
            name = "user_tasks",                     // table name for the user-tasks relational table
            joinColumns = @JoinColumn(
                    name = "user_id",                // user-tasks relational table column name
                    referencedColumnName = "user_id" // column name to be mapped   to be mapped to column   (on the user db table)
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "task_id",                // user-tasks relational table column name
                    referencedColumnName = "id"      // column name to be mapped (on the task db table)
            )
    )
    @OneToMany
    private Set<Task> tasks = new HashSet<>();



    public Task addTask(Task task){ tasks.add(task);return task; }
    public void removeTask(Task task){ tasks.remove(task); }
}
