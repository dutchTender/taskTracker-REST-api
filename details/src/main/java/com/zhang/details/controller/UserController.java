package com.zhang.details.controller;

import com.zhang.common.base.rest.response.AbstractAPIResponse;
import com.zhang.common.base.rest.response.AbstractRestMetaData;
import com.zhang.common.base.rest.response.AbstractRestResponse;
import com.zhang.common.base.rest.validate.QueryConstants;
import com.zhang.common.base.controller.AbstractLongIdController;
import com.zhang.common.base.rest.response.RestResponseMessage;
import com.zhang.common.interfaces.generics.controller.ILongIdSortingController;
import com.zhang.core.persistence.dto.UserDTO;
import com.zhang.core.persistence.model.User;
import com.zhang.core.service.IUserService;
import com.zhang.details.service.DTOService;
import com.zhang.details.util.UmMappings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final DTOService dtoService;
    private final AbstractAPIResponse<List<UserDTO>> apiResponseCollection = new AbstractAPIResponse<>();
    private final AbstractAPIResponse<UserDTO> apiResponse = new AbstractAPIResponse<>();

    public UserController(IUserService userService, DTOService dtoService) {
        this.userService = userService;
        this.dtoService = dtoService;
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
    public ResponseEntity<AbstractRestResponse<List<UserDTO>>> findAllPaginatedAndSortedDTO(@RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size, @RequestParam(value = QueryConstants.SORT_BY) final String sortBy,
                                                   @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder) {
        List<User> userList = findAllPaginatedAndSorted(page, size, sortBy, sortOrder);
        AbstractRestMetaData metaData = new AbstractRestMetaData("http://localhost:8082/api/users/", "params: sort by - {" + sortBy + " }" + " sort order - { " + sortOrder + " }  page - {" + page + "}  size - {" + size + "}");
        return apiResponseCollection.createAPIResponse(dtoService.buildDTOListFromUsers(Optional.ofNullable(userList)), metaData, RestResponseMessage.USERS_GET_SUCCESS, "Success");
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
    public ResponseEntity<AbstractRestResponse<List<UserDTO>>> findAllPaginatedDTO(@RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size) {
        List<User> userList =  findAllPaginated(page, size);
        AbstractRestMetaData metaData = new AbstractRestMetaData("http://localhost:8082/api/users/", "params: page - {" +page+" }" + " size- {"+size+"}");
        return apiResponseCollection.createAPIResponse( dtoService.buildDTOListFromUsers(Optional.ofNullable(userList)), metaData, RestResponseMessage.USERS_GET_SUCCESS, "Success");
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
    public ResponseEntity<AbstractRestResponse<List<UserDTO>>> findAllSortedDTO(@RequestParam(value = QueryConstants.SORT_BY) final String sortBy, @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder) {
        List<UserDTO> dtoList = dtoService.buildDTOListFromUsers(Optional.ofNullable(findAllSorted(sortBy, sortOrder)));
        AbstractRestMetaData metaData = new AbstractRestMetaData("http://localhost:8082/api/users/", "params: sort by - {" +sortBy+" }" + " sort order - { "+sortOrder+" } total users : "+dtoList.size());
        return apiResponseCollection.createAPIResponse(dtoList, metaData, RestResponseMessage.USERS_GET_SUCCESS, "Success");
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
    public ResponseEntity<AbstractRestResponse<List<UserDTO>>> findAllDTO(final HttpServletRequest request ) {
        List<UserDTO> dtoList = dtoService.buildDTOListFromUsers(Optional.ofNullable(findAll(request)));
        AbstractRestMetaData metaData = new AbstractRestMetaData("http://localhost:8082/api/users/", "total users : "+dtoList.size());
        return apiResponseCollection.createAPIResponse(dtoList, metaData, RestResponseMessage.USERS_GET_SUCCESS, "Success");

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
    public ResponseEntity<AbstractRestResponse<UserDTO>> findOneDTO(@PathVariable("id") final Long id) {
        User user = findOne(id);
        AbstractRestMetaData metaData = new AbstractRestMetaData("http://localhost:8082/api/users/"+user.getId(), "N/A");
        return apiResponse.createAPIResponse(dtoService.buildDTOFromUser(user), metaData, RestResponseMessage.USER_GET_SUCCESS, "Success");
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // create - one
    // Unit testing  : NA
    // Integration testing : NA
    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///
    public User create(@RequestBody final User resource) {
        return createInternal(resource);
    }
    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AbstractRestResponse<UserDTO>> createDTO(@RequestBody @Valid final UserDTO resource) {
        User user = create(dtoService.buildUserFromDTO(resource, 0L));
        AbstractRestMetaData metaData = new AbstractRestMetaData("http://localhost:8082/api/users/"+user.getId(), "N/A");
        return apiResponse.createAPIResponse(dtoService.buildDTOFromUser(user), metaData, RestResponseMessage.USER_CREATE_SUCCESS, "Success");
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // update - one
    // Unit testing  : NA
    // Integration testing : NA
    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///
    ///
    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AbstractRestResponse<UserDTO>> update(@PathVariable("id") final Long id, @RequestBody @Valid final UserDTO resource) {
        User user = updateInternal(id, dtoService.buildUserFromDTO(resource, id));
        AbstractRestMetaData metaData = new AbstractRestMetaData("http://localhost:8082/api/users/"+user.getId(), "N/A");
        return apiResponse.createAPIResponse(dtoService.buildDTOFromUser(user), metaData, RestResponseMessage.USER_UPDATE_SUCCESS, "Success");
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
    public ResponseEntity<AbstractRestResponse<UserDTO>> delete(@PathVariable("id") final Long id) {
        deleteByIdInternal(id);
        AbstractRestMetaData metaData = new AbstractRestMetaData("http://localhost:8082/api/users/", "N/A");
        return apiResponse.createAPIResponse(null, metaData, RestResponseMessage.USER_DELETE_SUCCESS, "Success");
    }
    @Override
    protected final IUserService getService() {
        return userService;
    }

}
