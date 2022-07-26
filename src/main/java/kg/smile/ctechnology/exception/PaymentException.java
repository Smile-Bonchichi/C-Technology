package kg.smile.ctechnology.exception;

import kg.smile.ctechnology.exception.base.BaseException;

import org.springframework.http.HttpStatus;

public class PaymentException extends BaseException {
    public PaymentException(String message,
                            Long statusCode,
                            HttpStatus httpStatus) {
        super(message, statusCode, httpStatus);
    }
}
