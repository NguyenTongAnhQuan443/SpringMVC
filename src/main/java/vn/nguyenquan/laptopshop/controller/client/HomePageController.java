package vn.nguyenquan.laptopshop.controller.client;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import vn.nguyenquan.laptopshop.domain.Product;
import vn.nguyenquan.laptopshop.domain.User;
import vn.nguyenquan.laptopshop.domain.dto.RegisterDTO;
import vn.nguyenquan.laptopshop.service.ProductService;
import vn.nguyenquan.laptopshop.service.UserSevice;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class HomePageController {

    private final ProductService productService;
    private final UserSevice userSevice;
    private final PasswordEncoder passwordEncoder;

    public HomePageController(ProductService productService, UserSevice userSevice, PasswordEncoder passwordEncoder) {
        this.productService = productService;
        this.userSevice = userSevice;
        this.passwordEncoder = passwordEncoder;
    }

    // Home
    @GetMapping("/")
    public String getHomePage(Model model) {
        List<Product> products = this.productService.getAllProduct();
        model.addAttribute("products", products);
        return "/client/homepage/show";
    }

    // Register
    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerUser", new RegisterDTO());
        return "client/auth/register";
    }

    @PostMapping("/register")
    public String handleRegister(@ModelAttribute("registerUser") RegisterDTO registerDTO) {
        User user = this.userSevice.registerDTOtoUser(registerDTO);

        String hashPassword = this.passwordEncoder.encode(user.getPassword());
        user.setRole(this.userSevice.getRoleByName(hashPassword));

        this.userSevice.handleSaveUser(user);
        return "redirect:/login";
    }

    // Login
    @GetMapping("/login")
    public String getLoginPage(Model model) {
        return "client/auth/login";
    }

}
