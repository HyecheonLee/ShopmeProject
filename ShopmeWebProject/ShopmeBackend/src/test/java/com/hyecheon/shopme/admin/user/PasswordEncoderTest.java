package com.hyecheon.shopme.admin.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/08/12
 */

public class PasswordEncoderTest {
    @DisplayName("1. 패스워드 인코드 테스트")
    @Test
    void test_1() {
        final var passwordEncoder = new BCryptPasswordEncoder();
        final var rawPassword = "nam2020";
        final var encodedPassword = passwordEncoder.encode(rawPassword);

        Assertions.assertThat(encodedPassword).isNotEqualTo(rawPassword);
        Assertions.assertThat(passwordEncoder.matches(rawPassword, encodedPassword)).isTrue();
    }

}
