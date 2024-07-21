package vn.com.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import vn.com.demo.domain.Order;
import vn.com.demo.domain.OrderDetail;
import vn.com.demo.domain.User;
import vn.com.demo.repository.OrderDetailRepository;
import vn.com.demo.repository.OrderRepository;

@Service
public class OrderService {
  private final OrderRepository orderRepository;
  private final OrderDetailRepository orderDetailRepository;

  public OrderService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository) {
    this.orderRepository = orderRepository;
    this.orderDetailRepository = orderDetailRepository;
  }

  public List<Order> findAllOrder() {
    return this.orderRepository.findAll();
  }

  public List<OrderDetail> handlerFindOrderDetail(Long id) {
    List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
    List<OrderDetail> orderDetails = this.orderDetailRepository.findAll();
    for (OrderDetail item : orderDetails) {
      if (item.getOrder().getId() == id) {
        orderDetailList.add(item);
      }
    }

    return orderDetailList;
  }

  public void handlerOrder(Long id) {
    Optional<Order> orOptional = this.orderRepository.findById(id);
    if (orOptional.isPresent()) {
      Order order = orOptional.get();
      List<OrderDetail> orderDetails = this.orderDetailRepository.findAll();
      for (OrderDetail item : orderDetails) {
        if (item.getOrder().getId() == order.getId()) {
          this.orderDetailRepository.deleteById(item.getId());
        }
      }
      this.orderRepository.deleteById(order.getId());

    }

  }

  public Order findOrderById(Long id) {
    Optional<Order> order = this.orderRepository.findById(id);
    Order orderResult = new Order();
    if (order.isPresent()) {
      orderResult = order.get();
    }
    return orderResult;
  }

  @Transactional
  public void updateOrder(Order order) {
    Optional<Order> orderOptional = this.orderRepository.findById(order.getId());
    if (orderOptional.isPresent()) {
      Order orderUpdate = orderOptional.get();
      orderUpdate.setReceiverName(orderUpdate.getReceiverName());
      orderUpdate.setReceiverAddress(orderUpdate.getReceiverAddress());
      orderUpdate.setReceiverPhone(orderUpdate.getReceiverPhone());
      orderUpdate.setStatus(order.getStatus());
      orderUpdate.setUser(orderUpdate.getUser());
      orderUpdate.setOrderDetails(orderUpdate.getOrderDetails());
      this.orderRepository.saveAndFlush(orderUpdate);
    }
  }

  public List<Order> findOrderWithByUser(User user, HttpSession session) {
    List<Order> order = this.orderRepository.findByUser(user);
    return order;
  }
}
