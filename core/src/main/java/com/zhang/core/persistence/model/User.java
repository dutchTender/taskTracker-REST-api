package com.zhang.core.persistence.model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.zhang.common.interfaces.ILongNameableDto;
import com.zhang.common.persistence.model.ILongNameableEntity;
//import io.swagger.annotations.ApiModelProperty;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
@Entity

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//@Table(name = "user", schema = "oif_ods")
@Table(name = "users")
public class User implements ILongNameableEntity, ILongNameableDto {
    @Id
    @Column(name = "user_id")
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
    @SequenceGenerator(name = "user_seq_gen", sequenceName = "oif_ods.user_user_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@ApiModelProperty(hidden = true)
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

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getName(), user.getName()) && Objects.equals(getUser_type(), user.getUser_type()) && Objects.equals(getEmail(), user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getUser_type(), getEmail());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user_type='" + user_type + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
