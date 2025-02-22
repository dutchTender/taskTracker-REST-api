package com.zhang.core.persistence.dao;

import com.zhang.common.interfaces.generics.IByNameApi;
import com.zhang.core.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IUserDAO extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>, IByNameApi<User> {
    public User findByName(String name);
}
