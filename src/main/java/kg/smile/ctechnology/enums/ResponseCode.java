package kg.smile.ctechnology.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ResponseCode {
    SUCCESS_AUTH(100, "Успешная авторизация"),
    USER_NOT_FOUND(101, "Такого пользователя нет в базе"),
    WRONG_PASSWORD(102, "Неправильный пароль"),

    SUCCESS_LOGOUT(200, "Успешный выход"),
    LOGOUT_ERROR(201, "Ошибка при выходе из системы"),

    SUCCESS_PAYMENT(300, "Успешный платеж"),
    PAYMENT_ERROR(301, "Ошибка при проведении платежа");

    final Integer statusCode;

    final String message;
}
