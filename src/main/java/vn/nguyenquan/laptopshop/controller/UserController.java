package vn.nguyenquan.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.nguyenquan.laptopshop.service.UserSevice;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    private UserSevice userSevice;

    public UserController(UserSevice userSevice) {
        this.userSevice = userSevice;
    }

    @RequestMapping("/")
    public String getHomePage() {
        return this.userSevice.handleHello();
    }
}