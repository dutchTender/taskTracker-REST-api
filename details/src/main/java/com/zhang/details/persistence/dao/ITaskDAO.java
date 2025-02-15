package com.zhang.details.persistence.dao;

import com.zhang.common.interfaces.IByNameApi;
import com.zhang.details.persistence.dto.TaskDTOInterface;
import com.zhang.details.persistence.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ITaskDAO extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task>, IByNameApi<Task> {
        public Task findByName(String name);

        public TaskDTOInterface findByNameIgnoreCase(String name);
}
