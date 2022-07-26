package kg.smile.ctechnology.service.database;

import kg.smile.ctechnology.entity.Wallet;
import kg.smile.ctechnology.service.base.CrudService;

public interface WalletService extends CrudService<Wallet> {
    Wallet getByUser(Long userId);
}
