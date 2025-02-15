package gov.nara.um.spring.controller;

import gov.nara.common.persistence.service.ILongRawService;
import gov.nara.common.web.controller.AbstractLongIdController;
import gov.nara.common.web.exception.MyBadRequestException;
import gov.nara.common.web.exception.MyResourceNotFoundException;
import gov.nara.um.persistence.model.Task;
import gov.nara.um.persistence.model.User;
import gov.nara.um.persistence.dto.UserTasksDTO;
import gov.nara.um.service.ITaskService;
import gov.nara.um.service.IUserService;
import gov.nara.um.util.UmMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
@Controller
@RequestMapping(value = UmMappings.USER_TASKS)
@CrossOrigin(origins = "*")
public class UserTaskController extends AbstractLongIdController<User>  {

    @Autowired
    private IUserService userService;

    @Autowired
    private ITaskService taskService;


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // find - all
    // Unit testing  : NA
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<UserTasksDTO> findAll(final HttpServletRequest request) {
        List<UserTasksDTO> returnList = new ArrayList<UserTasksDTO>();
        List<User>  userList = new ArrayList<User>();
        // nothing to extract from the request
        userList = userService.findAll();
        // build return list by looping through all users
        for(Iterator<User> iterUser = userList.iterator(); iterUser.hasNext(); ) {
            User current = iterUser.next();
            Set<Task> businessUnitsList = current.getTasks();
            for(Iterator<Task> iterBU = businessUnitsList.iterator(); iterBU.hasNext(); ) {
                Task task = iterBU.next();
                UserTasksDTO userBusinessUnitDTO = new UserTasksDTO();
                userBusinessUnitDTO.setTask_id(task.getId());
                userBusinessUnitDTO.setUser_id(current.getId());
                returnList.add(userBusinessUnitDTO);
            }
        }
        return returnList;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // find - one
    // Unit testing  : NA
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<UserTasksDTO> findOne(@PathVariable("id") final Long id) {

        List<UserTasksDTO> returnList = new ArrayList<UserTasksDTO>();
        User user = userService.findOne(id);
        if(user != null){
            for(Iterator<Task> iterBU = user.getTasks().iterator(); iterBU.hasNext(); ) {
                Task task = iterBU.next();
                UserTasksDTO userBusinessUnitDTO = new UserTasksDTO();
                userBusinessUnitDTO.setTask_id(task.getId());
                userBusinessUnitDTO.setUser_id(user.getId());
                returnList.add(userBusinessUnitDTO);
            }
        }
        else {
            throw new MyResourceNotFoundException("the resource requested does not exist.");
        }
        return returnList;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // create - one
    // Unit testing  : NA
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody final UserTasksDTO resource) {

        Long userId = resource.getUser_id();
        Long taskId = resource.getTask_id();
        User user = userService.findOne(userId);
        Task task = taskService.findOne(taskId);

        if(user != null && task != null){
            user.addTask(task);
            userService.update(user);
        }
        else {
            // throw custom exception
            String message = "";
            if(user == null){
                message = "user_id ";
            }
            else {
                message= "tasl_id ";
            }
            message += "value is not valid";
            throw new MyBadRequestException(message);
        }
        // throw custom bad request exception
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // update - one
    // Unit testing  : NA
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") final Long id, @RequestBody final UserTasksDTO resource) {

        Long userId = id;
        Long taskId = resource.getTask_id();
        User user = userService.findOne(userId);
        Task task = taskService.findOne(taskId);

        if(user != null && task != null){
            if(user.getTasks().size() > 0){
                user.getTasks().clear();;
            }
            user.addTask(task);
            userService.update(user);
        }
        else {
            // throw custom exception
            String message = "";
            if(user == null){
                message = "user_id ";
            }
            else {
                message= "task_id ";
            }
            message += "value in the API payload is not valid.";
            throw new MyBadRequestException(message);
        }
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

        User user = userService.findOne(id);
        if(user != null){
            user.getTasks().clear();
            userService.update(user);
        }
        else {
            throw new MyBadRequestException("user id value provided in the API path was not valid.");
        }
    }
    @Override
    protected ILongRawService<User> getService() {

        return userService;
    }
}
