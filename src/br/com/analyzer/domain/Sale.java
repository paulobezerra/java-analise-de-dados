package br.com.analyzer.domain;

import br.com.analyzer.config.Config;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sale extends Registry{
    private Integer salesId;
    private List<SalesItem> itens;
    private String salesman;

    public Sale(String[] chunks) {
        super(chunks[0]);
        Config config = new Config();
        this.salesId = Integer.parseInt(chunks[1]);
        this.salesman = chunks[3];
        this.itens = new ArrayList<>();
        String[] itens = chunks[2]
                .replace("[", "")
                .replace("]", "")
                .split(config.getItemDelimiterChar());
        Arrays.asList(itens).forEach(itemString -> {
            String[] itemChunks = itemString.split(config.getItemValuesDelimiterChar());
            SalesItem item = new SalesItem(itemChunks);
            this.itens.add(item);
        });
    }

    public BigDecimal getTotal() {
        return this.itens.stream()
                .map(item -> item.getTotal())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Integer getSalesId() {
        return salesId;
    }

    public List<SalesItem> getItens() {
        return itens;
    }

    public String getSalesman() {
        return salesman;
    }

    public static int compareByTotal(Sale a, Sale b) {
        return a.getTotal().compareTo(b.getTotal());
    }
}
