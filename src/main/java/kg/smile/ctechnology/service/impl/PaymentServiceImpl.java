package kg.smile.ctechnology.service.impl;

import kg.smile.ctechnology.dto.response.base.BaseResponseDto;
import kg.smile.ctechnology.entity.PaymentLog;
import kg.smile.ctechnology.entity.User;
import kg.smile.ctechnology.entity.Wallet;
import kg.smile.ctechnology.enums.PaymentType;
import kg.smile.ctechnology.enums.ResponseCode;
import kg.smile.ctechnology.exception.NotFoundException;
import kg.smile.ctechnology.exception.PaymentException;
import kg.smile.ctechnology.service.PaymentService;
import kg.smile.ctechnology.service.database.PaymentLogService;
import kg.smile.ctechnology.service.database.UserService;
import kg.smile.ctechnology.service.database.WalletService;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentServiceImpl implements PaymentService {
    final UserService userService;

    final WalletService walletService;

    final PaymentLogService paymentLogService;

    @Autowired
    public PaymentServiceImpl(UserService userService,
                              WalletService walletService,
                              PaymentLogService paymentLogService) {
        this.userService = userService;
        this.walletService = walletService;
        this.paymentLogService = paymentLogService;
    }

    @Override
    public BaseResponseDto payment() {
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
            Wallet wallet = walletService.getByUser(user.getId());
            PaymentLog paymentLog = paymentLogService.saveOrUpdate(
                    PaymentLog.builder()
                            .sum(BigDecimal.valueOf(1.1))
                            .paymentType(PaymentType.OUT)
                            .restBalance(wallet.getBalance().subtract(BigDecimal.valueOf(1.1)))
                            .user(user)
                            .build()
            );
            wallet.setBalance(paymentLog.getRestBalance());
            walletService.saveOrUpdate(wallet);

            return BaseResponseDto.builder()
                    .message(ResponseCode.SUCCESS_PAYMENT.getMessage())
                    .status((long) ResponseCode.SUCCESS_PAYMENT.getStatusCode())
                    .build();
        } catch (Exception ignored) {
            throw new PaymentException(
                    ResponseCode.PAYMENT_ERROR.getMessage(),
                    (long) ResponseCode.PAYMENT_ERROR.getStatusCode(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
