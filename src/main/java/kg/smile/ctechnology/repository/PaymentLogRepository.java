package kg.smile.ctechnology.repository;

import kg.smile.ctechnology.entity.PaymentLog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentLogRepository extends JpaRepository<PaymentLog, Long> {
}
