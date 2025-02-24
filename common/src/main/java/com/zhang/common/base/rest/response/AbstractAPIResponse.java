package com.zhang.common.base.rest.response;
import org.springframework.http.ResponseEntity;

public class AbstractAPIResponse<T> {
    public ResponseEntity<AbstractRestResponse<T>> createAPISuccessResponse(T dto, AbstractRestMetaData restMetaData, String message) {
        AbstractRestResponse<T> restResponse = new AbstractRestResponse<>(
                "success",
                message,
                dto,
                restMetaData
        ) {};
        return ResponseEntity.ok(restResponse);
    }
}
