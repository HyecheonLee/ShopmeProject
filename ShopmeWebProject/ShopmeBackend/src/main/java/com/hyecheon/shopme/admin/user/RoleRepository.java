package com.hyecheon.shopme.admin.user;


import com.hyecheon.shopmecommon.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/08/11
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
