package com.zhang.common.base.rest.response;
import org.springframework.http.ResponseEntity;


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
}
