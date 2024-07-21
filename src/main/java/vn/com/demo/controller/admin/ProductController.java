package vn.com.demo.controller.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import vn.com.demo.domain.Product;
import vn.com.demo.service.ProductService;
import vn.com.demo.service.UploadFileService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;

@Controller
public class ProductController {

  private UploadFileService uploadFileService;
  private ProductService productService;

  public ProductController(UploadFileService uploadFileService, ProductService productService) {
    this.uploadFileService = uploadFileService;
    this.productService = productService;
  }

  @GetMapping("/admin/product")
  public String getProduct(Model model, @RequestParam("page") Optional<String> pageOptional) {
    int pageResult = 1;
    try {
      if (pageOptional.isPresent()) {
        pageResult = Integer.parseInt(pageOptional.get());
      } else {
        pageResult = 1;
      }
    } catch (Exception e) {
    }
    Pageable pageable = PageRequest.of(pageResult - 1, 5);
    Page<Product> products = this.productService.listProducts(pageable);
    List<Product> productLits = products.getContent();
    model.addAttribute("products", productLits);
    model.addAttribute("currentPage", pageResult);
    model.addAttribute("totalPages", products.getTotalPages());
    return "admin/product/show";
  }

  @GetMapping("/admin/product/create")
  public String showCreateProduct(Model model) {
    Product product = new Product();
    model.addAttribute("product", product);
    return "admin/product/create";
  }

  @PostMapping("/create-product")
  public String createProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult,
      @RequestParam("hoidanitFile") MultipartFile file) {

    List<FieldError> errors = bindingResult.getFieldErrors();

    for (FieldError fieldError : errors) {
      System.out.println(fieldError + ": " + fieldError.getDefaultMessage());
    }

    if (bindingResult.hasErrors()) {
      return "admin/product/create";
    }
    String productLink = this.uploadFileService.handleSaveUploadFile(file, "product");

    Product productInit = new Product();
    productInit.setId(product.getId());
    productInit.setName(product.getName());
    productInit.setPrice(product.getPrice());
    productInit.setImage(productLink);
    productInit.setDetailDesc(product.getDetailDesc());
    productInit.setShortDesc(product.getShortDesc());
    productInit.setQuantity(product.getQuantity());
    productInit.setSold(product.getSold());
    productInit.setFactory(product.getFactory());
    productInit.setTarget(product.getTarget());
    Product resProduct = this.productService.saveProduct(productInit);
    return "redirect:/admin/product";
  }

  @GetMapping("/admin/product/delete/{productId}")
  public String deleteProduct(@PathVariable("productId") Long productId) {
    this.productService.deleteProductById(productId);
    return "redirect:/admin/product";
  }

  @GetMapping("/admin/product/edit/{productId}")
  public String editProduct(@PathVariable("productId") Long productId, Model model) {
    Product product = this.productService.findProductById(productId);
    model.addAttribute("product", product);
    System.out.println(product.getImage());
    return "admin/product/detail";
  }

  @GetMapping("/admin/product/update/{productId}")
  public String getProduct(@PathVariable Long productId, Model model) {
    Product product = this.productService.findProductById(productId);
    if (product != null) {
      model.addAttribute("product", product);
      return "admin/product/update";
    }
    return "redirect:/admin/product";
  }

  @PostMapping("/admin/product/update/{productId}")
  public String updateProductById(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult,
      @PathVariable("productId") Long productId,
      @RequestParam("hoidanitFile") MultipartFile file) {
    if (bindingResult.hasErrors()) {
      return "admin/product/update";
    }
    Product productCurrent = this.productService.findProductById(productId);
    if (productCurrent != null) {
      if (!file.isEmpty()) {
        String productLink = this.uploadFileService.handleSaveUploadFile(file, "product");
        productCurrent.setImage(productLink);
      }
      productCurrent.setName(product.getName());
      productCurrent.setPrice(product.getPrice());
      productCurrent.setDetailDesc(product.getDetailDesc());
      productCurrent.setShortDesc(product.getShortDesc());
      productCurrent.setQuantity(product.getQuantity());
      productCurrent.setSold(product.getSold());
      productCurrent.setFactory(product.getFactory());
      productCurrent.setTarget(product.getTarget());
      return "redirect:/admin/product";
    }
    return "admin/product/update";
  }
}
