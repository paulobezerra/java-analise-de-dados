package br.com.analyzer.consumer.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sale extends Registry{
    private Integer salesId;
    private List<SalesItem> itens;
    private String salesman;

    public Sale() {
        super("000");
        this.itens = new ArrayList<>();
    }

    public Sale(String[] chunks, String itemsDelimiterChar, String valuesDelimiterChar) {
        super(chunks[0]);
        this.salesId = Integer.parseInt(chunks[1]);
        this.salesman = chunks[3];
        this.itens = new ArrayList<>();
        String[] itens = chunks[2]
                .replace("[", "")
                .replace("]", "")
                .split(itemsDelimiterChar);
        Arrays.asList(itens).forEach(itemString -> {
            String[] itemChunks = itemString.split(valuesDelimiterChar);
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
