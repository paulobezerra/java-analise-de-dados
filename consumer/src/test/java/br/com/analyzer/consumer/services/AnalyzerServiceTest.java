package br.com.analyzer.consumer.services;

import br.com.analyzer.consumer.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AnalyzerServiceTest {

    @InjectMocks
    AnalyzerService analyzer;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void mustAnaliseRegisters() {
        Salesman salesman1 = new Salesman("001ç1234567891234çPedroç50000".split("ç"));
        Salesman salesman2 = new Salesman("001ç3245678865434çPauloç40000.99".split("ç"));

        Customer customer1 = new Customer("002ç2345675434544345çJose da SilvaçRural".split("ç"));
        Customer customer2 = new Customer("002ç2345675433444345çEduardo PereiraçRural".split("ç"));

        Sale sale1 = new Sale("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro".split("ç"), ",", "-");
        Sale sale2 = new Sale("003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo".split("ç"), ",", "-");

        List<Registry> registries = Arrays.asList(salesman1, salesman2, customer1, customer2, sale1, sale2);

        Analysis analysis = analyzer.analize(registries);

        assertEquals(2, analysis.getQuantityCustomer());
        assertEquals(2, analysis.getQuantitySalesman());
        assertEquals(10, analysis.getSaleMoreExpensive().getSalesId());
        assertEquals("Paulo", analysis.getWorstSeller().getName());
    }

}
