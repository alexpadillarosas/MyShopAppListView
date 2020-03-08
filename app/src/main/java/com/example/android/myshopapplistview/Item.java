package com.example.android.myshopapplistview;

import java.io.Serializable;
import java.math.BigDecimal;

public class Item implements Serializable {

    private BigDecimal price;
    private String name;
    private String description;

    public Item(BigDecimal price, String name, String description) {
        this.price = price;
        this.name = name;
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
