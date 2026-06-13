package com.pup.taguig.model;

import lombok.Data;

@Data
public class Product {
	private int id;
    private String name;
    private String description;
    private double price;
    private int stock;
}
