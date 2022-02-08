package br.com.analyzer.infra;

import br.com.analyzer.config.Config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilesUtils {
    private final Config config;

    public FilesUtils(Config config) {
        this.config = config;
    }

    public Boolean makeSureAllDirectoriesExist() {
        try {
            Files.createDirectories(Paths.get(this.config.getDirIn()));
            Files.createDirectories(Paths.get(this.config.getDirOut()));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public List<File> getAllFiles() {
        try {
            return Files.list(Paths.get(this.config.getDirIn()))
                    .filter(path -> path.toString().endsWith(".dat"))
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
