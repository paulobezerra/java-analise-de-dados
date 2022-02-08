package br.com.analyzer.config;

import java.io.File;
import java.util.regex.Pattern;

public class Config {
    private final String homeDir;
    private final String dirIn;
    private final String dirOut;
    private final String dirLog;
    private final String customerId = "002";
    private final String salesmanId = "001";
    private final String salesId = "003";
    private final String delimiterChar = "ç";
    private final String itemDelimiterChar = ",";
    private final String itemValuesDelimiterChar = "-";
    private final String inExtension =  ".dat";
    private final String doneExtension =  ".done.dat";
    private final String errorLogExtension =  ".error.log";

    public Config() {
        this.homeDir = System.getProperty("user.home");
        this.dirIn = String.join(File.separator, homeDir, "data", "in");
        this.dirOut = String.join(File.separator, homeDir, "data", "out");
        this.dirLog = String.join(File.separator, homeDir, "data", "log");
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

    public String getDirLog() {
        return dirLog;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getSalesmanId() {
        return salesmanId;
    }

    public String getSalesId() {
        return salesId;
    }

    public String getDelimiterChar() {
        return delimiterChar;
    }

    public String getItemDelimiterChar() {
        return itemDelimiterChar;
    }

    public String getItemValuesDelimiterChar() {
        return itemValuesDelimiterChar;
    }

    public String getDoneExtension() {
        return doneExtension;
    }

    public String getInExtension() {
        return inExtension;
    }

    public String getErrorLogExtension() {
        return errorLogExtension;
    }
}
