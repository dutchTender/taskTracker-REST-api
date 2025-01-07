package gov.nara.um.spring.controller;

import gov.nara.common.util.QueryConstants;
import gov.nara.common.web.controller.AbstractController;
import gov.nara.common.web.controller.ISortingController;
import gov.nara.um.persistence.dto.BUnitDTO;
import gov.nara.um.persistence.model.*;
import gov.nara.um.service.IBUnitService;
import gov.nara.um.util.UmMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;



@Controller
@RequestMapping(value = UmMappings.BUSINESSUNITS)
public class BUnitController extends AbstractController<BusinessUnit> implements ISortingController<BusinessUnit> {

    @Autowired
    private IBUnitService service;
    // API
    // find - all/paginated
    @Override
    public List<BusinessUnit> findAllPaginatedAndSorted( final int page, final int size,  final String sortBy, final String sortOrder) {
        return findPaginatedAndSortedInternal(page, size, sortBy, sortOrder);
    }
    @RequestMapping(params = { QueryConstants.PAGE, QueryConstants.SIZE, QueryConstants.SORT_BY }, method = RequestMethod.GET)
    @ResponseBody
    public List<BUnitDTO> findAllPaginatedAndSortedDTO(@RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size, @RequestParam(value = QueryConstants.SORT_BY) final String sortBy,
                                                        @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder) {
           List<BusinessUnit> bUnitList = findAllPaginatedAndSorted(page, size, sortBy, sortOrder);
           return buildDTOListFromBUnits(bUnitList);
    }
    @Override

    public List<BusinessUnit> findAllPaginated(final int page, final int size) {
        return findPaginatedInternal(page, size);
    }
    @RequestMapping(params = { QueryConstants.PAGE, QueryConstants.SIZE }, method = RequestMethod.GET)
    @ResponseBody
    public List<BUnitDTO> findAllPaginatedDTO(@RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size) {
        List<BusinessUnit> bUnitList = findAllPaginated(page, size);
        return buildDTOListFromBUnits(bUnitList);
    }

    @Override
    public List<BusinessUnit> findAllSorted(final String sortBy, final String sortOrder) {
        return findAllSortedInternal(sortBy, sortOrder);
    }
    @RequestMapping(params = { QueryConstants.SORT_BY }, method = RequestMethod.GET)
    @ResponseBody
    public List<BUnitDTO> findAllSortedDTO(@RequestParam(value = QueryConstants.SORT_BY) final String sortBy, @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder) {
        List<BusinessUnit> bUnitList = findAllSorted(sortBy, sortOrder);
        return buildDTOListFromBUnits(bUnitList);
    }
    @Override
    public List<BusinessUnit> findAll(final HttpServletRequest request) {
        return findAllInternal(request);
    }
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<BUnitDTO> findAllDTO(final HttpServletRequest request) {
        List<BusinessUnit> bUnitList = findAllInternal(request);
        return buildDTOListFromBUnits(bUnitList);
    }
    public BusinessUnit findOne(final Integer id) {
        return findOneInternal(id);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BUnitDTO findOneDTO(@PathVariable("id") final Integer id) {
        BusinessUnit bUnit = findOne(id);
        return buildDTOFromBUnit(bUnit);
    }
    public void create(@RequestBody final BusinessUnit resource) {
        createInternal(resource);
    }
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createDTO(@RequestBody final BUnitDTO resource) {
        BusinessUnit newUnit = buildBUnitFromDTO(resource);
        create(newUnit);
    }
    public void update( final Integer id, final BusinessUnit resource) {
        updateInternal(id, resource);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateDTO(@PathVariable("id") final Integer id, @RequestBody final BUnitDTO resource) {
           BusinessUnit bUnit = buildBUnitFromDTO(resource);
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

    private BusinessUnit buildBUnitFromDTO(BUnitDTO bUnitDTO){
        BusinessUnit bUnit = new BusinessUnit();
        bUnit.setName(bUnitDTO.getName());
        bUnit.setOrg_code(bUnitDTO.getOrg_code());
        bUnit.setLdapName(bUnitDTO.getLdapName());
        bUnit.setId(bUnitDTO.getId());
        bUnit.setBusinessUnitConfigurationPreferences(buildBUnitConfigPreferencesFromIDs(bUnitDTO.getBUnitConfigurationIDs(), bUnitDTO.getId()));
        return bUnit;
    }
    private List<BusinessUnitConfigurationPreference> buildBUnitConfigPreferencesFromIDs(List<Long> prefIDs, Integer bUnitID){
        ArrayList<BusinessUnitConfigurationPreference> bUnitConfigPrefs = new ArrayList<>();
        prefIDs.forEach(id ->{
             BusinessUnitConfigurationPreference newConfigPref = new BusinessUnitConfigurationPreference();
             BusinessUnitConfiguration newConfig = new BusinessUnitConfiguration();
             newConfig.setId(id);
             BusinessUnitConfigurationID newConfigID = new BusinessUnitConfigurationID();
             newConfigID.setBusinessUnitConfigID(id);
             newConfigID.setBusinessUnitID(bUnitID);
             newConfigPref.setBusinessUnitConfigID(newConfig);
             newConfigPref.setId(newConfigID);
             bUnitConfigPrefs.add(newConfigPref);
        });
        return bUnitConfigPrefs;
    }
    private BUnitDTO buildDTOFromBUnit(BusinessUnit bUnit){
        BUnitDTO bUnitDTO = new BUnitDTO();
        bUnitDTO.setId(bUnit.getId());
        bUnitDTO.setName(bUnit.getName());
        bUnitDTO.setLdapName(bUnit.getLdapName());
        bUnitDTO.setOrg_code(bUnit.getOrg_code());
        bUnitDTO.setBUnitConfigurationIDs(buildIDsFromBUConfigPreferences(bUnit.getBusinessUnitConfigurationPreferences()));
        return  bUnitDTO;
    }
    private ArrayList<Long> buildIDsFromBUConfigPreferences(List<BusinessUnitConfigurationPreference> bUnitConfigs){
        ArrayList<Long> BUnitConfigIDs = new ArrayList<>();
        bUnitConfigs.forEach(bUnitConfig ->{
            BUnitConfigIDs.add(bUnitConfig.getId().getBusinessUnitConfigID());
        });
        return  BUnitConfigIDs;
    }
    private List<BUnitDTO> buildDTOListFromBUnits(List<BusinessUnit> bUnitList){
        List<BUnitDTO> DTOList = new ArrayList<>();
        bUnitList.forEach(bUnit ->{
            DTOList.add(buildDTOFromBUnit(bUnit));
        });
        return DTOList;
    }
    @Override
    protected final IBUnitService getService() {
        return service;
    }

}
