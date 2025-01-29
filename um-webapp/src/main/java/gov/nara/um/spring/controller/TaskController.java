package gov.nara.um.spring.controller;


import gov.nara.common.util.QueryConstants;
import gov.nara.common.web.controller.AbstractLongIdController;
import gov.nara.common.web.controller.ILongIdSortingController;
import gov.nara.um.persistence.dto.TaskDTO;
import gov.nara.um.persistence.dto.TaskRewardDTO;
import gov.nara.um.persistence.model.*;
import gov.nara.um.service.ITaskRewardService;
import gov.nara.um.service.ITaskService;
import gov.nara.um.util.UmMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;


@Controller
@RequestMapping(value = UmMappings.TASKS)
@CrossOrigin(origins = "*")
public class TaskController extends AbstractLongIdController<Task> implements ILongIdSortingController<Task> {

    @Autowired
    private ITaskService taskService;
    @Autowired
    private ITaskRewardService rewardService;
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
           return buildDTOListFromTasks(Optional.ofNullable(taskList));
    }
    @Override

    public List<Task> findAllPaginated(final int page, final int size) {
        return findPaginatedInternal(page, size);
    }
    @RequestMapping(params = { QueryConstants.PAGE, QueryConstants.SIZE }, method = RequestMethod.GET)
    @ResponseBody
    public List<TaskDTO> findAllPaginatedDTO(@RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size) {
        List<Task> taskList = findAllPaginated(page, size);
        return buildDTOListFromTasks(Optional.ofNullable(taskList));
    }

    @Override
    public List<Task> findAllSorted(final String sortBy, final String sortOrder) {
        return findAllSortedInternal(sortBy, sortOrder);
    }
    @RequestMapping(params = { QueryConstants.SORT_BY }, method = RequestMethod.GET)
    @ResponseBody
    public List<TaskDTO> findAllSortedDTO(@RequestParam(value = QueryConstants.SORT_BY) final String sortBy, @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder) {
        List<Task> taskList = findAllSorted(sortBy, sortOrder);
        return buildDTOListFromTasks(Optional.ofNullable(taskList));
    }
    @Override
    public List<Task> findAll(final HttpServletRequest request) {
        return findAllInternal(request);
    }
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<TaskDTO> findAllDTO(final HttpServletRequest request) {
        List<Task> taskList = findAllInternal(request);
        return buildDTOListFromTasks(Optional.ofNullable(taskList));
    }
    public Task findOne(final Long id) {
        return findOneInternal(id);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public TaskDTO findOneDTO(@PathVariable("id") final Long id) {
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
    public void update( final Long id, final Task resource) {
        updateInternal(id, resource);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateDTO(@PathVariable("id") final Long id, @RequestBody @Valid final TaskDTO resource) {
           resource.setId(id);
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
    public void delete(@PathVariable("id") final Long id) {
        deleteByIdInternal(id);
    }

    // remove user from business unit
    @RequestMapping(value = "/removeUser/{id}/{uid}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void removeUserFromTask(@PathVariable("id") final String id, @PathVariable("uid") final String uid) {
        getService().removerUser(id, uid);
    }

    private Task buildTaskFromDTO(TaskDTO taskDTO){
        Task task;
        if(taskDTO.getId() != null){
            task = taskService.findOne(taskDTO.getId());
            task.setName(taskDTO.getName());
            task.setTaskTime(taskDTO.getTaskTime());
            task.setTaskDescription(taskDTO.getTaskDescription());
        }
        else {
            task = new Task(taskDTO);
        }
        if (task.getId() != null) {
            if(taskDTO.getTaskRewards().size() > 0){
                taskDTO.getTaskRewards().forEach(reward ->{
                    TaskReward taskReward = rewardService.findOne(reward.getId());
                    task.addTaskRewardConfiguration(buildTaskRewardConfigsFromID(taskReward, task));
                });
            }

        }
        return task;
    }
    private TaskDTO buildDTOFromTask(Task bUnit){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(bUnit.getId());
        taskDTO.setName(bUnit.getName());
        taskDTO.setTaskDescription(bUnit.getTaskDescription());
        taskDTO.setTaskTime(bUnit.getTaskTime());
        taskDTO.setTaskRewards(buildIDsFromTaskRewardPreferences(Optional.ofNullable(bUnit.getTaskRewardPreferences())));
        return taskDTO;
    }
    private TaskRewardPreference buildTaskRewardConfigsFromID(TaskReward taskReward, Task task){

                        TaskRewardPreference taskRewardPreference = new TaskRewardPreference();
                        // create embeded ID
                        TaskRewardsPreferenceID taskRewardsPreferenceID = new TaskRewardsPreferenceID();
                        taskRewardsPreferenceID.setTaskID(task.getId());
                        taskRewardsPreferenceID.setTaskRewardID(taskReward.getId());
                        // initialize taskRewardConfig object
                        taskRewardPreference.setId(taskRewardsPreferenceID);
                        taskRewardPreference.setTaskRewardID(taskReward);
                        taskRewardPreference.setTaskID(task);
                        taskReward.addTaskRewardConfig(taskRewardPreference);
                        rewardService.update(taskReward);

                        return taskRewardPreference;
    }
    private HashSet<TaskRewardDTO> buildIDsFromTaskRewardPreferences(Optional<Set<TaskRewardPreference>> taskRewardPreferences){
        HashSet<TaskRewardDTO> taskRewardDTOs = new HashSet<>();
        taskRewardPreferences.ifPresent(
                rewardPreferences->{
                    rewardPreferences.forEach(rewardPreference->{
                        TaskReward reward = rewardPreference.getTaskRewardID();
                        // build TaskReward DTO from reward
                        TaskRewardDTO dto = new TaskRewardDTO();
                        dto.setId(reward.getId());
                        dto.setName(reward.getName());
                        taskRewardDTOs.add(dto);
                    });
                }
        );
        return  taskRewardDTOs;
    }
    private List<TaskDTO> buildDTOListFromTasks(Optional<List<Task>> taskList){
        List<TaskDTO> DTOList = new ArrayList<>();
        taskList.ifPresent(
                tasks -> {
                    tasks.forEach(task ->{
                        DTOList.add(buildDTOFromTask(task));
                    });
                }
        );
        return DTOList;
    }
    @Override
    protected final ITaskService getService() {
        return taskService;
    }

}
