package kg.smile.ctechnology.entity;

import kg.smile.ctechnology.entity.base.BaseEntity;
import kg.smile.ctechnology.enums.Currency;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "wallets")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Wallet extends BaseEntity {
    @Column(name = "balance", nullable = false)
    BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false)
    Currency currency;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    User user;
}
