package br.com.analyzer.infra;

import br.com.analyzer.config.Config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Bootstrap {
    private final Config config;

    public Bootstrap(Config config) {
        this.config = config;
    }

    public Boolean makeSureAllDirectoriesExist() {
        try {
            Files.createDirectories(Paths.get(this.config.getDirIn()));
            Files.createDirectories(Paths.get(this.config.getDirOut()));
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
