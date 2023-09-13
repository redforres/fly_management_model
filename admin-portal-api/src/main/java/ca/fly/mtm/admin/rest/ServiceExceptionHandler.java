package ca.fly.mtm.admin.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public interface ServiceExceptionHandler {
    @ExceptionHandler(value = { EntityNotFoundException.class })
    default ResponseEntity<Object> handleException(EntityNotFoundException ex) {
        Logger logger = LoggerFactory.getLogger(this.getClass());

        StackTraceElement[] stackTrace = ex.getStackTrace();
        StackTraceElement location = stackTrace[0]; // last exception call site

        logger.error("Exception thrown at {}.{} @ Line {} - {}",
                location.getClassName(),
                location.getMethodName(),
                location.getLineNumber(),
                ex.getMessage());

        return ResponseEntity.notFound().build();
    }
}
