package com.pup.taguig.dto;

public class OrderItemResponseDTO {

    private Long id;
    private int productId;
    private int quantity;
    private double unitPrice;
    private double subtotal;

    public OrderItemResponseDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
}
