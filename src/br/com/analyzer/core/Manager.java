package br.com.analyzer.core;

import br.com.analyzer.config.Config;
import br.com.analyzer.domain.Analysis;
import br.com.analyzer.domain.Registry;
import br.com.analyzer.infra.FilesUtils;
import br.com.analyzer.infra.Timer;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Manager {
    private final Config config;
    private final FilesUtils filesUtils;
    private final Reader reader;
    private final Analyzer analyzer;

    public Manager(Config config, FilesUtils filesUtils) {
        this.config = config;
        this.filesUtils = filesUtils;
        this.reader = new Reader(config, filesUtils);
        this.analyzer = new Analyzer();
    }

    public void process() {
        List<File> files = this.filesUtils.getAllFiles();
        if (files.size() > 0) {
            files.forEach(f -> {
                try {
                    List<Registry> registries = this.reader.read(f);
                    Analysis analysis = this.analyzer.analize(registries);
                    this.filesUtils.generateReport(analysis, f.getName());
                    this.filesUtils.generateErrorLog(f.getName(), this.reader.getErrors());
                    f.delete();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        Timer.setTimeout(() -> this.process(), 5000);
    }
}
