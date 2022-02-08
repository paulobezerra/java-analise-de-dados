package br.com.analyzer.core;

import br.com.analyzer.config.Config;
import br.com.analyzer.domain.BuilderRegistry;
import br.com.analyzer.domain.ReadError;
import br.com.analyzer.domain.Registry;
import br.com.analyzer.infra.FilesUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    private final Config config;
    private final FilesUtils fileUtils;
    private List<ReadError> errors;

    public Reader(Config config, FilesUtils filesUtils) {
        this.config = config;
        this.fileUtils = filesUtils;
        this.errors = new ArrayList<>();
    }

    public List<Registry> read(File file) {
        BuilderRegistry builder = new BuilderRegistry(this.config);
        List<Registry> registries = new ArrayList<>();
        this.fileUtils
                .readFile(file.getAbsolutePath())
                .forEach(line -> {
                            try {
                                registries.add(builder.build(line));
                            } catch (Exception e) {
                                this.errors.add(new ReadError(line, e.getMessage()));
                            }
                        });
        return registries;
    }

    public List<ReadError> getErrors() {
        return errors;
    }

    public void clearErrors() {
        this.errors = new ArrayList<>();
    }
}
