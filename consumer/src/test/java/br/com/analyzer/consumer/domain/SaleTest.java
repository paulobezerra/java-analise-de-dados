package br.com.analyzer.consumer.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {

    @Test
    public void CreateSale() {
        String line = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro";

        Sale sale = new Sale(line.split("ç"), ",", "-");

        assertEquals("003", sale.getType());
        assertEquals(10, sale.getSalesId());

        List<SalesItem> items = sale.getItens();
        assertEquals(1, items.get(0).getId());
        assertEquals(10, items.get(0).getQuantity());
        assertEquals(new BigDecimal("100"), items.get(0).getPrice());
        assertEquals(new BigDecimal("1000"), items.get(0).getTotal());

        assertEquals(2, items.get(1).getId());
        assertEquals(30, items.get(1).getQuantity());
        assertEquals(new BigDecimal("2.50"), items.get(1).getPrice());
        assertEquals(new BigDecimal("75.00"), items.get(1).getTotal());

        assertEquals(3, items.get(2).getId());
        assertEquals(40, items.get(2).getQuantity());
        assertEquals(new BigDecimal("3.10"), items.get(2).getPrice());
        assertEquals(new BigDecimal("124.00"), items.get(2).getTotal());
    }

}