package com.zhang.common.interfaces.generics;

import com.zhang.common.interfaces.identity.IWithName;

public interface IByNameApi<T extends IWithName> {

    T findByName(final String name);

}
