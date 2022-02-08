package br.com.analyzer.core;

import br.com.analyzer.config.Config;
import br.com.analyzer.domain.Registry;
import br.com.analyzer.infra.FilesUtils;

import java.io.File;

public class Reader {
    private final Config config;
    private final FilesUtils fileUtils;

    public Reader(Config config, FilesUtils filesUtils) {
        this.config = config;
        this.fileUtils = filesUtils;
    }

    public Registry read(File f) {
        return new Registry();
    }
}
