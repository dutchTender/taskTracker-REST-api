package com.zhang.common.base.service;

import com.zhang.common.interfaces.generics.service.ILongIdService;
import com.zhang.common.interfaces.entity.ILongNameableEntity;


public abstract class AbstractLongIdService<T extends ILongNameableEntity> extends AbstractLongIdRawService<T> implements ILongIdService<T> {

    public AbstractLongIdService() {
        super();
    }

    // API

}

