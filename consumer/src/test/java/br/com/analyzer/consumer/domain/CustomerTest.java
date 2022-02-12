package br.com.analyzer.consumer.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    public void createNewCustomer() {
        String line = "002ç2345675433444345çEduardo PereiraçRural";

        Customer customer = new Customer(line.split("ç"));

        assertEquals("002", customer.getType());
        assertEquals("2345675433444345", customer.getCnpj());
        assertEquals("Eduardo Pereira", customer.getName());
        assertEquals("Rural", customer.getBusinessArea());
    }

}