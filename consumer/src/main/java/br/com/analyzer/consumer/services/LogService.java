package br.com.analyzer.consumer.services;

import br.com.analyzer.consumer.domain.ReadError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class LogService {
    @Value("${spring.files.extension}")
    String extension;

    @Value("${spring.files.doneExtension}")
    String doneExtension;

    @Value("${spring.files.logExtension}")
    String logExtension;

    @Autowired
    FileService files;

    public void generate(String filename, List<ReadError> errors) throws IOException {
        if (errors.size() == 0) return;

        String datetime = String.valueOf((new Date()).getTime());

        String outFileName = String.join(
                File.separator,
                files.getDirLog(),
                filename.replace(extension, "") + "_" + datetime + logExtension
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
