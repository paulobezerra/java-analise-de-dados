package br.com.analyzer.infra;

import br.com.analyzer.config.Config;
import br.com.analyzer.domain.Analysis;
import br.com.analyzer.domain.ReadError;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilesUtils {
    private final Config config;

    public FilesUtils(Config config) {
        this.config = config;
    }

    public Boolean makeSureAllDirectoriesExist() {
        try {
            Files.createDirectories(Paths.get(this.config.getDirIn()));
            Files.createDirectories(Paths.get(this.config.getDirOut()));
            Files.createDirectories(Paths.get(this.config.getDirLog()));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public List<File> getAllFiles() {
        try {
            return Files.list(Paths.get(this.config.getDirIn()))
                    .filter(path -> path.toString().endsWith(this.config.getInExtension()))
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void generateReport(Analysis analysis, String filename) throws IOException {
        String datetime = getDateTime();

        String outFileName = String.join(
                File.separator,
                config.getDirOut(),
                filename.replace(config.getInExtension(), "") + "_" + datetime + config.getDoneExtension()
        );
        BufferedWriter writer = new BufferedWriter(new FileWriter(outFileName));
        writer.write("Quantidade de clientes no arquivo de entrada: " + analysis.getQuantityCustomer());
        writer.newLine();
        writer.write("Quantidade de vendedores no arquivo de entrada: " + analysis.getQuantitySalesman());
        writer.newLine();
        writer.write("ID da venda mais cara: " + analysis.getSaleMoreExpensive().getSalesId());
        writer.newLine();
        writer.write("Pior vendedor: " + analysis.getWorstSeller().getName());
        writer.close();
        System.out.println("Relatório gerado: " + outFileName);
    }

    private String getDateTime() {
        return String.valueOf((new Date()).getTime());
    }

    public List<String> readFile(String fileName) {
        List<String> lines = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(l -> lines.add(l));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public void generateErrorLog(String filename, List<ReadError> errors) throws IOException {
        if (errors.size() == 0) return;

        String datetime = getDateTime();

        String outFileName = String.join(
                File.separator,
                config.getDirLog(),
                filename.replace(config.getInExtension(), "") + "_" + datetime + config.getErrorLogExtension()
        );
        BufferedWriter writer = new BufferedWriter(new FileWriter(outFileName));

        errors.forEach(e -> {
            try {
                writer.write(e.getLine() + ": "+e.getMessage());
                writer.newLine();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        writer.close();
        System.out.println("Algumas linhas não poderam ser processadas: " + outFileName);
    }
}

