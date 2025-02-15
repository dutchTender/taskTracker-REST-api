package com.zhang.details.spring.controller;

import com.zhang.common.util.QueryConstants;
import com.zhang.common.web.controller.AbstractLongIdController;
import com.zhang.common.web.controller.ILongIdSortingController;
import com.zhang.core.persistence.dto.TaskRewardDTO;
import com.zhang.core.persistence.model.TaskReward;
import com.zhang.core.service.ITaskRewardService;
import com.zhang.details.util.UmMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = UmMappings.TASKS_REWARDS)
@CrossOrigin(origins = "*")
public class TaskRewardController extends AbstractLongIdController<TaskReward> implements ILongIdSortingController<TaskReward> {

    @Autowired
    private ITaskRewardService service;
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
        return buildDTOListFromConfigurationList(Optional.ofNullable(bUnitConfigList));

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
        return buildDTOListFromConfigurationList(Optional.ofNullable(taskConfigList));
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
    public List<TaskRewardDTO> findAllSortedDTO(@RequestParam(value = QueryConstants.SORT_BY) final String sortBy, @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder) {
        List<TaskReward> taskConfigList =  findAllSorted(sortBy, sortOrder);
        return buildDTOListFromConfigurationList(Optional.ofNullable(taskConfigList));
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
    public List<TaskRewardDTO> findAllDTO(final HttpServletRequest request) {
        List<TaskReward> taskConfigList = findAll(request);
        return buildDTOListFromConfigurationList(Optional.ofNullable(taskConfigList));
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
    public TaskRewardDTO findOneDTO(@PathVariable("id") final Long id) {
        return buildDTOFromTaskReward(findOne(id));
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // create - one
    // Unit testing  : NA
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void create(@RequestBody final TaskReward resource) {
        createInternal(resource);
    }
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createDTO(@RequestBody @Valid final TaskRewardDTO resource) {
        create(buildTaskConfigurationFromDTO(resource));
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // update - one
    // Unit testing  : NA
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void update(@PathVariable("id") final Long id, @RequestBody final TaskReward resource) {
        updateInternal(id, resource);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateDTO(@PathVariable("id") final Long id, @RequestBody @Valid final TaskRewardDTO resource) {
        update(id, buildTaskConfigurationFromDTO(resource));
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
    public void delete(@PathVariable("id") final Long id) {
        deleteByIdInternal(id);
    }
    private TaskRewardDTO buildDTOFromTaskReward(TaskReward reward){
        TaskRewardDTO taskRewardDTO = new TaskRewardDTO();
        taskRewardDTO.setId(reward.getId());
        taskRewardDTO.setName(reward.getName());
        return  taskRewardDTO;
    }
    private TaskReward buildTaskConfigurationFromDTO(TaskRewardDTO taskRewardDTO){
        TaskReward taskReward = new TaskReward();
        taskReward.setId(taskRewardDTO.getId());
        taskReward.setName(taskRewardDTO.getName());
        return  taskReward;
    }
    private List<TaskRewardDTO> buildDTOListFromConfigurationList(Optional<List<TaskReward>> taskRewardList ){
        List<TaskRewardDTO> dtoList = new ArrayList<>();
        taskRewardList.ifPresent(
                rList->{
                    rList.forEach( taskReward ->{
                        dtoList.add(buildDTOFromTaskReward(taskReward));
                    } );
                }
        );
        return dtoList;
    }
    @Override
    protected final ITaskRewardService getService() {
        return service;
    }



}
