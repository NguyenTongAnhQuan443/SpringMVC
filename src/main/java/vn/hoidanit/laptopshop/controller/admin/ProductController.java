package vn.hoidanit.laptopshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.qos.logback.core.model.Model;

@Controller
public class ProductController {

    @GetMapping("/admin/product")
    public String getProductPage(Model model) {
        return "/admin/product/show";
    }

}
