package br.com.analyzer.config;

import java.io.File;

public class Config {
    private final String homeDir;
    private final String dirIn;
    private final String dirOut;

    public Config() {
        this.homeDir = System.getProperty("user.home");
        this.dirIn = String.join(File.separator, homeDir, "data", "in");
        this.dirOut = String.join(File.separator, homeDir, "data", "out");
    }

    public String getHomeDir() {
        return homeDir;
    }

    public String getDirIn() {
        return dirIn;
    }

    public String getDirOut() {
        return dirOut;
    }
}
