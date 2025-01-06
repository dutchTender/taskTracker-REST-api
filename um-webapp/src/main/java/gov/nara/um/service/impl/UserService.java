package gov.nara.um.service.impl;

import gov.nara.common.persistence.service.AbstractLongIdService;

import gov.nara.um.persistence.dao.IUserDao;
import gov.nara.um.persistence.model.User;
import gov.nara.um.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService extends AbstractLongIdService<User>  implements IUserService {

    @Autowired
    private IUserDao dao;

    public UserService() {
        super();
    }

    // API
    // find
    @Override
    @Transactional(readOnly = true)
    public User findByName(final String name) {
        return dao.findByName(name);
    }

    // other
    public void removeUser(final String name){
        dao.delete(findByName(name));
    }
    // Spring

    @Override
    protected final IUserDao getDao() {
        return dao;
    }

    @Override
    protected JpaSpecificationExecutor<User> getSpecificationExecutor() {
        return dao;
    }
}
