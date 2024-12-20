package vn.nguyenquan.laptopshop.repository;

import org.springframework.stereotype.Repository;

import vn.nguyenquan.laptopshop.domain.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product);

    // Product findById(long id);
}
