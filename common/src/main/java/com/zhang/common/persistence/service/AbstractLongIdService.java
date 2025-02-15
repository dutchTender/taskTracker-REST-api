package com.zhang.common.persistence.service;

import com.zhang.common.persistence.model.ILongNameableEntity;


public abstract class AbstractLongIdService<T extends ILongNameableEntity> extends AbstractLongIdRawService<T> implements ILongIdService<T> {

    public AbstractLongIdService() {
        super();
    }

    // API

}

