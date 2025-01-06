package gov.nara.um.spring.controller;



import gov.nara.common.util.QueryConstants;
import gov.nara.common.web.controller.AbstractController;
import gov.nara.common.web.controller.AbstractLongIdController;
import gov.nara.common.web.controller.ILongIdSortingController;
import gov.nara.common.web.controller.ISortingController;
import gov.nara.um.persistence.dto.BusinessUnitDTO;
import gov.nara.um.persistence.dto.UserDTO;
import gov.nara.um.persistence.model.BusinessUnit;
import gov.nara.um.persistence.model.User;
import gov.nara.um.service.IBusinessUnitService;
import gov.nara.um.service.IUserService;
import gov.nara.um.util.UmMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping(value = UmMappings.USERS)
public class UserController extends AbstractLongIdController<User> implements ILongIdSortingController<User> {

    @Autowired
    private IUserService userService;

    @Autowired
    private IBusinessUnitService bUnitService;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // find - all/paginated and sorted
    // Unit testing  : NA
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    @RequestMapping(params = { QueryConstants.PAGE, QueryConstants.SIZE, QueryConstants.SORT_BY }, method = RequestMethod.GET)
    @ResponseBody
    public List<User> findAllPaginatedAndSorted(@RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size, @RequestParam(value = QueryConstants.SORT_BY) final String sortBy,
                                                        @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder) {
        return findPaginatedAndSortedInternal(page, size, sortBy, sortOrder);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // find - all/paginated
    // Unit testing  : NA
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    @RequestMapping(params = { QueryConstants.PAGE, QueryConstants.SIZE }, method = RequestMethod.GET)
    @ResponseBody
    public List<User> findAllPaginated(@RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size) {
        return findPaginatedInternal(page, size);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // find - all/sorted
    // Unit testing  : DONE
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    @RequestMapping(params = { QueryConstants.SORT_BY }, method = RequestMethod.GET)
    @ResponseBody
    public List<User> findAllSorted(@RequestParam(value = QueryConstants.SORT_BY) final String sortBy, @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder) {
        return findAllSortedInternal(sortBy, sortOrder);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // find - all
    // Unit testing  : NA
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<User> findAll(final HttpServletRequest request) {
        return findAllInternal(request);
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
    public User findOne(@PathVariable("id") final Long id) {
        return findOneInternal(id);
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
    public void update(@PathVariable("id") final Long id, @RequestBody final User resource) {
        updateInternal(id, resource);
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
        user.setBusinessUnits(buildBusinessUnitFromIDs(dto.getBusiness_unitIDs()));
        return  user;
    }

    private Set<BusinessUnit> buildBusinessUnitFromIDs(Integer[] bUnits){
        Set<BusinessUnit> businessUnits = new HashSet<>();
        Arrays.stream(bUnits).iterator().forEachRemaining(bUnitID ->{
            System.out.println("processing business unit ID for user DTO : " + bUnitID);
            BusinessUnit bUnit = bUnitService.findOne(bUnitID);
            businessUnits.add(bUnit);
        });
        System.out.println(businessUnits.toArray().length); // assert that that size matches input bUnits
        return  businessUnits;
    }
    @Override
    protected final IUserService getService() {
        return userService;
    }

}
