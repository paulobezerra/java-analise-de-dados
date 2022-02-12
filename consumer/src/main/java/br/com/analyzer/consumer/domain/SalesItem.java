package br.com.analyzer.consumer.domain;

import java.math.BigDecimal;

public class SalesItem {
    private Integer id;
    private Integer quantity;
    private BigDecimal price;

    public SalesItem(String[] chunks) {
        this.id = Integer.parseInt(chunks[0]);
        this.quantity = Integer.parseInt(chunks[1]);
        this.price = new BigDecimal(chunks[2]);
    }

    public Integer getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getTotal() {
        return new BigDecimal(quantity).multiply(price);
    }
}
