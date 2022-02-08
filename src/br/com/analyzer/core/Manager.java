package br.com.analyzer.core;

import br.com.analyzer.config.Config;
import br.com.analyzer.domain.Analysis;
import br.com.analyzer.infra.FilesUtils;
import br.com.analyzer.infra.Timer;

import java.io.File;
import java.util.List;

public class Manager {
    private final Config config;
    private final FilesUtils filesUtils;
    private final Reader reader;
    private final Recorder recorder;
    private final Analyzer analyzer;

    public Manager(Config config, FilesUtils filesUtils) {
        this.config = config;
        this.filesUtils = filesUtils;
        this.reader = new Reader(config, filesUtils);
        this.recorder = new Recorder();
        this.analyzer = new Analyzer();
    }

    public void process() {
        List<File> files = this.filesUtils.getAllFiles();
        if (files.size() > 0) {
            files.forEach(f -> {
                this.recorder.addAll(this.reader.read(f));
                Analysis analysis = this.analyzer.analize(this.recorder);
                this.filesUtils.generateReport(analysis);
                this.filesUtils.removeFile(f);
            });
        }
        Timer.setTimeout(() -> this.process(), 5000);
    }
}
