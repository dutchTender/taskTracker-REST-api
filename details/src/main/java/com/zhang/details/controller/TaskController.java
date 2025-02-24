package com.zhang.details.controller;


import com.zhang.common.base.rest.response.AbstractRestMetaData;
import com.zhang.common.base.rest.response.AbstractRestResponse;
import com.zhang.common.base.rest.response.RestResponseMessage;
import com.zhang.core.persistence.model.Task;
import com.zhang.common.base.rest.validate.QueryConstants;
import com.zhang.common.base.controller.AbstractLongIdController;
import com.zhang.common.interfaces.generics.controller.ILongIdSortingController;
import com.zhang.core.persistence.dto.TaskDTO;
import com.zhang.core.service.ITaskService;
import com.zhang.details.service.DTOService;
import com.zhang.details.util.UmMappings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;


@RestController
@RequestMapping(value = UmMappings.TASKS)
@CrossOrigin(origins = "*")
public class TaskController extends AbstractLongIdController<Task> implements ILongIdSortingController<Task> {


    private final ITaskService taskService;
    private final DTOService dtoService;

    public TaskController(ITaskService taskService, DTOService dtoService) {
        this.taskService = taskService;
        this.dtoService = dtoService;
    }
    // API
    // find - all/paginated
    @Override
    public List<Task> findAllPaginatedAndSorted(final int page, final int size, final String sortBy, final String sortOrder) {
        return findPaginatedAndSortedInternal(page, size, sortBy, sortOrder);
    }
    @RequestMapping(params = { QueryConstants.PAGE, QueryConstants.SIZE, QueryConstants.SORT_BY }, method = RequestMethod.GET)
    @ResponseBody
    public List<TaskDTO> findAllPaginatedAndSortedDTO(@RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size, @RequestParam(value = QueryConstants.SORT_BY) final String sortBy,
                                                      @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder) {
           List<Task> taskList = findAllPaginatedAndSorted(page, size, sortBy, sortOrder);
           return dtoService.buildDTOListFromTasks(Optional.ofNullable(taskList));
    }
    @Override

    public List<Task> findAllPaginated(final int page, final int size) {
        return findPaginatedInternal(page, size);
    }
    @RequestMapping(params = { QueryConstants.PAGE, QueryConstants.SIZE }, method = RequestMethod.GET)
    @ResponseBody
    public List<TaskDTO> findAllPaginatedDTO(@RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size) {
        List<Task> taskList = findAllPaginated(page, size);
        return dtoService.buildDTOListFromTasks(Optional.ofNullable(taskList));
    }

    @Override
    public List<Task> findAllSorted(final String sortBy, final String sortOrder) {
        return findAllSortedInternal(sortBy, sortOrder);
    }
    @RequestMapping(params = { QueryConstants.SORT_BY }, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<AbstractRestResponse<List<TaskDTO>>> findAllSortedDTO(@RequestParam(value = QueryConstants.SORT_BY) final String sortBy, @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder) {
        List<Task> taskList = findAllSorted(sortBy, sortOrder);

        AbstractRestMetaData metaData = new AbstractRestMetaData("http://localhost:8082/api/tasks/", "total tasks : "+taskList.size());
        AbstractRestResponse<List<TaskDTO>> restResponse = new AbstractRestResponse<>(
                "success",
                RestResponseMessage.TASKS_GET_SUCCESS,
                dtoService.buildDTOListFromTasks(Optional.ofNullable(taskList)),
                metaData
        ) {};
        return ResponseEntity.ok(restResponse);
    }
    @Override
    public List<Task> findAll(final HttpServletRequest request) {
        return findAllInternal(request);
    }
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<AbstractRestResponse<List<TaskDTO>>> findAllDTO(final HttpServletRequest request) {
        List<Task> taskList = findAll(request);
        AbstractRestMetaData metaData = new AbstractRestMetaData("http://localhost:8082/api/tasks/", "total tasks : "+taskList.size());
        AbstractRestResponse<List<TaskDTO>> restResponse = new AbstractRestResponse<>(
                "success",
                RestResponseMessage.TASKS_GET_SUCCESS,
                dtoService.buildDTOListFromTasks(Optional.ofNullable(taskList)),
                metaData
        ) {};
        return ResponseEntity.ok(restResponse);
    }
    public Task findOne(final Long id) {
        return findOneInternal(id);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<AbstractRestResponse<TaskDTO>> findOneDTO(@PathVariable("id") final Long id) {
        Task task = findOne(id);
        AbstractRestMetaData metaData = new AbstractRestMetaData("http://localhost:8082/api/tasks/"+id, " N/A");
        AbstractRestResponse<TaskDTO> restResponse = new AbstractRestResponse<>(
                "success",
                RestResponseMessage.TASKS_GET_SUCCESS,
                dtoService.buildDTOFromTask(task),
                metaData
        ) {};
        return ResponseEntity.ok(restResponse);
    }
    public Task create(@RequestBody final Task resource) {
        return createInternal(resource);
    }
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AbstractRestResponse<TaskDTO>> createDTO(@RequestBody @Valid final TaskDTO resource) {
        Task createdTask = create(dtoService.buildTaskFromDTO(resource));
        AbstractRestMetaData metaData = new AbstractRestMetaData("http://localhost:8082/api/tasks/"+createdTask.getId(), " N/A");
        AbstractRestResponse<TaskDTO> restResponse = new AbstractRestResponse<>(
                "success",
                RestResponseMessage.TASK_CREATE_SUCCESS,
                dtoService.buildDTOFromTask(createdTask),
                metaData
        ) {};
        return ResponseEntity.ok(restResponse);
    }
    public Task update( final Long id, final Task resource) {
        return updateInternal(id, resource);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AbstractRestResponse<TaskDTO>> updateDTO(@PathVariable("id") final Long id, @RequestBody @Valid final TaskDTO resource) {
           resource.setId(id);
           Task updatedTask = updateInternal(id, dtoService.buildTaskFromDTO(resource));

        AbstractRestMetaData metaData = new AbstractRestMetaData("http://localhost:8082/api/tasks/"+id, " N/A");
        AbstractRestResponse<TaskDTO> restResponse = new AbstractRestResponse<>(
                "success",
                RestResponseMessage.TASK_UPDATE_SUCCESS,
                dtoService.buildDTOFromTask(updatedTask),
                metaData
        ) {};
        return ResponseEntity.ok(restResponse);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<AbstractRestResponse<TaskDTO>> delete(@PathVariable("id") final Long id) {
        deleteByIdInternal(id);
        AbstractRestMetaData metaData = new AbstractRestMetaData("http://localhost:8082/api/tasks/", " N/A");
        AbstractRestResponse<TaskDTO> restResponse = new AbstractRestResponse<>(
                "success",
                RestResponseMessage.TASK_DELETE_SUCCESS,
                null,
                metaData
        ) {};
        return ResponseEntity.ok(restResponse);
    }
    @Override
    protected final ITaskService getService() {
        return taskService;
    }

}
