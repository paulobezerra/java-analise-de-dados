package br.com.analyzer.consumer.components;

import br.com.analyzer.consumer.domain.Customer;
import br.com.analyzer.consumer.domain.Registry;
import br.com.analyzer.consumer.domain.Sale;
import br.com.analyzer.consumer.domain.Salesman;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BuilderRegistry {

    @Value("${spring.domain.customerId}")
    String customerId;

    @Value("${spring.domain.salesmanId}")
    String salesmanId;

    @Value("${spring.domain.saleId}")
    String saleId;

    @Value("${spring.files.delimiterChar}")
    String delimiterChar;

    @Value("${spring.files.itemDelimiterChar}")
    String itemDelimiterChar;

    @Value("${spring.files.valuesDelimiterChar}")
    String valuesDelimiterChar;

    public Registry build(String line) throws Exception {
        String[] chunks = line.split(delimiterChar);

        if (chunks[0].equals(customerId)) {
            return new Customer(chunks);
        }

        if (chunks[0].equals(salesmanId)) {
            return new Salesman(chunks);
        }

        if (chunks[0].equals(saleId)) {
            return new Sale(chunks, itemDelimiterChar, valuesDelimiterChar);
        }

        throw new Exception(line);
    }
}
