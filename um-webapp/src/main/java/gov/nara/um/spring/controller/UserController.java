package gov.nara.um.spring.controller;



import gov.nara.common.util.QueryConstants;
import gov.nara.common.web.controller.AbstractLongIdController;
import gov.nara.common.web.controller.ILongIdSortingController;
import gov.nara.um.persistence.dto.UserDTO;
import gov.nara.um.persistence.model.BusinessUnit;
import gov.nara.um.persistence.model.User;
import gov.nara.um.service.IBUnitService;
import gov.nara.um.service.IUserService;
import gov.nara.um.util.UmMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Controller
@RequestMapping(value = UmMappings.USERS)
public class UserController extends AbstractLongIdController<User> implements ILongIdSortingController<User> {

    @Autowired
    private IUserService userService;

    @Autowired
    private IBUnitService bUnitService;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // find - all/paginated and sorted
    // Unit testing  : NA
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public List<User> findAllPaginatedAndSorted( final int page,  final int size, final String sortBy, final String sortOrder) {
        ArrayList<UserDTO> returnList = new ArrayList<>();
        List<User> userList = findPaginatedAndSortedInternal(page, size, sortBy, sortOrder);
        userList.forEach(user ->{
            returnList.add(buildDTOFromUser(user));
        });

        return userList;
    }
    @RequestMapping(params = { QueryConstants.PAGE, QueryConstants.SIZE, QueryConstants.SORT_BY }, method = RequestMethod.GET)
    @ResponseBody
    public List<UserDTO> findAllPaginatedAndSorted2DTO(@RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size, @RequestParam(value = QueryConstants.SORT_BY) final String sortBy,
                                                   @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder) {
        ArrayList<UserDTO> userDTOList = new ArrayList<>();
        List<User> userList = findAllPaginatedAndSorted(page, size, sortBy, sortOrder);
        userList.forEach(user ->{
            userDTOList.add(buildDTOFromUser(user));
        });
        return userDTOList;
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
        ArrayList<UserDTO> userDTOList = new ArrayList<>();
        List<User> userList =  findAllPaginated(page, size);
        userList.forEach(user ->{
            userDTOList.add(buildDTOFromUser(user));
        });
        return userDTOList;
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
        ArrayList<UserDTO> userDTOList = new ArrayList<>();
        List<User> userList = findAllSorted(sortBy, sortOrder);
        userList.forEach(user ->{
            userDTOList.add(buildDTOFromUser(user));
        });
        return userDTOList;
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
    public List<UserDTO> findAllDTO(final HttpServletRequest request) {
        ArrayList<UserDTO> userDTOList = new ArrayList<>();
        List<User> userList = findAll(request);
        userList.forEach(user ->{
            userDTOList.add(buildDTOFromUser(user));
        });
        return userDTOList;
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
        UserDTO userDTO = buildDTOFromUser(user);
        return userDTO;
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
    public void create(@RequestBody final User resource) {
        createInternal(resource);
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
    public void update(@PathVariable("id") final Long id, @RequestBody final UserDTO resource) {
        updateInternal(id, buildUserFromDTO(resource));
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




    private User buildUserFromDTO(UserDTO dto){
        User user = new User();
        user.setName(dto.getUser_name());
        user.setUser_type(dto.getUser_type());
        user.setBusinessUnits(buildBUnitFromIDs(dto.getBusiness_unitIDs()));
        return  user;
    }
    private HashSet<BusinessUnit> buildBUnitFromIDs(HashSet<Integer> bUnitIDs){
        HashSet<BusinessUnit> businessUnits = new HashSet<>();
        bUnitIDs.forEach(bUnitID ->{
            System.out.println("processing business unit ID for user DTO : " + bUnitID);
            BusinessUnit bUnit = bUnitService.findOne(bUnitID);
            businessUnits.add(bUnit);
        });
        System.out.println("BUnit set size after conversion" + businessUnits.size()); // assert that that size matches input bUnits
        return  businessUnits;
    }
    private UserDTO buildDTOFromUser(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setUser_name(user.getName());
        userDTO.setUser_type(user.getUser_type());
        userDTO.setBusiness_unitIDs(buildIDsFromBUnits(user.getBusinessUnits()));
        return  userDTO;
    }
    private HashSet<Integer> buildIDsFromBUnits(Set<BusinessUnit> bUnits){
        HashSet<Integer> BUnitIDs = new HashSet<>();
        bUnits.forEach(bUnit ->{
            System.out.println("processing business unit ID for user DTO : " + bUnit.getId());
            BUnitIDs.add(bUnit.getId());
        });
        System.out.println("BUnitDTO set size after conversion" + BUnitIDs.size()); // assert that that size matches input bUnits
        return  BUnitIDs;
    }
    @Override
    protected final IUserService getService() {
        return userService;
    }

}
