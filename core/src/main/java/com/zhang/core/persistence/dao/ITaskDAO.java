package com.zhang.core.persistence.dao;

import com.zhang.common.interfaces.generics.IByNameApi;
import com.zhang.core.persistence.dto.TaskDTOInterface;
import com.zhang.core.persistence.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ITaskDAO extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task>, IByNameApi<Task> {
        public Task findByName(String name);

        public TaskDTOInterface findByNameIgnoreCase(String name);
}
