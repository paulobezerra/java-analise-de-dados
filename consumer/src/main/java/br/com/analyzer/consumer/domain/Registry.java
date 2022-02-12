package br.com.analyzer.consumer.domain;

public abstract class Registry {
    private String type;

    public Registry(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
