package gov.nara.um.spring.controller;

import gov.nara.common.util.QueryConstants;
import gov.nara.common.web.controller.AbstractController;
import gov.nara.common.web.controller.ISortingController;
import gov.nara.um.persistence.dto.TaskDTO;
import gov.nara.um.persistence.model.*;
import gov.nara.um.service.ITaskService;
import gov.nara.um.util.UmMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;



@Controller
@RequestMapping(value = UmMappings.TASKS)
public class TaskController extends AbstractController<Task> implements ISortingController<Task> {

    @Autowired
    private ITaskService service;
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
           List<Task> bUnitList = findAllPaginatedAndSorted(page, size, sortBy, sortOrder);
           return buildDTOListFromBUnits(bUnitList);
    }
    @Override

    public List<Task> findAllPaginated(final int page, final int size) {
        return findPaginatedInternal(page, size);
    }
    @RequestMapping(params = { QueryConstants.PAGE, QueryConstants.SIZE }, method = RequestMethod.GET)
    @ResponseBody
    public List<TaskDTO> findAllPaginatedDTO(@RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size) {
        List<Task> bUnitList = findAllPaginated(page, size);
        return buildDTOListFromBUnits(bUnitList);
    }

    @Override
    public List<Task> findAllSorted(final String sortBy, final String sortOrder) {
        return findAllSortedInternal(sortBy, sortOrder);
    }
    @RequestMapping(params = { QueryConstants.SORT_BY }, method = RequestMethod.GET)
    @ResponseBody
    public List<TaskDTO> findAllSortedDTO(@RequestParam(value = QueryConstants.SORT_BY) final String sortBy, @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder) {
        List<Task> bUnitList = findAllSorted(sortBy, sortOrder);
        return buildDTOListFromBUnits(bUnitList);
    }
    @Override
    public List<Task> findAll(final HttpServletRequest request) {
        return findAllInternal(request);
    }
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<TaskDTO> findAllDTO(final HttpServletRequest request) {
        List<Task> bUnitList = findAllInternal(request);
        return buildDTOListFromBUnits(bUnitList);
    }
    public Task findOne(final Integer id) {
        return findOneInternal(id);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public TaskDTO findOneDTO(@PathVariable("id") final Integer id) {
        Task bUnit = findOne(id);
        return buildDTOFromBUnit(bUnit);
    }
    public void create(@RequestBody final Task resource) {
        createInternal(resource);
    }
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createDTO(@RequestBody final TaskDTO resource) {
        Task newUnit = buildBUnitFromDTO(resource);
        create(newUnit);
    }
    public void update( final Integer id, final Task resource) {
        updateInternal(id, resource);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateDTO(@PathVariable("id") final Integer id, @RequestBody final TaskDTO resource) {
           Task bUnit = buildBUnitFromDTO(resource);
           update(id, bUnit);
    }
    @RequestMapping(value = "/addUser/{id}/{uid}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void addUserToBusinessUnit(@PathVariable("id") final String id, @PathVariable("uid") final String uid) {
        getService().addUser(id, uid);

    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") final Integer id) {
        deleteByIdInternal(id);
    }

    // remove user from business unit
    @RequestMapping(value = "/removeUser/{id}/{uid}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void removeUserFromBusinessUnit(@PathVariable("id") final String id, @PathVariable("uid") final String uid) {
        getService().removerUser(id, uid);
    }

    private Task buildBUnitFromDTO(TaskDTO taskDTO){
        Task bUnit = new Task();
        bUnit.setName(taskDTO.getName());
        bUnit.setTaskTime(taskDTO.getTaskTime());
        bUnit.setTaskDescription(taskDTO.getTaskDescription());
        bUnit.setId(taskDTO.getId());
        bUnit.setTasksConfigurationPreferences(buildBUnitConfigPreferencesFromIDs(taskDTO.getTaskConfigurationIDs(), taskDTO.getId()));
        return bUnit;
    }
    private TaskDTO buildDTOFromBUnit(Task bUnit){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(bUnit.getId());
        taskDTO.setName(bUnit.getName());
        taskDTO.setTaskDescription(bUnit.getTaskDescription());
        taskDTO.setTaskTime(bUnit.getTaskTime());
        taskDTO.setTaskConfigurationIDs(buildIDsFromBUConfigPreferences(bUnit.getTasksConfigurationPreferences()));
        return taskDTO;
    }
    private List<TaskConfigurationPreference> buildBUnitConfigPreferencesFromIDs(List<Long> prefIDs, Integer bUnitID){
        ArrayList<TaskConfigurationPreference> bUnitConfigPrefs = new ArrayList<>();
        if(prefIDs != null)
            prefIDs.forEach(id ->{
                 TaskConfigurationPreference newConfigPref = new TaskConfigurationPreference();
                 BusinessUnitConfiguration newConfig = new BusinessUnitConfiguration();
                 newConfig.setId(id);
                 BusinessUnitConfigurationID newConfigID = new BusinessUnitConfigurationID();
                 newConfigID.setBusinessUnitConfigID(id);
                 newConfigID.setTaskID(bUnitID);
                 newConfigPref.setTaskConfigID(newConfig);
                 newConfigPref.setId(newConfigID);
                 bUnitConfigPrefs.add(newConfigPref);
            });
        return bUnitConfigPrefs;
    }
    private ArrayList<Long> buildIDsFromBUConfigPreferences(List<TaskConfigurationPreference> bUnitConfigs){
        ArrayList<Long> BUnitConfigIDs = new ArrayList<>();
        if(bUnitConfigs != null)
            bUnitConfigs.forEach(bUnitConfig ->{
                BUnitConfigIDs.add(bUnitConfig.getId().getBusinessUnitConfigID());
            });
        return  BUnitConfigIDs;
    }
    private List<TaskDTO> buildDTOListFromBUnits(List<Task> bUnitList){
        List<TaskDTO> DTOList = new ArrayList<>();
        if(bUnitList != null)
            bUnitList.forEach(bUnit ->{
                DTOList.add(buildDTOFromBUnit(bUnit));
            });
        return DTOList;
    }
    @Override
    protected final ITaskService getService() {
        return service;
    }

}
