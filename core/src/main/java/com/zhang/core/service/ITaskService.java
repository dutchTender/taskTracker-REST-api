package com.zhang.core.service;

import com.zhang.common.interfaces.generics.service.ILongIdService;
import com.zhang.core.persistence.model.Task;

public interface ITaskService extends ILongIdService<Task> {

    public Task findTaskReference(final Long taskId);
}
