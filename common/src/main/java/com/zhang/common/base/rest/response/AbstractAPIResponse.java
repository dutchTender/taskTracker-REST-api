package com.zhang.common.base.rest.response;
import org.springframework.http.ResponseEntity;

import java.util.Collection;


public abstract class AbstractAPIResponse<T> {
    public ResponseEntity<AbstractRestResponse<T>> createAPISuccessResponse(T dto, AbstractRestMetaData restMetaData) {
        AbstractRestResponse<T> restResponse = new AbstractRestResponse<>(
                "success",
                RestResponseMessage.USERS_GET_SUCCESS,
                dto,
                restMetaData
        ) {};
        return ResponseEntity.ok(restResponse);
    }

    public ResponseEntity<AbstractRestResponse<Collection<T>>> createAPISuccessResponseCollection(Collection<T> dtoCollection, AbstractRestMetaData restMetaData) {
        AbstractRestResponse<Collection<T>> restResponse = new AbstractRestResponse<>(
                "success",
                RestResponseMessage.USERS_GET_SUCCESS,
                dtoCollection,
                restMetaData
        ) {};
        return ResponseEntity.ok(restResponse);
    }
}
