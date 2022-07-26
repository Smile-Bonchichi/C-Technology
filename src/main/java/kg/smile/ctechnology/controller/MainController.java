package kg.smile.ctechnology.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import kg.smile.ctechnology.dto.request.UserAuthRequestDto;
import kg.smile.ctechnology.dto.response.UserAuthResponseDto;
import kg.smile.ctechnology.dto.response.base.BaseResponseDto;
import kg.smile.ctechnology.service.AuthLogoutService;
import kg.smile.ctechnology.service.PaymentService;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "Контроллер для авторизации, выхода из системы и платежа")
@RestController
@RequestMapping("/api/")
@CrossOrigin(maxAge = 3600, origins = "*")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MainController {
    final AuthLogoutService authLogoutService;

    final PaymentService paymentService;

    @Autowired
    public MainController(AuthLogoutService authLogoutService, PaymentService paymentService) {
        this.authLogoutService = authLogoutService;
        this.paymentService = paymentService;
    }

    @PostMapping("login")
    @ApiOperation("API авторизации в системе")
    public ResponseEntity<UserAuthResponseDto> authUser(@Valid @RequestBody UserAuthRequestDto userAuthRequestDto) {
        return ResponseEntity
                .ok(authLogoutService.authUser(userAuthRequestDto));
    }

    @GetMapping("logout")
    @ApiOperation("API для выхода из системы")
    public ResponseEntity<BaseResponseDto> logout() {
        return ResponseEntity
                .ok(authLogoutService.logout());
    }

    @GetMapping("payment")
    @ApiOperation("API для операции с балансом")
    public ResponseEntity<BaseResponseDto> payment() {
        return ResponseEntity
                .ok(paymentService.payment());
    }
}
