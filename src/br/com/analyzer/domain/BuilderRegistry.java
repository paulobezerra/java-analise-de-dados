package br.com.analyzer.domain;

import br.com.analyzer.config.Config;

public class BuilderRegistry {

    private final Config config;

    public BuilderRegistry(Config config) {
        this.config = config;
    }

    public Registry build(String line) throws Exception {
        String[] chunks = line.split(config.getDelimiterChar());

        if (chunks[0].equals(config.getCustomerId())) {
            return new Customer(chunks);
        }

        if (chunks[0].equals(config.getSalesmanId())) {
            return new Salesman(chunks);
        }

        if (chunks[0].equals(config.getSalesId())) {
            return new Sale(chunks);
        }

        throw new Exception(line);
    }
}
