package com.hyecheon.shopme.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/08/11
 */
@Controller
public class HomeController {
    @GetMapping("")
    public String homePage() {
        return "index";
    }
}
