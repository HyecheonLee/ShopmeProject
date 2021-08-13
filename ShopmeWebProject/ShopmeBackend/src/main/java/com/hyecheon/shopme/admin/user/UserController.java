package com.hyecheon.shopme.admin.user;

import com.hyecheon.shopmecommon.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/08/12
 */
@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String listAll(Model model) {
        final var users = userService.listAll();
        model.addAttribute("users", users);
        if (!model.containsAttribute("message")) {
            model.addAttribute("message", null);
        }
        return "users";
    }

    @GetMapping("/users/new")
    public String newUser(Model model) {
        final var user = User.builder().enabled(true).build();
        model.addAttribute("user", user);
        final var roles = userService.roles();
        model.addAttribute("roles", roles);

        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        userService.save(user);
        redirectAttributes.addFlashAttribute("message", "사용자가 성공적으로 저장되었습니다.");
        return "redirect:/users";
    }
}
