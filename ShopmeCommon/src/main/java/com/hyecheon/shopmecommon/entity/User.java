package com.hyecheon.shopmecommon.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/08/11
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128, nullable = false, unique = true, updatable = false)
    private String email;

    @Column(length = 64, nullable = false)
    private String password;

    @Column(length = 45, nullable = false)
    private String firstName;

    @Column(length = 45, nullable = false)
    private String lastName;

    @Column(length = 64)
    private String photos;

    private boolean enabled;

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role... roles) {
        for (Role role : roles) {
            addRole(role);
        }
    }

    public void addRole(Role role) {
        if (role != null) roles.add(role);
    }
}
