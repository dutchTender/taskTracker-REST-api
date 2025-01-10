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
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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
           List<Task> taskList = findAllPaginatedAndSorted(page, size, sortBy, sortOrder);
           return buildDTOListFromTasks(taskList);
    }
    @Override

    public List<Task> findAllPaginated(final int page, final int size) {
        return findPaginatedInternal(page, size);
    }
    @RequestMapping(params = { QueryConstants.PAGE, QueryConstants.SIZE }, method = RequestMethod.GET)
    @ResponseBody
    public List<TaskDTO> findAllPaginatedDTO(@RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size) {
        List<Task> taskList = findAllPaginated(page, size);
        return buildDTOListFromTasks(taskList);
    }

    @Override
    public List<Task> findAllSorted(final String sortBy, final String sortOrder) {
        return findAllSortedInternal(sortBy, sortOrder);
    }
    @RequestMapping(params = { QueryConstants.SORT_BY }, method = RequestMethod.GET)
    @ResponseBody
    public List<TaskDTO> findAllSortedDTO(@RequestParam(value = QueryConstants.SORT_BY) final String sortBy, @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder) {
        List<Task> taskList = findAllSorted(sortBy, sortOrder);
        return buildDTOListFromTasks(taskList);
    }
    @Override
    public List<Task> findAll(final HttpServletRequest request) {
        return findAllInternal(request);
    }
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<TaskDTO> findAllDTO(final HttpServletRequest request) {
        List<Task> taskList = findAllInternal(request);
        return buildDTOListFromTasks(taskList);
    }
    public Task findOne(final Integer id) {
        return findOneInternal(id);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public TaskDTO findOneDTO(@PathVariable("id") final Integer id) {
        Task task = findOne(id);
        return buildDTOFromTask(task);
    }
    public void create(@RequestBody final Task resource) {
        createInternal(resource);
    }
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createDTO(@RequestBody @Valid final TaskDTO resource) {
        Task newTask = buildTaskFromDTO(resource);
        create(newTask);
    }
    public void update( final Integer id, final Task resource) {
        updateInternal(id, resource);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateDTO(@PathVariable("id") final Integer id, @RequestBody @Valid final TaskDTO resource) {
           Task task = buildTaskFromDTO(resource);
           update(id, task);
    }
    @RequestMapping(value = "/addUser/{id}/{uid}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void addUserToTask(@PathVariable("id") final String id, @PathVariable("uid") final String uid) {
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
    public void removeUserFromTask(@PathVariable("id") final String id, @PathVariable("uid") final String uid) {
        getService().removerUser(id, uid);
    }

    private Task buildTaskFromDTO(TaskDTO taskDTO){
        Task bUnit = new Task();
        bUnit.setName(taskDTO.getName());
        bUnit.setTaskTime(taskDTO.getTaskTime());
        bUnit.setTaskDescription(taskDTO.getTaskDescription());
        bUnit.setId(taskDTO.getId());
        bUnit.setTaskRewardConfigs(buildTaskConfigPreferencesFromIDs(Optional.ofNullable(taskDTO.getTaskConfigurationIDs()), taskDTO.getId()));
        return bUnit;
    }
    private TaskDTO buildDTOFromTask(Task bUnit){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(bUnit.getId());
        taskDTO.setName(bUnit.getName());
        taskDTO.setTaskDescription(bUnit.getTaskDescription());
        taskDTO.setTaskTime(bUnit.getTaskTime());
        taskDTO.setTaskConfigurationIDs(buildIDsFromTaskConfigPreferences(Optional.ofNullable(bUnit.getTaskRewardConfigs())));
        return taskDTO;
    }
    private List<TaskRewardConfig> buildTaskConfigPreferencesFromIDs(Optional<List<Long>> prefIDs, Integer taskID){
        ArrayList<TaskRewardConfig> taskRewardConfigs = new ArrayList<>();
        prefIDs.ifPresent(
                ids->{
                    ids.forEach(id ->{
                        TaskRewardConfig newConfigPref = new TaskRewardConfig();
                        TaskReward newReward = new TaskReward();
                        newReward.setId(id);
                        TaskRewardsConfigID newConfigID = new TaskRewardsConfigID();
                        newConfigID.setTaskConfigID(id);
                        newConfigID.setTaskID(taskID);
                        newConfigPref.setTaskRewardID(newReward);
                        newConfigPref.setId(newConfigID);
                        taskRewardConfigs.add(newConfigPref);
                    });
                }
        );

        return taskRewardConfigs;
    }
    private ArrayList<Long> buildIDsFromTaskConfigPreferences(Optional<List<TaskRewardConfig>> taskConfigs){
        ArrayList<Long> taskConfigIDs = new ArrayList<>();
        taskConfigs.ifPresent(
                configs->{
                    configs.forEach(taskConfig ->{
                        taskConfigIDs.add(taskConfig.getId().getTaskConfigID());
                    });
                }
        );

        return  taskConfigIDs;
    }
    private List<TaskDTO> buildDTOListFromTasks(List<Task> taskList){
        List<TaskDTO> DTOList = new ArrayList<>();
        if(taskList != null)
            taskList.forEach(task ->{
                DTOList.add(buildDTOFromTask(task));
            });
        return DTOList;
    }
    @Override
    protected final ITaskService getService() {
        return service;
    }

}
