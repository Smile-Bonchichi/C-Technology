package kg.smile.ctechnology.service.database.impl;

import kg.smile.ctechnology.entity.Wallet;
import kg.smile.ctechnology.repository.WalletRepository;
import kg.smile.ctechnology.service.base.impl.CrudServiceImpl;
import kg.smile.ctechnology.service.database.WalletService;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WalletServiceImpl extends CrudServiceImpl<Wallet> implements WalletService {
    final WalletRepository walletRepository;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository) {
        super(walletRepository);
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet getByUser(Long userId) {
        return walletRepository.getByUser(userId);
    }
}
