package com.github.hojoungjang.pt2_implementation.model;

import java.math.BigDecimal;

public class Purchase {
    private int id;
    private String product;
    private BigDecimal price;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProduct() {
        return product;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
