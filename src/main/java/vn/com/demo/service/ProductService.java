package vn.com.demo.service;

import org.springframework.stereotype.Service;
import java.util.List;
import vn.com.demo.domain.Product;
import vn.com.demo.repository.ProductRepository;

@Service
public class ProductService {
  private ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Product saveProduct(Product product) {
    return productRepository.save(product);
  }

  public List<Product> listProducts() {
    return this.productRepository.findAll();
  }

  public void deleteProductById(Long id) {
    Product product = this.productRepository.findById(id).get();
    this.productRepository.delete(product);
  }

  public Product findProductById(Long id) {
    return this.productRepository.findById(id).get();
  }

}
