package com.zhang.common.base.controller;

import com.zhang.common.interfaces.entity.ILongNameableEntity;

import com.zhang.common.base.rest.RestPreconditions;

public abstract class AbstractLongIdController<T extends ILongNameableEntity> extends AbstractLongIdReadOnlyController<T> {

    // save/create/persist

    protected final T createInternal(final T resource) {
        RestPreconditions.checkRequestElementNotNull(resource);
        // verifies that id is not part of the payload
        RestPreconditions.checkRequestState(resource.getId() == null);
        return getService().create(resource);
    }

    // update

    /**
     * - note: the operation is IDEMPOTENT <br/>
     */
    protected final void updateInternal(final Long id, final T resource) {
        RestPreconditions.checkRequestElementNotNull(resource);
        RestPreconditions.checkRequestElementNotNull(id);
        /////////////////////////////////////////////////////////////////////////////////////////////
        // the resource pay load will never have the id for a put call
        // the id is passed though via parameters and hooked into the id parameter for this function
        // check if payload has an id.
        //RestPreconditions.checkRequestState(resource.getId() == null);
        ////////////////////////////////////////////////////////////////////////////////////////////
        RestPreconditions.checkNotNull(getService().findOne(id));
        getService().update(resource);
    }

    // delete/remove
    protected final void deleteByIdInternal(final Long id) {
        getService().delete(id);
    }

}
