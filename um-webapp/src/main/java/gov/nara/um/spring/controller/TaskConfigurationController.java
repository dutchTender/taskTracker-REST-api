package gov.nara.um.spring.controller;

import gov.nara.common.util.QueryConstants;
import gov.nara.common.web.controller.AbstractLongIdController;
import gov.nara.common.web.controller.ILongIdSortingController;
import gov.nara.um.persistence.dto.TaskConfigurationDTO;
import gov.nara.um.persistence.model.TaskReward;
import gov.nara.um.service.ITaskConfigurationService;
import gov.nara.um.util.UmMappings;
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
@RequestMapping(value = UmMappings.TASKS_CONFIGURATIONS)
public class TaskConfigurationController extends AbstractLongIdController<TaskReward> implements ILongIdSortingController<TaskReward> {

    @Autowired
    private ITaskConfigurationService service;
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
    public List<TaskConfigurationDTO> findAllPaginatedAndSortedDTO(@RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size, @RequestParam(value = QueryConstants.SORT_BY) final String sortBy,
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
    public List<TaskConfigurationDTO> findAllPaginatedDTO(@RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size) {
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
    public List<TaskConfigurationDTO> findAllSortedDTO(@RequestParam(value = QueryConstants.SORT_BY) final String sortBy, @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder) {
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
    public List<TaskConfigurationDTO> findAllDTO(final HttpServletRequest request) {
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
    public TaskConfigurationDTO findOneDTO(@PathVariable("id") final Long id) {
        return buildDTOFromTaskConfiguration(findOne(id));
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
    public void createDTO(@RequestBody @Valid final TaskConfigurationDTO resource) {
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
    public void updateDTO(@PathVariable("id") final Long id, @RequestBody @Valid final TaskConfigurationDTO resource) {
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
    private TaskConfigurationDTO buildDTOFromTaskConfiguration(TaskReward bUnitConfig){
        TaskConfigurationDTO taskConfigDTO = new TaskConfigurationDTO();
        taskConfigDTO.setId(bUnitConfig.getId());
        taskConfigDTO.setName(bUnitConfig.getName());
        return  taskConfigDTO;
    }
    private TaskReward buildTaskConfigurationFromDTO(TaskConfigurationDTO taskConfigurationDTO){
        TaskReward bUnitConfig = new TaskReward();
        bUnitConfig.setId(taskConfigurationDTO.getId());
        bUnitConfig.setName(taskConfigurationDTO.getName());
        return  bUnitConfig;
    }
    private List<TaskConfigurationDTO> buildDTOListFromConfigurationList(Optional<List<TaskReward>> taskConfigurationList ){
        List<TaskConfigurationDTO> dtoList = new ArrayList<>();
        taskConfigurationList.ifPresent(
                configList->{
                    configList.forEach( taskConfiguration ->{
                        dtoList.add(buildDTOFromTaskConfiguration(taskConfiguration));
                    } );
                }
        );
        return dtoList;
    }
    @Override
    protected final ITaskConfigurationService getService() {
        return service;
    }



}
