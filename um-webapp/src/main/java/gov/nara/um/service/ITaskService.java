package gov.nara.um.service;

import gov.nara.common.persistence.service.IService;
import gov.nara.um.persistence.model.Task;

public interface ITaskService extends IService<Task> {

    public Task addUser(final String unitId, final String userId);

    public Task removerUser(final String unitId, final String userId);

}
