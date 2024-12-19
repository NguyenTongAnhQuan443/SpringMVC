package vn.nguyenquan.laptopshop.controller.admin;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.nguyenquan.laptopshop.domain.User;
import vn.nguyenquan.laptopshop.service.RoleService;
import vn.nguyenquan.laptopshop.service.UploadService;
import vn.nguyenquan.laptopshop.service.UserSevice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserSevice userSevice;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public UserController(UserSevice userSevice, UploadService uploadService, PasswordEncoder passwordEncoder,
            RoleService roleService) {
        this.userSevice = userSevice;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    // Create
    @GetMapping("/admin/user/create")
    public String getCreateUserPages(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @PostMapping("/admin/user/create")
    public String createUserPage(Model model,
            @ModelAttribute("newUser") @Valid User user,
            BindingResult newUserBindingResult,
            @RequestParam("NguyenQuanFile") MultipartFile file) {

        // Validate
        List<FieldError> errors = newUserBindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(error.getField() + " - " + error.getDefaultMessage());
        }

        if (newUserBindingResult.hasErrors()) {
            return "/admin/user/create";
        }
        //
        String avatar = this.uploadService.handleSaveUploadFile(file, "avatar"); // avatar là tên thư mục lưu file
        String hashPassword = this.passwordEncoder.encode(user.getPassword());

        user.setAvatar(avatar);
        user.setPassword(hashPassword);
        user.setRole(this.roleService.getRoleByName(user.getRole().getName()));

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
    public String postUpdateUser(Model model, @ModelAttribute("newUser") @Valid User user,
            BindingResult updateUseBindingResult) {

        // Validate
        List<FieldError> errors = updateUseBindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(error.getField() + " - " + error.getDefaultMessage());
        }

        if (updateUseBindingResult.hasErrors()) {
            return "/admin/user/update";
        }
        //
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