package com.hyecheon.shopme.admin.user;

import com.hyecheon.shopmecommon.entity.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/08/11
 */
@DataJpaTest
class RoleRepositoryTest {
    @Autowired
    RoleRepository roleRepository;

    @DisplayName("1. 롤 생성")
    @Test
    void test_1() {
        final var role = Role.builder()
                .name("Admin")
                .description("manage everything")
                .build();
        final var savedRole = roleRepository.save(role);
        assertThat(savedRole.getId()).isNotNull();
    }

    @DisplayName("2. 롤 생성")
    @Test
    void test_2() {

        final var salesperson = Role.builder()
                .name("Salesperson")
                .description("manage product price, customers, shopping, orders and sales report")
                .build();

        final var roleEditor = Role.builder()
                .name("Editor")
                .description("manage categories, brands, products, articles and menus")
                .build();

        final var roleShipper = Role.builder()
                .name("Shipper")
                .description("view products, view orders and update order status")
                .build();

        final var roleAssistant = Role.builder()
                .name("Assistant")
                .description("manage questions and reviews")
                .build();

        final var roles = roleRepository.saveAll(List.of(salesperson, roleEditor, roleShipper, roleShipper, roleAssistant));

        for (Role role : roles) {
            assertThat(role.getId()).isNotNull();
        }
    }
}