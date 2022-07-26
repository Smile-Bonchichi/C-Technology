package kg.smile.ctechnology.service.database.impl;

import kg.smile.ctechnology.entity.User;
import kg.smile.ctechnology.repository.UserRepository;
import kg.smile.ctechnology.service.base.impl.CrudServiceImpl;
import kg.smile.ctechnology.service.database.UserService;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl extends CrudServiceImpl<User> implements UserService {
    final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    public User getByLogin(String login) {
        return userRepository.getByLogin(login);
    }
}
