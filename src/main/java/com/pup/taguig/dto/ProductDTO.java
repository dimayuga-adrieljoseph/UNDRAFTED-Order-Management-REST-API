package com.pup.taguig.dto;

import lombok.Data;

@Data
public class ProductDTO {
	private Integer id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
}
