package vn.com.demo.domain;

import java.util.List;

import org.hibernate.annotations.GenerationTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotEmpty(message = "Vui lòng nhập tên sản phẩm")
  private String name;
  @NotNull(message = "Bắt buộc nhập")
  @Min(value = 1, message = "Vui lòng nhập lớn hơn hoặc bằng  1")
  @Max(value = 1000000, message = "Vui lòng nhập lớn hơn hoặc bằng 1000000")
  private double price;
  private String image;
  @NotEmpty(message = "Vui lòng nhập mô tả")
  @Column(columnDefinition = "MEDIUMTEXT")
  private String detailDesc;
  @NotEmpty(message = "Vui lòng nhập mô tả ngắn")
  private String shortDesc;
  @NotNull(message = "Bắt buộc nhập")
  @Min(value = 1, message = "Vui lòng nhập lớn hơn hoặc bằng  1")
  @Max(value = 1000000, message = "Vui lòng nhập lớn hơn hoặc bằng 1000000")
  private Long quantity;
  @NotNull(message = "Bắt buộc nhập")
  @Min(value = 1, message = "Vui lòng nhập lớn hơn hoặc bằng  1")
  @Max(value = 1000000, message = "Vui lòng nhập lớn hơn hoặc bằng 1000000")
  private Long sold;
  @NotEmpty(message = "Vui lòng chọn nhà sản xuất")
  private String factory;
  @NotEmpty(message = "Vui lòng chọn mục tiêu")
  private String target;

  public Product() {
  }

  public Product(Long id, String name, double price, String image, String detailDesc, String shortDesc, Long quantity,
      Long sold, String factory, String target) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.image = image;
    this.detailDesc = detailDesc;
    this.shortDesc = shortDesc;
    this.quantity = quantity;
    this.sold = sold;
    this.factory = factory;
    this.target = target;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getDetailDesc() {
    return detailDesc;
  }

  public void setDetailDesc(String detailDesc) {
    this.detailDesc = detailDesc;
  }

  public String getShortDesc() {
    return shortDesc;
  }

  public void setShortDesc(String shortDesc) {
    this.shortDesc = shortDesc;
  }

  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }

  public Long getSold() {
    return sold;
  }

  public void setSold(Long sold) {
    this.sold = sold;
  }

  public String getFactory() {
    return factory;
  }

  public void setFactory(String factory) {
    this.factory = factory;
  }

  public String getTarget() {
    return target;
  }

  public void setTarget(String target) {
    this.target = target;
  }

  @Override
  public String toString() {
    return "Products [id=" + id + ", name=" + name + ", price=" + price + ", image=" + image + ", detailDesc="
        + detailDesc + ", shortDesc=" + shortDesc + ", quantity=" + quantity + ", sold=" + sold + ", factory=" + factory
        + ", target=" + target + "]";
  }

}
