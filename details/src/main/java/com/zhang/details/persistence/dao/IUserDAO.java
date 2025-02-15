package com.zhang.details.persistence.dao;

import com.zhang.common.interfaces.IByNameApi;
import com.zhang.details.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IUserDAO extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>, IByNameApi<User> {
    public User findByName(String name);
}
