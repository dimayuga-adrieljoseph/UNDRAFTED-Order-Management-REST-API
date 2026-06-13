package com.pup.taguig.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pup.taguig.dto.OrderItemRequestDTO;
import com.pup.taguig.dto.OrderItemResponseDTO;
import com.pup.taguig.dto.OrderRequestDTO;
import com.pup.taguig.dto.OrderResponseDTO;
import com.pup.taguig.model.Order;
import com.pup.taguig.model.OrderItem;
import com.pup.taguig.model.Product;
import com.pup.taguig.repository.OrderMapper;
import com.pup.taguig.repository.ProductMapper;
import com.pup.taguig.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public OrderResponseDTO createOrder(OrderRequestDTO request) {
        // Validate each item: product exists and has sufficient stock
        for (OrderItemRequestDTO itemReq : request.getItems()) {
            Product product = productMapper.getProductById(itemReq.getProductId());
            if (product == null) {
                throw new IllegalArgumentException("Product not found: " + itemReq.getProductId());
            }
            if (product.getStock() < itemReq.getQuantity()) {
                throw new IllegalArgumentException(
                    "Insufficient stock for product: " + product.getName()
                    + ". Available: " + product.getStock()
                );
            }
        }

        // Create the order record
        Order order = new Order();
        order.setCustomerId(request.getCustomerId());
        order.setStatus("PENDING");
        order.setTotalPrice(0); // will update after computing items
        orderMapper.insertOrder(order);

        // Insert items, reduce stock, and compute total
        double total = 0;
        List<OrderItem> savedItems = new ArrayList<>();

        for (OrderItemRequestDTO itemReq : request.getItems()) {
            Product product = productMapper.getProductById(itemReq.getProductId());

            OrderItem item = new OrderItem();
            item.setOrderId(order.getId());
            item.setProductId(itemReq.getProductId());
            item.setQuantity(itemReq.getQuantity());
            item.setUnitPrice(product.getPrice());
            orderMapper.insertOrderItem(item);

            // Reduce stock
            product.setStock(product.getStock() - itemReq.getQuantity());
            productMapper.updateStock(product);

            total += product.getPrice() * itemReq.getQuantity();
            savedItems.add(item);
        }

        // Update total price on the order
        order.setTotalPrice(total);
        orderMapper.updateOrderStatus(order); // reuse update to also persist total

        return buildResponse(order, savedItems);
    }

    @Override
    public OrderResponseDTO getOrderById(Long id) {
        Order order = orderMapper.getOrderById(id);
        if (order == null) {
            return null;
        }
        List<OrderItem> items = orderMapper.getItemsByOrderId(id);
        return buildResponse(order, items);
    }

    @Override
    public List<OrderResponseDTO> getOrdersByCustomerId(Long customerId) {
        List<Order> orders = orderMapper.getOrdersByCustomerId(customerId);
        List<OrderResponseDTO> result = new ArrayList<>();
        for (Order order : orders) {
            List<OrderItem> items = orderMapper.getItemsByOrderId(order.getId());
            result.add(buildResponse(order, items));
        }
        return result;
    }

    @Override
    public void cancelOrder(Long id) {
        Order order = orderMapper.getOrderById(id);
        if (order == null) {
            throw new IllegalArgumentException("Order not found: " + id);
        }
        if (!"PENDING".equals(order.getStatus())) {
            throw new IllegalStateException("Only PENDING orders can be cancelled. Current status: " + order.getStatus());
        }

        // Restore stock for each item
        List<OrderItem> items = orderMapper.getItemsByOrderId(id);
        for (OrderItem item : items) {
            Product product = productMapper.getProductById(item.getProductId());
            if (product != null) {
                product.setStock(product.getStock() + item.getQuantity());
                productMapper.updateStock(product);
            }
        }

        order.setStatus("CANCELLED");
        orderMapper.updateOrderStatus(order);
    }

    // --- Helper ---
    private OrderResponseDTO buildResponse(Order order, List<OrderItem> items) {
        OrderResponseDTO response = new OrderResponseDTO();
        response.setId(order.getId());
        response.setCustomerId(order.getCustomerId());
        response.setStatus(order.getStatus());
        response.setTotalPrice(order.getTotalPrice());

        List<OrderItemResponseDTO> itemDTOs = new ArrayList<>();
        for (OrderItem item : items) {
            OrderItemResponseDTO dto = new OrderItemResponseDTO();
            dto.setId(item.getId());
            dto.setProductId(item.getProductId());
            dto.setQuantity(item.getQuantity());
            dto.setUnitPrice(item.getUnitPrice());
            dto.setSubtotal(item.getUnitPrice() * item.getQuantity());
            itemDTOs.add(dto);
        }
        response.setItems(itemDTOs);
        return response;
    }
}
