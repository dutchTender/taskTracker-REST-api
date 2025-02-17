package com.zhang.details.util;

import com.zhang.core.persistence.dto.TaskDTO;
import com.zhang.core.persistence.dto.TaskRewardDTO;
import com.zhang.core.persistence.dto.UserDTO;
import com.zhang.core.persistence.model.*;
import com.zhang.core.service.ITaskRewardService;
import com.zhang.core.service.ITaskService;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DTOService

{
    ITaskService taskService;
    ITaskRewardService taskRewardService;
    public DTOService(ITaskService taskService, ITaskRewardService taskRewardService) {
        this.taskService = taskService;
        this.taskRewardService = taskRewardService;
    }

    public  User buildUserFromDTO(UserDTO dto, Long id){
        User user = new User();
        if(id != 0)
            user.setId(id);
        user.setName(dto.getName());
        user.setUser_type(dto.getUser_type());
        user.setEmail(dto.getEmail());
        user.setTasks(buildTasksFromDTOs(dto.getTasks()));
        return  user;
    }
    public HashSet<Task> buildTasksFromDTOs(HashSet<TaskDTO> taskDTOs){
        HashSet<Task> tasks = new HashSet<>();
        if(taskDTOs != null)
            taskDTOs.forEach(taskDTO ->{
                Task bUnit = taskService.findTaskReference(taskDTO.getId());
                tasks.add(bUnit);
            });
        return tasks;
    }
    public UserDTO buildDTOFromUser(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setUser_type(user.getUser_type());
        userDTO.setTasks(buildDTOsFromTasks(user.getTasks()));
        userDTO.setEmail(user.getEmail());
        return  userDTO;
    }
    public HashSet<TaskDTO> buildDTOsFromTasks(Set<Task> tasks){
        HashSet<TaskDTO> taskDTOs = new HashSet<>();
        if(tasks != null)
            tasks.forEach( task -> {
                taskDTOs.add(buildDTOFromTask(task));
            });
        return  taskDTOs;
    }
    public List<UserDTO> buildDTOListFromUsers(Optional<List<User>> userList){
        List<UserDTO> DTOList = new ArrayList<>();
        userList.ifPresent(
                users-> {
                    users.forEach(user ->{
                        DTOList.add(buildDTOFromUser(user));
                    });
                });

        return DTOList;
    }

    public Task buildTaskFromDTO(TaskDTO taskDTO){
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
                    TaskReward taskReward = taskRewardService.findOne(reward.getId());
                    task.addTaskRewardConfiguration(buildTaskRewardConfigsFromID(taskReward, task));
                });
            }

        }
        return task;
    }
    public TaskDTO buildDTOFromTask(Task bUnit){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(bUnit.getId());
        taskDTO.setName(bUnit.getName());
        taskDTO.setTaskDescription(bUnit.getTaskDescription());
        taskDTO.setTaskTime(bUnit.getTaskTime());
        taskDTO.setTaskRewards(buildIDsFromTaskRewardPreferences(Optional.ofNullable(bUnit.getTaskRewardPreferences())));
        return taskDTO;
    }
    public TaskRewardPreference buildTaskRewardConfigsFromID(TaskReward taskReward, Task task){

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
        taskRewardService.update(taskReward);

        return taskRewardPreference;
    }
    public  HashSet<TaskRewardDTO> buildIDsFromTaskRewardPreferences(Optional<Set<TaskRewardPreference>> taskRewardPreferences){
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

    public  TaskRewardDTO buildDTOFromTaskReward(TaskReward reward){
        TaskRewardDTO taskRewardDTO = new TaskRewardDTO();
        taskRewardDTO.setId(reward.getId());
        taskRewardDTO.setName(reward.getName());
        return  taskRewardDTO;
    }
    public  TaskReward buildTaskConfigurationFromDTO(TaskRewardDTO taskRewardDTO){
        TaskReward taskReward = new TaskReward();
        taskReward.setId(taskRewardDTO.getId());
        taskReward.setName(taskRewardDTO.getName());
        return  taskReward;
    }
    public  List<TaskRewardDTO> buildDTOListFromConfigurationList(Optional<List<TaskReward>> taskRewardList ){
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
    public  List<TaskDTO> buildDTOListFromTasks(Optional<List<Task>> taskList){
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


}
