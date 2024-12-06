package vn.hoidanit.laptopshop.controller.admin;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

    private final UserSevice userSevice;

    public UserController(UserSevice userSevice) {
        this.userSevice = userSevice;
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("nguyenquan", "Nguyen Quan XIN CHAO");
        return "hello";
    }

    // Create
    @GetMapping("/admin/user/create")
    public String getCreateUserPages(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @PostMapping("/admin/user/create")
    public String createUserPage(Model model, @ModelAttribute("newUser") User user) {
        this.userSevice.handleSaveUser(user);
        return "redirect:/admin/user"; // redirect đến link url
    }

    // Show List User
    @GetMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = this.userSevice.getAllUser();
        model.addAttribute("users", users);
        return "admin/user/show";
    }

    // User Details
    @GetMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {
        User user = this.userSevice.getUserById(id);
        model.addAttribute("user", user);
        return "/admin/user/detail";
    }

    // Update
    @GetMapping("/admin/user/update/{id}")
    public String requestMethodName(Model model, @PathVariable long id) {
        User currentUser = this.userSevice.getUserById(id);
        model.addAttribute("newUser", currentUser);
        return "/admin/user/update";
    }

    @PostMapping("/admin/user/update")
    public String postUpdateUser(Model model, @ModelAttribute("newUser") User user) {
        User currentUser = this.userSevice.getUserById(user.getId());

        if (currentUser != null) {
            currentUser.setAddress(user.getAddress());
            currentUser.setFullName(user.getFullName());
            currentUser.setPhone(user.getPhone());
            this.userSevice.handleSaveUser(currentUser);
        }
        return "redirect:/admin/user";
    }

    // Delete

    @GetMapping("/admin/user/delete/{id}")
    public String getDeleteUserPage(Model model, @PathVariable long id) {
        model.addAttribute("id", id);

        User user = new User();
        user.setId(id);
        model.addAttribute("newUser", user);

        return "/admin/user/delete";
    }

    @PostMapping("/admin/user/delete")
    public String postDeleteUser(Model model, @ModelAttribute("newUser") User user) {
        this.userSevice.deleteAUser(user.getId());
        return "redirect:/admin/user";
    }

}