package br.com.analyzer.consumer.components;

import br.com.analyzer.consumer.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@ExtendWith(MockitoExtension.class)
class BuilderRegistryTest {

    @InjectMocks
    BuilderRegistry builder;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(builder, "salesmanId", "001");
        ReflectionTestUtils.setField(builder, "customerId", "002");
        ReflectionTestUtils.setField(builder, "saleId", "003");
        ReflectionTestUtils.setField(builder, "delimiterChar", "ç");
        ReflectionTestUtils.setField(builder, "itemDelimiterChar", ",");
        ReflectionTestUtils.setField(builder, "valuesDelimiterChar", "-");
    }

    @Test
    public void createNewSalesman() throws Exception {
        String line = "001ç3245678865434çPauloç40000.99";

        Registry registry = builder.build(line);

        assertInstanceOf(Salesman.class, registry);

        Salesman salesman = (Salesman) registry;

        assertEquals("001", salesman.getType());
        assertEquals("3245678865434", salesman.getCpf());
        assertEquals("Paulo", salesman.getName());
        assertEquals(new BigDecimal("40000.99"), salesman.getSalary());
    }

    @Test
    public void createNewCustomer() throws Exception {
        String line = "002ç2345675433444345çEduardo PereiraçRural";

        Registry registry = builder.build(line);

        assertInstanceOf(Customer.class, registry);

        Customer customer = (Customer) registry;

        assertEquals("002", customer.getType());
        assertEquals("2345675433444345", customer.getCnpj());
        assertEquals("Eduardo Pereira", customer.getName());
        assertEquals("Rural", customer.getBusinessArea());
    }

    @Test
    public void createNewSale() throws Exception {
        String line = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro";

        Registry registry = builder.build(line);

        assertInstanceOf(Sale.class, registry);

        Sale sale = (Sale) registry;

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