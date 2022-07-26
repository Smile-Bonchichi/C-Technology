package kg.smile.ctechnology.exception;

import kg.smile.ctechnology.exception.base.BaseException;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException {
    public NotFoundException(String message,
                             Long statusCode,
                             HttpStatus httpStatus) {
        super(message, statusCode, httpStatus);
    }
}
