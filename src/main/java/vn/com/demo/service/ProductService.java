package vn.com.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

import vn.com.demo.domain.Cart;
import vn.com.demo.domain.CartDetail;
import vn.com.demo.domain.Order;
import vn.com.demo.domain.OrderDetail;
import vn.com.demo.domain.Product;
import vn.com.demo.domain.User;
import vn.com.demo.repository.CartDetailRepository;
import vn.com.demo.repository.CartRepository;
import vn.com.demo.repository.OrderDetailRepository;
import vn.com.demo.repository.OrderRepository;
import vn.com.demo.repository.ProductRepository;

@Service
public class ProductService {
  private final CartRepository cartRepository;
  private final CartDetailRepository cartDetailRepository;
  private final ProductRepository productRepository;
  private final UserService userService;
  private final OrderRepository orderRepository;
  private final OrderDetailRepository orderDetailRepository;

  public ProductService(ProductRepository productRepository, CartRepository cartRepository,
      CartDetailRepository cartDetailRepository, UserService userService, OrderRepository orderRepository,
      OrderDetailRepository orderDetailRepository) {
    this.productRepository = productRepository;
    this.cartRepository = cartRepository;
    this.cartDetailRepository = cartDetailRepository;
    this.userService = userService;
    this.orderRepository = orderRepository;
    this.orderDetailRepository = orderDetailRepository;
  }

  public Product saveProduct(Product product) {
    return productRepository.save(product);
  }

  public Page<Product> listProducts(Pageable page) {
    return this.productRepository.findAll(page);
  }

  public void deleteProductById(Long id) {
    Product product = this.productRepository.findById(id).get();
    this.productRepository.delete(product);
  }

  public Product findProductById(Long id) {
    return this.productRepository.findById(id).get();
  }

  public void handleAddProductToCart(String email, Long id, HttpSession session) {
    User user = this.userService.getUserByEmail(email);
    if (user != null) {
      // check user co cart hay chua ? , neu chua tao moi
      Cart cart = this.cartRepository.findByUser(user);
      if (cart == null) {
        // create Cart
        Cart otheCart = new Cart();
        otheCart.setUser(user);
        otheCart.setSum(0);

        cart = this.cartRepository.save(otheCart);
      }
      // save cart detail
      // tim product by id
      Optional<Product> product = this.productRepository.findById(id);
      if (product.isPresent()) {
        Product realProduct = product.get();
        // check san pham da tung duoc them vao gio hang truoc day chua
        CartDetail oldDetail = this.cartDetailRepository.findByCartAndProduct(cart, realProduct);
        if (oldDetail == null) {
          CartDetail cartDetail = new CartDetail();
          cartDetail.setCart(cart);
          cartDetail.setPrice(realProduct.getPrice());
          cartDetail.setProduct(realProduct);
          cartDetail.setQuantity(1);
          this.cartDetailRepository.save(cartDetail);
          // update sum
          int s = cart.getSum() + 1;
          cart.setSum(s);
          this.cartRepository.save(cart);
          session.setAttribute("sum", s);

        } else {
          oldDetail.setQuantity(oldDetail.getQuantity() + 1);
          this.cartDetailRepository.save(oldDetail);
        }

      }
    }
  }

  public Cart fetchByUser(User user) {
    return this.cartRepository.findByUser(user);
    // voi user nay tim ra gio hang cua ong
  }

  public void handleRenoveCartDetail(Long id, HttpSession session) {
    Optional<CartDetail> cartDetail = this.cartDetailRepository.findById(id);
    if (cartDetail.isPresent()) {
      CartDetail cartDetail2 = cartDetail.get();

      Cart currentCart = cartDetail2.getCart();
      // remove cartdeatil2
      this.cartDetailRepository.delete(cartDetail2);
      // update sum
      if (currentCart.getSum() > 1) {
        int sum = currentCart.getSum() - 1;
        currentCart.setSum(sum);
        session.setAttribute("sum", sum);
        this.cartRepository.saveAndFlush(currentCart);
      } else {
        this.cartRepository.delete(currentCart);
        session.setAttribute("sum", 0);
      }

    }
  }

  public void handleUpdateCartBeforeCheckout(List<CartDetail> cartDetails) {
    for (CartDetail cartDetail : cartDetails) {
      Optional<CartDetail> cdOptional = this.cartDetailRepository.findById(cartDetail.getId());
      if (cdOptional.isPresent()) {
        CartDetail currentCartDetail = cdOptional.get();
        currentCartDetail.setQuantity(cartDetail.getQuantity());
        this.cartDetailRepository.save(currentCartDetail);
      }
    }
  }

  public void handlePlaceOrder(HttpSession session, User user, String receiverName, String receiverAddress,
      String receiverPhone) {
    Order order = new Order();
    order.setUser(user);
    order.setReceiverName(receiverName);
    order.setReceiverAddress(receiverAddress);
    order.setReceiverPhone(receiverPhone);
    order.setStatus("PENDING");
    order = this.orderRepository.saveAndFlush(order);

    // create_order_detail
    // step 1 cart by user
    Cart cart = this.cartRepository.findByUser(user);
    if (cart != null) {
      List<CartDetail> cartDetails = cart.getCart_detail();
      if (cartDetails != null) {
        double totalPrice = 0;
        for (var cardDetail : cartDetails) {
          OrderDetail orderDetail = new OrderDetail();
          orderDetail.setOrder(order);
          orderDetail.setProduct(cardDetail.getProduct());
          orderDetail.setQuantity(cardDetail.getQuantity());
          orderDetail.setPrice(cardDetail.getPrice());
          this.orderDetailRepository.save(orderDetail);
          totalPrice = totalPrice + (cardDetail.getPrice() * cardDetail.getQuantity());
        }
        order.setTotalPrice(totalPrice);
        this.orderRepository.saveAndFlush(order);
        // step2 : delete cart_detail and cart
        for (CartDetail cartDetail : cartDetails) {
          this.cartDetailRepository.deleteById(cartDetail.getId());
        }
        this.cartRepository.deleteById(cart.getId());

        // step 3 : update session
        session.setAttribute("sum", 0);
      }
    }
  }

}
