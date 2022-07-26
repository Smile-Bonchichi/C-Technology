package kg.smile.ctechnology.boot;

import kg.smile.ctechnology.entity.PaymentLog;
import kg.smile.ctechnology.entity.Role;
import kg.smile.ctechnology.entity.User;
import kg.smile.ctechnology.entity.Wallet;
import kg.smile.ctechnology.enums.Currency;
import kg.smile.ctechnology.enums.PaymentType;
import kg.smile.ctechnology.service.database.PaymentLogService;
import kg.smile.ctechnology.service.database.RoleService;
import kg.smile.ctechnology.service.database.UserService;
import kg.smile.ctechnology.service.database.WalletService;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationStartRunner implements CommandLineRunner {
    final UserService userService;

    final RoleService roleService;

    final PasswordEncoder passwordEncoder;

    final WalletService walletService;

    final PaymentLogService paymentLogService;

    @Autowired
    public ApplicationStartRunner(UserService userService,
                                  RoleService roleService,
                                  PasswordEncoder passwordEncoder,
                                  WalletService walletService,
                                  PaymentLogService paymentLogService) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.walletService = walletService;
        this.paymentLogService = paymentLogService;
    }

    @Override
    public void run(String... args) {
        Role role = roleService.saveOrUpdate(
                Role.builder()
                        .roleName("ROLE_USER")
                        .build()
        );

        User user = userService.saveOrUpdate(
                User.builder()
                        .login("smile")
                        .password(passwordEncoder.encode("12345678"))
                        .isActive(1L)
                        .roles(Collections.singletonList(role))
                        .build()
        );

        Wallet wallet = walletService.saveOrUpdate(
                Wallet.builder()
                        .balance(BigDecimal.valueOf(8))
                        .currency(Currency.USD)
                        .user(user)
                        .build()
        );

        paymentLogService.saveOrUpdate(
                PaymentLog.builder()
                        .sum(wallet.getBalance())
                        .paymentType(PaymentType.IN)
                        .restBalance(wallet.getBalance())
                        .user(user)
                        .build()
        );
    }
}
