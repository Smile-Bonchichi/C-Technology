package kg.smile.ctechnology.exception.base;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import org.springframework.http.HttpStatus;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseException extends RuntimeException {
    final Long statusCode;

    final HttpStatus httpStatus;

    public BaseException(String message,
                         Long statusCode,
                         HttpStatus httpStatus) {
        super(message);
        this.statusCode = statusCode;
        this.httpStatus = httpStatus;
    }
}
