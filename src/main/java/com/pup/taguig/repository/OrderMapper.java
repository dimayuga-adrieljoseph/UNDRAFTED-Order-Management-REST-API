package com.pup.taguig.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pup.taguig.model.Order;
import com.pup.taguig.model.OrderItem;

@Mapper
public interface OrderMapper {

    // Order operations
    void insertOrder(Order order);
    Order getOrderById(Long id);
    List<Order> getOrdersByCustomerId(Long customerId);
    void updateOrderStatus(Order order);

    // OrderItem operations
    void insertOrderItem(OrderItem item);
    List<OrderItem> getItemsByOrderId(Long orderId);
}
