package com.zhang.details.controller;


import com.zhang.core.persistence.model.Task;
import com.zhang.common.util.QueryConstants;
import com.zhang.common.web.controller.AbstractLongIdController;
import com.zhang.common.web.controller.ILongIdSortingController;
import com.zhang.core.persistence.dto.TaskDTO;

import com.zhang.core.service.ITaskRewardService;
import com.zhang.core.service.ITaskService;
import com.zhang.details.util.DTOService;
import com.zhang.details.util.UmMappings;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;


@RestController
@RequestMapping(value = UmMappings.TASKS)
@CrossOrigin(origins = "*")
public class TaskController extends AbstractLongIdController<Task> implements ILongIdSortingController<Task> {


    private final ITaskService taskService;

    public TaskController(ITaskService taskService) {
        this.taskService = taskService;
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
           return DTOService.buildDTOListFromTasks(Optional.ofNullable(taskList));
    }
    @Override

    public List<Task> findAllPaginated(final int page, final int size) {
        return findPaginatedInternal(page, size);
    }
    @RequestMapping(params = { QueryConstants.PAGE, QueryConstants.SIZE }, method = RequestMethod.GET)
    @ResponseBody
    public List<TaskDTO> findAllPaginatedDTO(@RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size) {
        List<Task> taskList = findAllPaginated(page, size);
        return DTOService.buildDTOListFromTasks(Optional.ofNullable(taskList));
    }

    @Override
    public List<Task> findAllSorted(final String sortBy, final String sortOrder) {
        return findAllSortedInternal(sortBy, sortOrder);
    }
    @RequestMapping(params = { QueryConstants.SORT_BY }, method = RequestMethod.GET)
    @ResponseBody
    public List<TaskDTO> findAllSortedDTO(@RequestParam(value = QueryConstants.SORT_BY) final String sortBy, @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder) {
        List<Task> taskList = findAllSorted(sortBy, sortOrder);
        return DTOService.buildDTOListFromTasks(Optional.ofNullable(taskList));
    }
    @Override
    public List<Task> findAll(final HttpServletRequest request) {
        return findAllInternal(request);
    }
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<TaskDTO> findAllDTO(final HttpServletRequest request) {
        List<Task> taskList = findAllInternal(request);
        return DTOService.buildDTOListFromTasks(Optional.ofNullable(taskList));
    }
    public Task findOne(final Long id) {
        return findOneInternal(id);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public TaskDTO findOneDTO(@PathVariable("id") final Long id) {
        Task task = findOne(id);
        return DTOService.buildDTOFromTask(task);
    }
    public void create(@RequestBody final Task resource) {
        createInternal(resource);
    }
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createDTO(@RequestBody @Valid final TaskDTO resource) {
        Task newTask = DTOService.buildTaskFromDTO(resource);
        create(newTask);
    }
    public void update( final Long id, final Task resource) {
        updateInternal(id, resource);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateDTO(@PathVariable("id") final Long id, @RequestBody @Valid final TaskDTO resource) {
           resource.setId(id);
           Task task = DTOService.buildTaskFromDTO(resource);
           update(id, task);
    }
    @RequestMapping(value = "/addUser/{id}/{uid}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void addUserToTask(@PathVariable("id") final String id, @PathVariable("uid") final String uid) {
        getService().addUser(id, uid);

    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") final Long id) {
        deleteByIdInternal(id);
    }

    // remove user from business unit
    @RequestMapping(value = "/removeUser/{id}/{uid}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void removeUserFromTask(@PathVariable("id") final String id, @PathVariable("uid") final String uid) {
        getService().removerUser(id, uid);
    }

    @Override
    protected final ITaskService getService() {
        return taskService;
    }

}
