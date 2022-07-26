package kg.smile.ctechnology.service.database.impl;

import kg.smile.ctechnology.entity.Role;
import kg.smile.ctechnology.repository.RoleRepository;
import kg.smile.ctechnology.service.base.impl.CrudServiceImpl;
import kg.smile.ctechnology.service.database.RoleService;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleServiceImpl extends CrudServiceImpl<Role> implements RoleService {
    final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        super(roleRepository);
        this.roleRepository = roleRepository;
    }
}
