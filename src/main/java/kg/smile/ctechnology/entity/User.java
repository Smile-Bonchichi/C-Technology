package kg.smile.ctechnology.entity;

import kg.smile.ctechnology.entity.base.BaseEntity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity {
    @Column(name = "login", nullable = false, unique = true, length = 15)
    String login;

    @Column(name = "password", nullable = false, length = 60)
    String password;

    @Column(name = "is_active", nullable = false, length = 3)
    Long isActive;

    @ManyToMany
    @JoinColumn(name = "role_id", nullable = false)
    List<Role> roles;
}
