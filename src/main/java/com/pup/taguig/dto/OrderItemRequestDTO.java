package com.pup.taguig.dto;

public class OrderItemRequestDTO {

    private int productId;
    private int quantity;

    public OrderItemRequestDTO() {}

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
