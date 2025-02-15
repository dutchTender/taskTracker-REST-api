package com.zhang.um.service;

import com.zhang.common.persistence.service.ILongIdService;
import com.zhang.um.persistence.model.Task;

public interface ITaskService extends ILongIdService<Task> {

    public Task addUser(final String unitId, final String userId);

    public Task removerUser(final String unitId, final String userId);
    public Task findTaskReference(final Long taskId);
}
