package br.com.analyzer.consumer.services;

import br.com.analyzer.consumer.domain.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnalyzerService {

    public Analysis analize(List<Registry> registries) {
        Analysis analysis = new Analysis();
        analysis.setQuantityCustomer(this.countCustomers(registries));
        analysis.setQuantitySalesman(this.countSalesman(registries));
        analysis.setSaleMoreExpensive(this.searchSaleMoreExpensive(registries));
        analysis.setWorstSeller(this.searchWorstSalesman(registries));
        return analysis;
    }

    private long countCustomers(List<Registry> registries) {
        return registries.stream().filter(r -> r.getClass().equals(Customer.class)).count();
    }

    private long countSalesman(List<Registry> registries) {
        return registries.stream().filter(r -> r.getClass().equals(Salesman.class)).count();
    }

    private Sale searchSaleMoreExpensive(List<Registry> registries) {
        List<Sale> sales = registries.stream()
                .filter(r -> r.getClass().equals(Sale.class))
                .map(r -> (Sale) r)
                .collect(Collectors.toList());

        if (sales.size() == 0) return null;

        return sales.stream().max(Sale::compareByTotal).get();
    }

    private Salesman searchWorstSalesman(List<Registry> registries) {
        List<Salesman> salesmen = registries.stream()
                .filter(r -> r.getClass().equals(Salesman.class))
                .map(r -> (Salesman) r)
                .collect(Collectors.toList());

        List<Sale> sales = registries.stream()
                .filter(r -> r.getClass().equals(Sale.class))
                .map(r -> (Sale) r)
                .collect(Collectors.toList());

        salesmen.forEach(salesman -> {
            salesman.setSalesTotal(sales.stream()
                    .filter(s -> s.getSalesman().equals(salesman.getName()))
                    .map(s -> s.getTotal())
                    .reduce(BigDecimal.ZERO, BigDecimal::add));
        });

        return salesmen.stream().min(Salesman::compareByTotal).get();
    }
}
