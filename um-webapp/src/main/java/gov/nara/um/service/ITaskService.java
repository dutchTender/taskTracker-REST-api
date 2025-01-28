package gov.nara.um.service;

import gov.nara.common.persistence.service.ILongRawService;
import gov.nara.um.persistence.model.Task;

public interface ITaskService extends ILongRawService<Task>, gov.nara.common.interfaces.IByNameApi<Task> {

    public Task addUser(final String unitId, final String userId);

    public Task removerUser(final String unitId, final String userId);

}
