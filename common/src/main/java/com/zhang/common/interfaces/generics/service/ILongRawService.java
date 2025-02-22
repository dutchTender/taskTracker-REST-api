package com.zhang.common.interfaces.generics.service;

import com.zhang.common.interfaces.generics.ILongOperations;
import com.zhang.common.interfaces.identity.IWithName;
import org.springframework.data.domain.Page;

public interface ILongRawService<T extends IWithName> extends ILongOperations<T> {

    Page<T> findAllPaginatedAndSortedRaw(final int page, final int size, final String sortBy, final String sortOrder);
    Page<T> findAllPaginatedRaw(final int page, final int size);

}
