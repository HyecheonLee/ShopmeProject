package com.hyecheon.shopme.admin.user;

import com.hyecheon.shopmecommon.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/08/11
 */
public interface UserRepository extends JpaRepository<User,Long> {
}
