package br.com.analyzer.consumer.domain;

import java.math.BigDecimal;

public class Salesman extends Registry{
    private String cpf;
    private String name;
    private BigDecimal salary;
    private BigDecimal salesTotal;

    public Salesman(String[] chunks) {
        super(chunks[0]);
        this.cpf = chunks[1];
        this.name = chunks[2];
        this.salary = new BigDecimal(chunks[3]);
    }

    public void setSalesTotal(BigDecimal salesTotal) {
        this.salesTotal = salesTotal;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public BigDecimal getSalesTotal() {
        return salesTotal;
    }

    public static int compareByTotal(Salesman a, Salesman b) {
        return a.salesTotal.compareTo(b.salesTotal);
    }
}
