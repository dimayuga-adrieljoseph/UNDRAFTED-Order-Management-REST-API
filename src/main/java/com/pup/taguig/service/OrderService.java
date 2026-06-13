package com.pup.taguig.service;

import java.util.List;

import com.pup.taguig.dto.OrderRequestDTO;
import com.pup.taguig.dto.OrderResponseDTO;

public interface OrderService {

    OrderResponseDTO createOrder(OrderRequestDTO request);
    OrderResponseDTO getOrderById(Long id);
    List<OrderResponseDTO> getOrdersByCustomerId(Long customerId);
    void cancelOrder(Long id);
}
