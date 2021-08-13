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
                .description("모든 것을 관리")
                .build();
        final var savedRole = roleRepository.save(role);
        assertThat(savedRole.getId()).isNotNull();
    }

    @DisplayName("2. 롤 생성")
    @Test
    void test_2() {

        final var salesperson = Role.builder()
                .name("Salesperson")
                .description("제품 가격, 고객, 쇼핑, 주문 및 판매 보고서 관리")
                .build();

        final var roleEditor = Role.builder()
                .name("Editor")
                .description("카테고리, 브랜드, 제품, 기사 및 메뉴 관리")
                .build();

        final var roleShipper = Role.builder()
                .name("Shipper")
                .description("제품 보기, 주문 보기 및 주문 상태 업데이트")
                .build();

        final var roleAssistant = Role.builder()
                .name("Assistant")
                .description("질문 및 리뷰 관리")
                .build();

        final var roles = roleRepository.saveAll(List.of(salesperson, roleEditor, roleShipper, roleShipper, roleAssistant));

        for (Role role : roles) {
            assertThat(role.getId()).isNotNull();
        }
    }
}