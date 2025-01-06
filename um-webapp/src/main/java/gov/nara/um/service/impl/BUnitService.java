package gov.nara.um.service.impl;
import gov.nara.common.persistence.service.AbstractService;
import gov.nara.um.persistence.dao.IBUnitDao;
import gov.nara.um.persistence.dao.IUserDao;
import gov.nara.um.persistence.model.BusinessUnit;
import gov.nara.um.persistence.model.User;
import gov.nara.um.service.IBUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class BUnitService extends AbstractService<BusinessUnit> implements IBUnitService {

    @Autowired
    private IBUnitDao dao;
    @Autowired
    private IUserDao userDao;
    public BUnitService() {
        super();
    }
    // API
    // find
    @Override
    @Transactional(readOnly = true)
    public BusinessUnit findByName(final String name) {
        return dao.findByName(name);
    }
    // other
    // remove user
    // Spring
    @Override
    protected final IBUnitDao getDao() {
        return dao;
    }

    @Override
    protected JpaSpecificationExecutor<BusinessUnit> getSpecificationExecutor() {
        return dao;
    }
    @Override
    public BusinessUnit addUser(String unitId, String userId) {
        Optional <BusinessUnit> businessUnitOptional =  dao.findById(Integer.valueOf(unitId));
        BusinessUnit businessUnit =  businessUnitOptional.get();
        Optional <User> userOptional = userDao.findById(Long.valueOf(userId));
        User user = userOptional.get();
        if(businessUnit != null && user != null){
            // we need to add it to the current versoin. no longer singular referenced
            //businessUnit.addUser(user);
            //user.setBusinessUnit(businessUnit);
            dao.save(businessUnit);
            userDao.save(user);
        }
        return businessUnit;
    }
    @Override
    public BusinessUnit removerUser(String unitId, String userId) {
        Optional <BusinessUnit> businessUnitOptional =  dao.findById(Integer.valueOf(unitId));
        BusinessUnit businessUnit =  businessUnitOptional.get();
        Optional <User> userOptional = userDao.findById(Long.valueOf(userId));
        User user = userOptional.get();
        if(businessUnit != null && user != null){
            //businessUnit.removeUser(user);
            //user.setBusinessUnit(null);
            dao.save(businessUnit);
            userDao.save(user);
        }
        return businessUnit;
    }
}
