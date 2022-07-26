package kg.smile.ctechnology.entity;

import kg.smile.ctechnology.entity.base.BaseEntity;
import kg.smile.ctechnology.enums.PaymentType;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "payment_log")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentLog extends BaseEntity {
    @Column(name = "sum", nullable = false)
    BigDecimal sum;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type", nullable = false)
    PaymentType paymentType;

    @Column(name = "rest_balance", nullable = false)
    BigDecimal restBalance;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;
}
