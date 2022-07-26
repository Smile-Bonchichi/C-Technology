package kg.smile.ctechnology.entity;

import kg.smile.ctechnology.entity.base.BaseEntity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role extends BaseEntity {
    @Column(name = "role_name", nullable = false, unique = true, length = 50)
    String roleName;
}
