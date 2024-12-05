package vn.hoidanit.laptopshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UserSevice;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserSevice userSevice;

    public UserController(UserSevice userSevice) {
        this.userSevice = userSevice;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("nguyenquan", "Nguyen Quan XIN CHAO");
        return "hello";
    }

    // Lấy trang Create GET
    @RequestMapping(value = "/admin/user/create", method = RequestMethod.GET)
    public String getCreateUserPages(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    // Lấy List User
    @RequestMapping(value = "/admin/user", method = RequestMethod.GET)
    public String getUserPage(Model model) {
        List<User> users = this.userSevice.getAllUser();
        model.addAttribute("users", users);
        return "admin/user/table-user";
    }

    // Lấy trang Create POST
    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") User user) {
        this.userSevice.handleSaveUser(user);
        return "redirect:/admin/user"; // redirect đến link url
    }

    // Details
    @RequestMapping(value = "/admin/user/{id}", method = RequestMethod.GET)
    public String getUserDetailPage(Model model, @PathVariable long id) {
        model.addAttribute("idUser", id);
        return "/admin/user/show";
    }

    // Update

    // Delete
}