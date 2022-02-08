package br.com.analyzer.config;

import java.io.File;
import java.util.regex.Pattern;

public class Config {
    private final String homeDir;
    private final String dirIn;
    private final String dirOut;
    private final Pattern customerPattern = Pattern.compile("/[0-9]*\\ç[0-9]*\\ç[\\wau]*\\ç[0-9]*/u");
    private final Pattern salesManPattern = Pattern.compile("/[0-9]*\\ç[0-9]*\\ç[\\wau\\ ]*\\ç[\\wau]*/u");
    private final Pattern salesPattern = Pattern.compile("/[0-9]*\\ç[0-9]*\\ç\\[([0-9]*-[0-9]*[.]?([0-9]*)?-[0-9]*[.]?([0-9]*)?\\,?)*\\]\\ç[\\wau]*/ug");

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
