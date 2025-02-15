package com.zhang.common.persistence.service;

import com.zhang.common.interfaces.IByNameApi;
import com.zhang.common.interfaces.IWithName;

public interface ILongIdService<T extends IWithName> extends ILongRawService<T>, IByNameApi<T> {

    //

}
