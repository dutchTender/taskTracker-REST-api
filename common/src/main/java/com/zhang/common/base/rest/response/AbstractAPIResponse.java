package com.zhang.common.base.rest.response;
import org.springframework.http.ResponseEntity;
import java.util.Collection;


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

    public ResponseEntity<AbstractRestResponse<Collection<T>>> createAPISuccessResponseCollection(Collection<T> dtoCollection, AbstractRestMetaData restMetaData, String message) {
        AbstractRestResponse<Collection<T>> restResponse = new AbstractRestResponse<>(
                "success",
                message,
                dtoCollection,
                restMetaData
        ) {};
        return ResponseEntity.ok(restResponse);
    }
}
