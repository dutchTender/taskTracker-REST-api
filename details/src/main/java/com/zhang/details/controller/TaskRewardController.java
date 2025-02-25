package com.zhang.details.controller;

import com.zhang.common.base.rest.response.AbstractAPIResponse;
import com.zhang.common.base.rest.response.AbstractRestMetaData;
import com.zhang.common.base.rest.response.AbstractRestResponse;
import com.zhang.common.base.rest.response.RestResponseMessage;
import com.zhang.common.base.rest.validate.QueryConstants;
import com.zhang.common.base.controller.AbstractLongIdController;
import com.zhang.common.interfaces.generics.controller.ILongIdSortingController;
import com.zhang.core.persistence.dto.TaskDTO;
import com.zhang.core.persistence.dto.TaskRewardDTO;
import com.zhang.core.persistence.model.TaskReward;
import com.zhang.core.service.ITaskRewardService;
import com.zhang.details.service.DTOService;
import com.zhang.details.util.UmMappings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = UmMappings.TASKS_REWARDS)
@CrossOrigin(origins = "*")
public class TaskRewardController extends AbstractLongIdController<TaskReward> implements ILongIdSortingController<TaskReward> {


    private final ITaskRewardService service;
    private final DTOService dtoService;

    public TaskRewardController(ITaskRewardService service, DTOService dtoService) {
        this.service = service;
        this.dtoService = dtoService;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // find - all/paginated and sorted
    // Unit testing  : NA
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public List<TaskReward> findAllPaginatedAndSorted(final int page, final int size, final String sortBy, final String sortOrder) {
        return findPaginatedAndSortedInternal(page, size, sortBy, sortOrder);
    }
    @RequestMapping(params = { QueryConstants.PAGE, QueryConstants.SIZE, QueryConstants.SORT_BY }, method = RequestMethod.GET)
    @ResponseBody
    public List<TaskRewardDTO> findAllPaginatedAndSortedDTO(@RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size, @RequestParam(value = QueryConstants.SORT_BY) final String sortBy,
                                                            @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder) {
        List<TaskReward> bUnitConfigList =  findAllPaginatedAndSorted(page, size, sortBy, sortOrder);
        return dtoService.buildDTOListFromConfigurationList(Optional.ofNullable(bUnitConfigList));
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // find - all/paginated
    // Unit testing  : NA
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public List<TaskReward> findAllPaginated(final int page, final int size) {
        return findPaginatedInternal(page, size);
    }
    @RequestMapping(params = { QueryConstants.PAGE, QueryConstants.SIZE }, method = RequestMethod.GET)
    @ResponseBody
    public List<TaskRewardDTO> findAllPaginatedDTO(@RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size) {
        List<TaskReward> taskConfigList = findAllPaginated(page, size);
        return dtoService.buildDTOListFromConfigurationList(Optional.ofNullable(taskConfigList));
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // find - all/sorted
    // Unit testing  : DONE
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public List<TaskReward> findAllSorted(@RequestParam(value = QueryConstants.SORT_BY) final String sortBy, @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder) {
        return findAllSortedInternal(sortBy, sortOrder);
    }
    @RequestMapping(params = { QueryConstants.SORT_BY }, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<AbstractRestResponse<List<TaskRewardDTO>>> findAllSortedDTO(@RequestParam(value = QueryConstants.SORT_BY) final String sortBy, @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder) {
        List<TaskReward> taskRewardsList =  findAllSorted(sortBy, sortOrder);
        AbstractRestMetaData metaData = new AbstractRestMetaData("http://localhost:8082/api/tasks-rweards/", "params: sort by - {" +sortBy+" }" + " sort order - { "+sortOrder+" } total tasks : "+taskRewardsList.size());
        AbstractAPIResponse<List<TaskRewardDTO>> apiResponse = new AbstractAPIResponse<>();
        return apiResponse.createAPISuccessResponse(dtoService.buildDTOListFromConfigurationList(Optional.ofNullable(taskRewardsList)), metaData, RestResponseMessage.TASK_REWARDS_GET_SUCCESS);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // find - all
    // Unit testing  : NA
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public List<TaskReward> findAll(final HttpServletRequest request) {
        return findAllInternal(request);
    }
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<AbstractRestResponse<List<TaskRewardDTO>>> findAllDTO(final HttpServletRequest request) {
        List<TaskReward> taskRewardsList = findAll(request);
        AbstractRestMetaData metaData = new AbstractRestMetaData("http://localhost:8082/api/tasks-rweards/", "total tasks : "+taskRewardsList.size());
        AbstractAPIResponse<List<TaskRewardDTO>> apiResponse = new AbstractAPIResponse<>();
        return apiResponse.createAPISuccessResponse(dtoService.buildDTOListFromConfigurationList(Optional.ofNullable(taskRewardsList)), metaData, RestResponseMessage.TASK_REWARDS_GET_SUCCESS);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // find - one
    // Unit testing  : NA
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public TaskReward findOne(final Long id) {
        return findOneInternal(id);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<AbstractRestResponse<TaskRewardDTO>> findOneDTO(@PathVariable("id") final Long id) {
        AbstractRestMetaData metaData = new AbstractRestMetaData("http://localhost:8082/api/tasks-rweards/", "total tasks : N/A");
        AbstractAPIResponse<TaskRewardDTO> apiResponse = new AbstractAPIResponse<>();
        return apiResponse.createAPISuccessResponse(dtoService.buildDTOFromTaskReward(findOne(id)), metaData, RestResponseMessage.TASK_REWARD_GET_SUCCESS);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // create - one
    // Unit testing  : NA
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public TaskReward create(@RequestBody final TaskReward resource) {
        return  createInternal(resource);
    }
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AbstractRestResponse<TaskRewardDTO>> createDTO(@RequestBody @Valid final TaskRewardDTO resource) {
        TaskReward createdReward = create(dtoService.buildTaskConfigurationFromDTO(resource));
        AbstractRestMetaData metaData = new AbstractRestMetaData("http://localhost:8082/api/tasks-rweards/"+createdReward.getId(), "total tasks : N/A");
        AbstractAPIResponse<TaskRewardDTO> apiResponse = new AbstractAPIResponse<>();
        return apiResponse.createAPISuccessResponse(dtoService.buildDTOFromTaskReward(createdReward), metaData, RestResponseMessage.TASK_REWARD_CREATE_SUCCESS);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // update - one
    // Unit testing  : NA
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public TaskReward update(@PathVariable("id") final Long id, @RequestBody final TaskReward resource) {
        return updateInternal(id, resource);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AbstractRestResponse<TaskRewardDTO>> updateDTO(@PathVariable("id") final Long id, @RequestBody @Valid final TaskRewardDTO resource) {
        TaskReward updatedReward = update(id, dtoService.buildTaskConfigurationFromDTO(resource));
        AbstractRestMetaData metaData = new AbstractRestMetaData("http://localhost:8082/api/tasks-rweards/"+updatedReward.getId(), "total tasks : N/A");
        AbstractAPIResponse<TaskRewardDTO> apiResponse = new AbstractAPIResponse<>();
        return apiResponse.createAPISuccessResponse(dtoService.buildDTOFromTaskReward(updatedReward), metaData, RestResponseMessage.TASK_REWARD_UPDATE_SUCCESS);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Delete - one
    // Unit testing  : NA
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<AbstractRestResponse<TaskRewardDTO>> delete(@PathVariable("id") final Long id) {
        deleteByIdInternal(id);
        AbstractRestMetaData metaData = new AbstractRestMetaData("http://localhost:8082/api/tasks-rweards/", "total tasks : N/A");
        AbstractAPIResponse<TaskRewardDTO> apiResponse = new AbstractAPIResponse<>();
        return apiResponse.createAPISuccessResponse(null, metaData, RestResponseMessage.TASK_REWARD_DELETE_SUCCESS);
    }
    @Override
    protected final ITaskRewardService getService() {
        return service;
    }



}
