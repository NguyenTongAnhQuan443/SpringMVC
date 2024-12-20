package vn.nguyenquan.laptopshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.nguyenquan.laptopshop.domain.Product;
import vn.nguyenquan.laptopshop.service.ProductService;
import vn.nguyenquan.laptopshop.service.UploadService;

import java.util.List;
import vn.nguyenquan.laptopshop.domain.Product;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ProductController {

    private final UploadService uploadService;
    private final ProductService productService;

    public ProductController(UploadService uploadService, ProductService productService) {
        this.uploadService = uploadService;
        this.productService = productService;
    }

    // Get List Product
    @GetMapping("/admin/product")
    public String getProductPage(Model model) {
        List<Product> products = this.productService.getAllProduct();
        model.addAttribute("products", products);
        return "/admin/product/show";
    }

    // create
    @GetMapping("/admin/product/create")
    public String getCreateProductPage(Model model) {
        model.addAttribute("newProduct", new Product());
        return "/admin/product/create";
    }

    @PostMapping("/admin/product/create")
    public String createProductPage(Model model, @ModelAttribute("newProduct") @Valid Product product,
            BindingResult newProducBindingResult, @RequestParam("NguyenQuanFile") MultipartFile file) {

        // Validate
        List<FieldError> errors = newProducBindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(error.getField() + " - " + error.getDefaultMessage());
        }

        if (newProducBindingResult.hasErrors()) {
            return "/admin/product/create";
        }
        //

        String avatar = this.uploadService.handleSaveUploadFile(file, "avatar"); // avatar là tên thư mục lưu file

        product.setImage(avatar);

        this.productService.handleSaveProduct(product);
        return "redirect:/admin/product"; // redirect đến link url
    }

}
