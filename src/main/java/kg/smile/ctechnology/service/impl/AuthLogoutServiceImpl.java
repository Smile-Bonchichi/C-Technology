package kg.smile.ctechnology.service.impl;

import kg.smile.ctechnology.dto.request.UserAuthRequestDto;
import kg.smile.ctechnology.dto.response.UserAuthResponseDto;
import kg.smile.ctechnology.dto.response.base.BaseResponseDto;
import kg.smile.ctechnology.entity.User;
import kg.smile.ctechnology.enums.ResponseCode;
import kg.smile.ctechnology.exception.LogoutException;
import kg.smile.ctechnology.exception.NotFoundException;
import kg.smile.ctechnology.exception.WrongPasswordException;
import kg.smile.ctechnology.service.AuthLogoutService;
import kg.smile.ctechnology.service.database.UserService;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthLogoutServiceImpl implements AuthLogoutService {
    final UserService userService;

    final PasswordEncoder passwordEncoder;


    @Autowired
    public AuthLogoutServiceImpl(UserService userService,
                                 PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserAuthResponseDto authUser(UserAuthRequestDto userAuthRequestDto) {
        User user = userService.getByLogin(userAuthRequestDto.getLogin());

        if (user == null) {
            throw new NotFoundException(
                    ResponseCode.USER_NOT_FOUND.getMessage(),
                    (long) ResponseCode.USER_NOT_FOUND.getStatusCode(),
                    HttpStatus.BAD_REQUEST
            );
        }

        if (passwordEncoder.matches(userAuthRequestDto.getPassword(), user.getPassword())) {
            user.setIsActive(1L);
            userService.saveOrUpdate(user);

            return UserAuthResponseDto.builder()
                    .token(
                            "Basic " + new String(
                                    Base64.getEncoder().encode(
                                            (userAuthRequestDto.getLogin() + ":" + userAuthRequestDto.getPassword()).getBytes()
                                    )
                            )
                    )
                    .status((long) ResponseCode.SUCCESS_AUTH.getStatusCode())
                    .message(ResponseCode.SUCCESS_AUTH.getMessage())
                    .build();
        }

        throw new WrongPasswordException(
                ResponseCode.WRONG_PASSWORD.getMessage(),
                (long) ResponseCode.WRONG_PASSWORD.getStatusCode(),
                HttpStatus.BAD_REQUEST
        );
    }

    @Override
    public BaseResponseDto logout() {
        User user = userService.getByLogin(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()
        );

        if (user == null) {
            throw new NotFoundException(
                    ResponseCode.USER_NOT_FOUND.getMessage(),
                    (long) ResponseCode.USER_NOT_FOUND.getStatusCode(),
                    HttpStatus.BAD_REQUEST
            );
        }

        try {
            user.setIsActive(-1L);
            userService.saveOrUpdate(user);

            return BaseResponseDto.builder()
                    .message(ResponseCode.SUCCESS_LOGOUT.getMessage())
                    .status((long) ResponseCode.SUCCESS_LOGOUT.getStatusCode())
                    .build();
        } catch (Exception ignored) {
            throw new LogoutException(
                    ResponseCode.LOGOUT_ERROR.getMessage(),
                    (long) ResponseCode.LOGOUT_ERROR.getStatusCode(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
