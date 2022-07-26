package kg.smile.ctechnology.service.database.impl;

import kg.smile.ctechnology.entity.PaymentLog;
import kg.smile.ctechnology.repository.PaymentLogRepository;
import kg.smile.ctechnology.service.base.impl.CrudServiceImpl;
import kg.smile.ctechnology.service.database.PaymentLogService;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentLogServiceImpl extends CrudServiceImpl<PaymentLog> implements PaymentLogService {
    final PaymentLogRepository paymentLogRepository;

    @Autowired
    public PaymentLogServiceImpl(PaymentLogRepository paymentLogRepository) {
        super(paymentLogRepository);
        this.paymentLogRepository = paymentLogRepository;
    }
}
