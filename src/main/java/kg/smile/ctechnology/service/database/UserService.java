package kg.smile.ctechnology.service.database;

import kg.smile.ctechnology.entity.User;
import kg.smile.ctechnology.service.base.CrudService;

public interface UserService extends CrudService<User> {
    User getByLogin(String login);
}
