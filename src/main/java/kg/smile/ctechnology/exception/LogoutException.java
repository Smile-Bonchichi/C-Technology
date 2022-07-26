package kg.smile.ctechnology.exception;

import kg.smile.ctechnology.exception.base.BaseException;

import org.springframework.http.HttpStatus;

public class LogoutException extends BaseException {
    public LogoutException(String message,
                           Long statusCode,
                           HttpStatus httpStatus) {
        super(message, statusCode, httpStatus);
    }
}
