package com.codewithmosh.store.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/hello")
    public String sayHello() {
        return "hello admin";
    }

}
