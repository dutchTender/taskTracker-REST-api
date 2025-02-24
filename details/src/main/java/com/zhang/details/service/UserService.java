package com.zhang.details.service;
import com.zhang.common.base.service.AbstractLongIdService;
import com.zhang.core.persistence.dao.IUserDAO;
import com.zhang.core.persistence.model.User;
import com.zhang.core.service.IUserService;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService extends AbstractLongIdService<User>  implements IUserService {

    private final IUserDAO dao;

    public UserService(IUserDAO dao) {
        super();
        this.dao = dao;
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
    protected final IUserDAO getDao() {
        return dao;
    }
    @Override
    protected JpaSpecificationExecutor<User> getSpecificationExecutor() {
        return dao;
    }
}
