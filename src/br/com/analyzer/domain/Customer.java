package br.com.analyzer.domain;

public class Customer extends Registry{
    private String cnpj;
    private String name;
    private String businessArea;

    public Customer(String[] chunks) {
        super(chunks[0]);
        this.cnpj = chunks[1];
        this.name = chunks[2];
        this.businessArea = chunks[3];
    }
}
