package br.com.analyzer;

import br.com.analyzer.config.Config;
import br.com.analyzer.infra.FilesUtils;
import br.com.analyzer.core.Manager;

public class Application {
    public static void main(String[] args) {
        Config config = new Config();
        FilesUtils filesUtils = new FilesUtils(config);
        Manager manager = new Manager(config, filesUtils);

        //Certificar que os diretórios existam
        if (!filesUtils.makeSureAllDirectoriesExist()) {
            System.out.println("Não foi possível localizar a pasta: "+config.getHomeDir());
            return;
        }

        manager.process();
    }



                //Interar por cada linha do arquivo
                    //Identificar o padrão de cada linha (vendedor, cliente, vendas)
                    //Instanciar objeto de acordo com padrão identificado
                //Calcular quantidade de clientes
                //Calcular quantidade de vendedores
                //Identificar venda mais cara
                //Totalidar vendas por vendedor
                //Identificar pior vendedor
                //Gerar arquivo de saída
}
