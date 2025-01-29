package gov.nara.um.service;

import gov.nara.common.persistence.service.ILongIdService;
import gov.nara.um.persistence.model.Task;

public interface ITaskService extends ILongIdService<Task> {

    public Task addUser(final String unitId, final String userId);

    public Task removerUser(final String unitId, final String userId);

}
