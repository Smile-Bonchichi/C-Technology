package kg.smile.ctechnology.repository;

import kg.smile.ctechnology.entity.Wallet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM wallets t WHERE t.user_id = :userId")
    Wallet getByUser(@Param("userId") Long userId);
}
