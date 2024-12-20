package vn.nguyenquan.laptopshop.service;

import java.util.List;
import org.springframework.stereotype.Service;

import vn.nguyenquan.laptopshop.domain.Product;
import vn.nguyenquan.laptopshop.repository.IProductRepository;

import java.util.Optional;

@Service
public class ProductService {

    private IProductRepository productRepository;

    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Save
    public Product handleSaveProduct(Product product) {
        return this.productRepository.save(product);
    }

    // Find All
    public List<Product> getAllProduct() {
        return this.productRepository.findAll();
    }

    // Find By ID
    // public Product getProductById(long id) {
    // return this.productRepository.findById(id);
    // }

    // Find Product By ID 2
    public Optional<Product> getProductById2(long id) {
        return this.productRepository.findById(id);
    }

    // Delete
    public void deleteProductById(long id) {
        this.productRepository.deleteById(id);
    }
}
