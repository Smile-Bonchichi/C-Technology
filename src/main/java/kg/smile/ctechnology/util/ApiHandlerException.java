package kg.smile.ctechnology.util;

import kg.smile.ctechnology.exception.base.BaseException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiHandlerException {
    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<Object> handleFailException(BaseException e) {
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(e.getMessage());
    }
}
