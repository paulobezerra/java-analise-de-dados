package br.com.analyzer.core;

import br.com.analyzer.config.Config;
import br.com.analyzer.domain.BuilderRegistry;
import br.com.analyzer.domain.Registry;
import br.com.analyzer.infra.FilesUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Reader {
    private final Config config;
    private final FilesUtils fileUtils;

    public Reader(Config config, FilesUtils filesUtils) {
        this.config = config;
        this.fileUtils = filesUtils;
    }

    public List<Registry> read(File file) {
        List<Registry> registries = new ArrayList<>();
        this.fileUtils.readFile(file.getName()).forEach(
                line -> {
                    registries.add(BuilderRegistry.build(line));
                }
        );
        return registries;
    }
}
