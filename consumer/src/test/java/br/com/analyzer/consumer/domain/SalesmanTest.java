package br.com.analyzer.consumer.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SalesmanTest {

    @Test
    public void createNewSalesman() {
        String line = "001ç3245678865434çPauloç40000.99";

        Salesman salesman = new Salesman(line.split("ç"));

        assertEquals("001", salesman.getType());
        assertEquals("3245678865434", salesman.getCpf());
        assertEquals("Paulo", salesman.getName());
        assertEquals(new BigDecimal("40000.99"), salesman.getSalary());
    }

}