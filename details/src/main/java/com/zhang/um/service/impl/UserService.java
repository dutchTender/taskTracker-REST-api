package com.zhang.um.service.impl;

import com.zhang.common.persistence.service.AbstractLongIdService;

import com.zhang.um.persistence.dao.IUserDAO;
import com.zhang.um.persistence.model.User;
import com.zhang.um.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService extends AbstractLongIdService<User>  implements IUserService {

    @Autowired
    private IUserDAO dao;

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
    protected final IUserDAO getDao() {
        return dao;
    }

    @Override
    protected JpaSpecificationExecutor<User> getSpecificationExecutor() {
        return dao;
    }
}
