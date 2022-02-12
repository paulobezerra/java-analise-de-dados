package br.com.analyzer.consumer.domain;

public class ReadError {
    private String line;
    private String message;

    public ReadError(String line, String message) {
        this.line = line;
        this.message = message;
    }

    public String getLine() {
        return line;
    }

    public String getMessage() {
        return message;
    }
}
