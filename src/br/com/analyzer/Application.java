package br.com.analyzer;

import br.com.analyzer.config.Config;
import br.com.analyzer.infra.FilesUtils;
import br.com.analyzer.core.Manager;

public class Application {
    public static void main(String[] args) {
        Config config = new Config();
        FilesUtils filesUtils = new FilesUtils(config);
        Manager manager = new Manager(config, filesUtils);

        if (!filesUtils.makeSureAllDirectoriesExist()) {
            System.out.println("Não foi possível localizar a pasta: "+config.getHomeDir());
            return;
        }

        manager.process();
    }
}
