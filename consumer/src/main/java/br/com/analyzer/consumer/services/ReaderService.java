package br.com.analyzer.consumer.services;

import br.com.analyzer.consumer.components.BuilderRegistry;
import br.com.analyzer.consumer.domain.Analysis;
import br.com.analyzer.consumer.domain.ReadError;
import br.com.analyzer.consumer.domain.Registry;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service

public class ReaderService {

    @Autowired
    BuilderRegistry builder;

    @Autowired
    AnalyzerService analyzer;

    @Autowired
    FileService files;

    @Autowired
    ReportService report;

    @Autowired
    LogService log;

    @RabbitListener(queues = "analyzer.queue")
    public void read(byte[] message, @Header("File-name") String fileName) {
        try {
            files.makeSureAllDirectoriesExist();
            List<Registry> registries = new ArrayList<>();
            List<ReadError> errors = new ArrayList<>();
            String messageString = new String(message);
            String[] stringArray = messageString.split("\\R");

            Arrays.asList(stringArray).forEach(line -> {
                readLine(line, registries, errors);
            });

            if (registries.size() > 0) {
                Analysis analysis = analyzer.analize(registries);
                report.generate(fileName, analysis);
            }

            if (errors.size() == 0) return;
            log.generate(fileName, errors);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readLine(String line, List<Registry> registries, List<ReadError> errors) {
        try {
            registries.add(builder.build(line));
        } catch (Exception e) {
            e.printStackTrace();
            errors.add(new ReadError(line, e.getMessage()));
        }
    }

    public BuilderRegistry getBuilder() {
        return builder;
    }
}