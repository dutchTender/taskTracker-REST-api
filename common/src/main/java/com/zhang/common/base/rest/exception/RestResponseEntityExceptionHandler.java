package com.zhang.common.base.rest.exception;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import com.zhang.common.base.rest.response.AbstractAPIResponse;
import com.zhang.common.base.rest.response.AbstractRestMetaData;
import com.zhang.common.base.rest.response.AbstractRestResponse;
import com.zhang.common.base.rest.response.RestResponseMessage;
import com.zhang.common.util.exception.*;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// can not enable controller advice at this time
// it is causing all unit tests to fail
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private final AbstractAPIResponse<Object> apiErrorResponse = new AbstractAPIResponse<>();
    public RestResponseEntityExceptionHandler() {
        super();
    }
    // API
    // 400  duplicate entry will be checked here
    @ExceptionHandler({ ConstraintViolationException.class, MyBadRequestException.class, DataIntegrityViolationException.class })
    public ResponseEntity<AbstractRestResponse<Object>> handleBadRequest(final RuntimeException ex, final WebRequest request) {
        //final String bodyOfResponse = "This should be application specific";
        AbstractRestMetaData metaData = new AbstractRestMetaData("http://localhost:8082/api/", "error: "+ex.getMessage());
        return apiErrorResponse.createAPIResponse( handleExceptionInternal(ex, exceptionMessage(HttpStatus.BAD_REQUEST, ex), new HttpHeaders(), HttpStatus.BAD_REQUEST, request), metaData, RestResponseMessage.BadRequest, "Error");
    }
    // bad request name
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        //final String bodyOfResponse = "This should be application specific";
        // ex.getCause() instanceof JsonMappingException, JsonParseException // for additional information later on
        AbstractRestMetaData metaData = new AbstractRestMetaData("http://localhost:8082/api/", "error: "+ex.getMessage());
        return ResponseEntity.ok(apiErrorResponse.createAPIResponse( handleExceptionInternal(ex, exceptionMessage(HttpStatus.BAD_REQUEST, ex), headers, HttpStatus.BAD_REQUEST, request), metaData, RestResponseMessage.HttpMessageNotReadable, "Error"));
    }
    // bad request value

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        AbstractRestMetaData metaData = new AbstractRestMetaData("http://localhost:8082/api/", "error: "+ex.getMessage());
        return ResponseEntity.ok(apiErrorResponse.createAPIResponse( handleExceptionInternal(ex, exceptionMessage(HttpStatus.BAD_REQUEST, ex), new HttpHeaders(), HttpStatus.BAD_REQUEST, request), metaData, RestResponseMessage.MethodArgumentNotValid, "Error"));
    }
    // 403
    // forbidden resource access
    @ExceptionHandler({ MyForbiddenException.class })
    public ResponseEntity<Object> handleForbidden(final MyForbiddenException ex, final WebRequest request) {
        AbstractRestMetaData metaData = new AbstractRestMetaData("http://localhost:8082/api/", "error: "+ex.getMessage());
        return ResponseEntity.ok(apiErrorResponse.createAPIResponse( handleExceptionInternal(ex, exceptionMessage(HttpStatus.FORBIDDEN, ex), new HttpHeaders(), HttpStatus.FORBIDDEN, request), metaData, request.getDescription(true), "Error"));
    }
    // 404
    /* if we enable this. unit test for the controller will start failing  */

    @ExceptionHandler( {MyResourceNotFoundException.class })
    protected ResponseEntity<Object> handleResourceNotFound(final MyResourceNotFoundException ex, final WebRequest request) {
        AbstractRestMetaData metaData = new AbstractRestMetaData("http://localhost:8082/api/", "error: "+ex.getMessage());
        return ResponseEntity.ok(apiErrorResponse.createAPIResponse( handleExceptionInternal(ex, exceptionMessage(HttpStatus.NOT_FOUND, ex), new HttpHeaders(), HttpStatus.NOT_FOUND, request), metaData, request.getDescription(true), "Error"));
    }
    @ExceptionHandler({ MyEntityNotFoundException.class })
    protected ResponseEntity<Object> handleNotFound(final MyEntityNotFoundException ex, final WebRequest request) {
        AbstractRestMetaData metaData = new AbstractRestMetaData("http://localhost:8082/api/", "error: "+ex.getMessage());
        return ResponseEntity.ok(apiErrorResponse.createAPIResponse( handleExceptionInternal(ex, exceptionMessage(HttpStatus.NOT_FOUND, ex), new HttpHeaders(), HttpStatus.NOT_FOUND, request), metaData, request.getDescription(true), "Error"));
    }
    @ExceptionHandler(value = { EntityNotFoundException.class })
    protected ResponseEntity<Object> handleBadRequest(final EntityNotFoundException ex, final WebRequest request) {
        AbstractRestMetaData metaData = new AbstractRestMetaData("http://localhost:8082/api/", "error: "+ex.getMessage());
        return ResponseEntity.ok(apiErrorResponse.createAPIResponse( handleExceptionInternal(ex, exceptionMessage(HttpStatus.NOT_FOUND, ex), new HttpHeaders(), HttpStatus.NOT_FOUND, request), metaData, request.getDescription(true), "Error"));
    }
    // 409
    @ExceptionHandler({ InvalidDataAccessApiUsageException.class, DataAccessException.class, MyConflictException.class })
    protected ResponseEntity<Object> handleConflict(final RuntimeException ex, final WebRequest request) {
        AbstractRestMetaData metaData = new AbstractRestMetaData("http://localhost:8082/api/", "error: "+ex.getMessage());
        return ResponseEntity.ok(apiErrorResponse.createAPIResponse( handleExceptionInternal(ex, exceptionMessage(HttpStatus.CONFLICT, ex), new HttpHeaders(), HttpStatus.CONFLICT, request), metaData, request.getDescription(true), "Error"));
    }
    // 4xx
    @ExceptionHandler({ MyPreconditionFailedException.class })
    /*412*/protected ResponseEntity<Object> handlePreconditionFailed(final RuntimeException ex, final WebRequest request) {
        AbstractRestMetaData metaData = new AbstractRestMetaData("http://localhost:8082/api/", "error: "+ex.getMessage());
        return ResponseEntity.ok(apiErrorResponse.createAPIResponse( handleExceptionInternal(ex, exceptionMessage(HttpStatus.PRECONDITION_FAILED, ex), new HttpHeaders(), HttpStatus.PRECONDITION_FAILED, request), metaData, request.getDescription(true), "Error"));
    }
    @ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class })
    /*500*/public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
        logger.error("500 Status Code", ex);
        AbstractRestMetaData metaData = new AbstractRestMetaData("http://localhost:8082/api/", "error: "+ex.getMessage());
        return ResponseEntity.ok(apiErrorResponse.createAPIResponse( handleExceptionInternal(ex, exceptionMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request), metaData, request.getDescription(true), "Error"));
    }

    private APIErrorDto exceptionMessage(final  HttpStatus httpStatus, final  Exception ex){
        final  String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage();
        final  String devMessage = ExceptionUtils.getRootCauseMessage(ex);
        return new APIErrorDto(httpStatus.value(), message, devMessage);
    }


}
