package com.github.hojoungjang.pt2_implementation.model;

import java.math.BigDecimal;

public class Account {
    private long id;
    private String name;
    private BigDecimal amount;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
