package com.zhang.common.persistence.service;

import com.zhang.common.interfaces.ILongOperations;
import com.zhang.common.interfaces.IWithName;
import org.springframework.data.domain.Page;

public interface ILongRawService<T extends IWithName> extends ILongOperations<T> {

    Page<T> findAllPaginatedAndSortedRaw(final int page, final int size, final String sortBy, final String sortOrder);
    Page<T> findAllPaginatedRaw(final int page, final int size);

}
