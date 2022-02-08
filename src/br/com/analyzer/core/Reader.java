package br.com.analyzer.core;

import br.com.analyzer.config.Config;
import br.com.analyzer.domain.Registry;
import br.com.analyzer.infra.FilesUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    private final Config config;
    private final FilesUtils fileUtils;

    public Reader(Config config, FilesUtils filesUtils) {
        this.config = config;
        this.fileUtils = filesUtils;
    }

    public List<Registry> read(File f) {
        return new ArrayList<>();
    }
}
