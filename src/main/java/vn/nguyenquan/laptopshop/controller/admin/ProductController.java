package vn.nguyenquan.laptopshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.nguyenquan.laptopshop.domain.Product;

@Controller
public class ProductController {

    @GetMapping("/admin/product")
    public String getProductPage(Model model) {
        return "/admin/product/show";
    }

    // create
    @GetMapping("/admin/product/create")
    public String getMethodName(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

}
