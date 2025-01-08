package gov.nara.um.spring.controller;

import gov.nara.common.util.QueryConstants;
import gov.nara.common.web.controller.AbstractLongIdController;
import gov.nara.common.web.controller.ILongIdSortingController;
import gov.nara.um.persistence.dto.BUnitConfigurationDTO;
import gov.nara.um.persistence.model.BusinessUnitConfiguration;
import gov.nara.um.service.ITaskConfigurationService;
import gov.nara.um.util.UmMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = UmMappings.BUSINESS_UNITS_CONFIGURATIONS)
public class TaskConfigurationController extends AbstractLongIdController<BusinessUnitConfiguration> implements ILongIdSortingController<BusinessUnitConfiguration> {

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

    public List<BusinessUnitConfiguration> findAllPaginatedAndSorted(final int page,  final int size, final String sortBy, final String sortOrder) {
        return findPaginatedAndSortedInternal(page, size, sortBy, sortOrder);
    }
    @RequestMapping(params = { QueryConstants.PAGE, QueryConstants.SIZE, QueryConstants.SORT_BY }, method = RequestMethod.GET)
    @ResponseBody
    public List<BUnitConfigurationDTO> findAllPaginatedAndSortedDTO(@RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size, @RequestParam(value = QueryConstants.SORT_BY) final String sortBy,
                                                                    @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder) {
        List<BusinessUnitConfiguration> bUnitConfigList =  findAllPaginatedAndSorted(page, size, sortBy, sortOrder);
        return buildDTOListFromConfigurationList(bUnitConfigList);

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // find - all/paginated
    // Unit testing  : NA
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public List<BusinessUnitConfiguration> findAllPaginated(final int page, final int size) {
        return findPaginatedInternal(page, size);
    }
    @RequestMapping(params = { QueryConstants.PAGE, QueryConstants.SIZE }, method = RequestMethod.GET)
    @ResponseBody
    public List<BUnitConfigurationDTO> findAllPaginatedDTO(@RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size) {
        List<BusinessUnitConfiguration> bUnitConfigList = findAllPaginated(page, size);
        return buildDTOListFromConfigurationList(bUnitConfigList);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // find - all/sorted
    // Unit testing  : DONE
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public List<BusinessUnitConfiguration> findAllSorted(@RequestParam(value = QueryConstants.SORT_BY) final String sortBy, @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder) {
        return findAllSortedInternal(sortBy, sortOrder);
    }
    @RequestMapping(params = { QueryConstants.SORT_BY }, method = RequestMethod.GET)
    @ResponseBody
    public List<BUnitConfigurationDTO> findAllSortedDTO(@RequestParam(value = QueryConstants.SORT_BY) final String sortBy, @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder) {
        List<BusinessUnitConfiguration> bUnitConfigList =  findAllSorted(sortBy, sortOrder);
        return buildDTOListFromConfigurationList(bUnitConfigList);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // find - all
    // Unit testing  : NA
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public List<BusinessUnitConfiguration> findAll(final HttpServletRequest request) {
        return findAllInternal(request);
    }
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<BUnitConfigurationDTO> findAllDTO(final HttpServletRequest request) {
        List<BusinessUnitConfiguration> bUnitConfigList = findAll(request);
        return buildDTOListFromConfigurationList(bUnitConfigList);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // find - one
    // Unit testing  : NA
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public BusinessUnitConfiguration findOne(final Long id) {
        return findOneInternal(id);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BUnitConfigurationDTO findOneDTO(@PathVariable("id") final Long id) {
        return buildDTOFromBUnitConfiguration(findOne(id));
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // create - one
    // Unit testing  : NA
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void create(@RequestBody final BusinessUnitConfiguration resource) {
        createInternal(resource);
    }
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createDTO(@RequestBody final BUnitConfigurationDTO resource) {
        create(buildBUnitConfigurationFromDTO(resource));
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // update - one
    // Unit testing  : NA
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void update(@PathVariable("id") final Long id, @RequestBody final BusinessUnitConfiguration resource) {
        updateInternal(id, resource);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateDTO(@PathVariable("id") final Long id, @RequestBody final BUnitConfigurationDTO resource) {
        update(id, buildBUnitConfigurationFromDTO(resource));
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
    private BUnitConfigurationDTO buildDTOFromBUnitConfiguration(BusinessUnitConfiguration bUnitConfig){
        BUnitConfigurationDTO bUnitConfigDTO = new BUnitConfigurationDTO();
        bUnitConfigDTO.setId(bUnitConfig.getId());
        bUnitConfigDTO.setName(bUnitConfig.getName());
        return  bUnitConfigDTO;
    }
    private BusinessUnitConfiguration buildBUnitConfigurationFromDTO(BUnitConfigurationDTO bUnitConfigurationDTO){
        BusinessUnitConfiguration bUnitConfig = new BusinessUnitConfiguration();
        bUnitConfig.setId(bUnitConfigurationDTO.getId());
        bUnitConfig.setName(bUnitConfigurationDTO.getName());
        return  bUnitConfig;
    }
    private List<BUnitConfigurationDTO> buildDTOListFromConfigurationList( List<BusinessUnitConfiguration> BUnitConfigurationList ){
        List<BUnitConfigurationDTO> dtoList = new ArrayList<>();
        if(BUnitConfigurationList != null)
            BUnitConfigurationList.forEach( bUnitConfiguration ->{
                dtoList.add(buildDTOFromBUnitConfiguration(bUnitConfiguration));
            } );
        return dtoList;
    }

    @Override
    protected final ITaskConfigurationService getService() {
        return service;
    }



}
