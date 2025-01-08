package gov.nara.um.persistence.dao;

import gov.nara.common.interfaces.IByNameApi;
import gov.nara.um.persistence.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ITaskDAO extends JpaRepository<Task, Integer>, JpaSpecificationExecutor<Task>, IByNameApi<Task> {
            public Task findByName(String name);

}
