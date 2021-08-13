package com.hyecheon.shopme.admin.user;

import com.hyecheon.shopmecommon.entity.Role;
import com.hyecheon.shopmecommon.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/08/11
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    RoleRepository roleRepository;


    @DisplayName("1. 유저 생성")
    @Test
    void test_1() {
        final var role = Role.builder()
                .name("Admin")
                .description("manage everything")
                .build();
        final var savedRole = entityManager.persist(role);

        final var user = User.builder()
                .email("rainbow0616@naver.com")
                .password("1234")
                .firstName("lee")
                .lastName("hyecheon")
                .build();
        user.addRole(savedRole);

        final var savedUser = userRepository.save(user);

        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getRoles().size()).isEqualTo(1);
    }

    @DisplayName("2. 유저 생성")
    @Test
    void test_2() {

        final var user = User.builder()
                .email("ravi@gmail.com")
                .password("ravi2020")
                .firstName("Ravi")
                .lastName("Kumar")
                .build();

        final var roleEditor = Role.builder()
                .id(3L).build();
        final var roleAssistant = Role.builder()
                .id(5L).build();

        user.addRole(roleEditor, roleAssistant);

        final var savedUser = userRepository.save(user);

        assertThat(savedUser.getId()).isNotNull();

    }

    @DisplayName("3. 유저 목록들")
    @Test
    void test_3() {
        final var users = userRepository.findAll();
        users.forEach(System.out::println);

        assertThat(users.size()).isEqualTo(2);
    }

    @DisplayName("4. 유저 조회")
    @Test
    void test_4() {
        saveUser();
        final var findUser = userRepository.findById(1L);

        assertThat(findUser).isNotEmpty();
        assertThat(findUser.get().getId()).isEqualTo(1L);
    }

    @DisplayName("5. 유저 업데이트")
    @Test
    void test_5() {
        saveUser();
        final var findUser = userRepository.findById(1L);
        assertThat(findUser).isNotEmpty();
        final var user = findUser.get();
        user.setEnabled(true);
        user.setEmail("asdlkzjclk@gmail.com");
        entityManager.flush();
        final var reFindUser = userRepository.findById(1L);
        assertThat(reFindUser.get().isEnabled()).isTrue();
        assertThat(reFindUser.get().getEmail()).isEqualTo("asdlkzjclk@gmail.com");
    }

    @DisplayName("6. 권한 업데이트")
    @Test
    void test_6() {
        saveRole();
        saveUser();
        final var findUser = userRepository.findById(1L);
        assertThat(findUser).isNotEmpty();
        final var user = findUser.get();
        assertThat(user.getRoles().size()).isOne();
        user.getRoles().remove(Role.builder().id(1L).build());
        entityManager.flush();
        final var reFindUser = userRepository.findById(1L).get();
        assertThat(reFindUser.getRoles().size()).isZero();
    }

    @DisplayName("7. 유저 삭제")
    @Test
    void test_7() {
        saveRole();
        saveUser();

        final var users = userRepository.findAll();
        assertThat(users.size()).isGreaterThan(0);
        final var user1 = users.get(0);
        userRepository.delete(user1);
        final var users2 = userRepository.findAll();
        assertThat(users2).isNotIn(user1);
    }

    private void saveUser() {
        final var user1 = User.builder()
                .email("ravi@gmail.com")
                .password("ravi2020")
                .firstName("Ravi")
                .lastName("Kumar")
                .build();
        user1.addRole(Role.builder().id(1L).build());
        final var user2 = User.builder()
                .email("rainbow0616@naver.com")
                .password("1234")
                .firstName("lee")
                .lastName("hyecheon")
                .build();
        userRepository.saveAll(List.of(user1, user2));
    }

    private void saveRole() {
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
            Assertions.assertThat(role.getId()).isNotNull();
        }
    }
}