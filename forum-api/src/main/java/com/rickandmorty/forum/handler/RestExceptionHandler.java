package com.rickandmorty.forum.handler;

import com.rickandmorty.forum.exceptions.ExceptionDetails;
import com.rickandmorty.forum.exceptions.NotFoundException;
import com.rickandmorty.forum.exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        ResponseEntity<ValidationException> responseEntity = new ResponseEntity<>(new ValidationException(
                "Bad Request Exception, Campos Inválidos",
                BAD_REQUEST.value(),
                "Verifique o erro do(s) campo(s)",
                ex.getClass().getName(),
                LocalDateTime.now(),
                fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", ")),
                fieldErrors.stream().map(FieldError::getDefaultMessage)
                        .collect(Collectors.joining(", "))
        ), BAD_REQUEST);

        logger.info("MethodArgumentNotValidException caught: {}", Objects.requireNonNull(responseEntity.getBody()).getDetails(), ex);

        return this.handleExceptionInternal(ex, responseEntity, headers, status, request);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionDetails handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception) {
        logger.info("SQLIntegrityConstraintViolationException caught: {}", exception.getMessage(), exception);

        return new ExceptionDetails(
                BAD_REQUEST.name(),
                BAD_REQUEST.value(),
                exception.getMessage(),
                exception.getClass().getName(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionDetails handlerNotFoundException(NotFoundException exception) {
        logger.info("NotFoundException caught: {}", exception.getMessage(), exception);

        return new ExceptionDetails(
                NOT_FOUND.name(),
                NOT_FOUND.value(),
                exception.getMessage(),
                exception.getClass().getName(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionDetails handlerIllegalArgumentException(IllegalArgumentException exception) {
        logger.info("IllegalArgumentException caught: {}", exception.getMessage(), exception);

        return new ExceptionDetails(
                BAD_REQUEST.name(),
                BAD_REQUEST.value(),
                exception.getMessage(),
                exception.getClass().getName(),
                LocalDateTime.now()
        );
    }

}
