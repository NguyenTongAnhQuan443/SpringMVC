package vn.hoidanit.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.laptopshop.service.UserSevice;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    private UserSevice userSevice;

    public UserController(UserSevice userSevice) {
        this.userSevice = userSevice;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        String test = this.userSevice.handleHello();
        model.addAttribute("eric", test);
        model.addAttribute("nguyenquan", "Nguyen Quan XIN CHAO");
        return "hello";
    }
}