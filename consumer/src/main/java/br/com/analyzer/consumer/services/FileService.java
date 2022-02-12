package br.com.analyzer.consumer.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileService {
    @Value("${spring.files.extension}")
    String extension;

    @Value("${spring.files.doneExtension}")
    String doneExtension;

    @Value("${spring.files.logExtension}")
    String logExtension;

    public Boolean makeSureAllDirectoriesExist() {
        try {
            Files.createDirectories(Paths.get(this.getDirOut()));
            Files.createDirectories(Paths.get(this.getDirLog()));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public String getDirOut() {
        String homeDir = System.getProperty("user.home");
        return String.join(File.separator, homeDir, "data", "out");
    }

    public String getDirLog() {
        String homeDir = System.getProperty("user.home");
        return String.join(File.separator, homeDir, "data", "log");
    }
}
