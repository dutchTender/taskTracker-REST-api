package com.zhang.details.controller;

import com.zhang.common.util.QueryConstants;
import com.zhang.common.web.controller.AbstractLongIdController;
import com.zhang.common.web.controller.ILongIdSortingController;
import com.zhang.core.persistence.dto.TaskDTO;
import com.zhang.core.persistence.dto.TaskRewardDTO;
import com.zhang.core.persistence.dto.UserDTO;
import com.zhang.core.persistence.model.Task;
import com.zhang.core.persistence.model.TaskReward;
import com.zhang.core.persistence.model.TaskRewardPreference;
import com.zhang.core.persistence.model.User;
import com.zhang.core.service.ITaskService;
import com.zhang.core.service.IUserService;
import com.zhang.details.util.DTOService;
import com.zhang.details.util.UmMappings;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.*;




@RestController
@RequestMapping(value = UmMappings.USERS)
@CrossOrigin(origins = "*")
public class UserController extends AbstractLongIdController<User> implements ILongIdSortingController<User> {


    private final IUserService userService;


    public UserController(IUserService userService) {
        this.userService = userService;

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // find - all/paginated and sorted
    // Unit testing  : NA
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public List<User> findAllPaginatedAndSorted( final int page,  final int size, final String sortBy, final String sortOrder) {
        return findPaginatedAndSortedInternal(page, size, sortBy, sortOrder);
    }
    @RequestMapping(params = { QueryConstants.PAGE, QueryConstants.SIZE, QueryConstants.SORT_BY }, method = RequestMethod.GET)
    @ResponseBody
    public List<UserDTO> findAllPaginatedAndSortedDTO(@RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size, @RequestParam(value = QueryConstants.SORT_BY) final String sortBy,
                                                   @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder) {
        List<User> userList = findAllPaginatedAndSorted(page, size, sortBy, sortOrder);
        return DTOService.buildDTOListFromUsers(Optional.ofNullable(userList));
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // find - all/paginated
    // Unit testing  : NA
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public List<User> findAllPaginated( final int page,final int size) {
        return findPaginatedInternal(page, size);
    }

    @RequestMapping(params = { QueryConstants.PAGE, QueryConstants.SIZE }, method = RequestMethod.GET)
    @ResponseBody
    public List<UserDTO> findAllPaginatedDTO(@RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size) {
        List<User> userList =  findAllPaginated(page, size);
        return DTOService.buildDTOListFromUsers(Optional.ofNullable(userList));
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // find - all/sorted
    // Unit testing  : DONE
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public List<User> findAllSorted(final String sortBy, final String sortOrder) {
        return findAllSortedInternal(sortBy, sortOrder);
    }
    @RequestMapping(params = { QueryConstants.SORT_BY }, method = RequestMethod.GET)
    @ResponseBody
    public List<UserDTO> findAllSortedDTO(@RequestParam(value = QueryConstants.SORT_BY) final String sortBy, @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder) {
        List<User> userList = findAllSorted(sortBy, sortOrder);
        return DTOService.buildDTOListFromUsers(Optional.ofNullable(userList));
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // find - all
    // Unit testing  : NA
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public List<User> findAll(final HttpServletRequest request) {
        return findAllInternal(request);
    }
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<UserDTO> findAllDTO(final HttpServletRequest request ) {
        List<User> userList = findAll(request);
        return DTOService.buildDTOListFromUsers(Optional.ofNullable(userList));
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // find - one
    // Unit testing  : NA
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public User findOne(final Long id) {return findOneInternal(id);}
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public UserDTO findOneDTO(@PathVariable("id") final Long id) {
        User user = findOne(id);
        return DTOService.buildDTOFromUser(user);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // create - one
    // Unit testing  : NA
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void create(@RequestBody final User resource) {
        createInternal(resource);
    }
    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createDTO(@RequestBody @Valid final UserDTO resource) {
        create(DTOService.buildUserFromDTO(resource, 0L));
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // update - one
    // Unit testing  : NA
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") final Long id, @RequestBody @Valid final UserDTO resource) {
        updateInternal(id, DTOService.buildUserFromDTO(resource, id));
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Delete - one
    // Unit testing  : NA
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") final Long id) {
        deleteByIdInternal(id);
    }



    @Override
    protected final IUserService getService() {
        return userService;
    }

}
