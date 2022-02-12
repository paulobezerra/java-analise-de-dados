package br.com.analyzer.producer.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilesService {

    @Value("${spring.files.extension}")
    private String inExtension;

    public Boolean makeSureAllDirectoriesExist() {
        try {
            Files.createDirectories(Paths.get(getDirIn()));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public List<File> getAllFiles() {
        try {
            return Files.list(Paths.get(getDirIn()))
                    .filter(path -> path.toString().endsWith(inExtension))
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void remove(File file) {
        file.delete();
    }

    private String getDirIn() {
        String homeDir = System.getProperty("user.home");
        return String.join(File.separator, homeDir, "data", "in");
    }
}

