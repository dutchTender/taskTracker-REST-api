package com.zhang.common.interfaces.generics.service;

import com.zhang.common.interfaces.generics.IByNameApi;
import com.zhang.common.interfaces.identity.IWithName;

public interface ILongIdService<T extends IWithName> extends ILongRawService<T>, IByNameApi<T> {

    //

}
