package br.com.analyzer;

import br.com.analyzer.config.Config;
import br.com.analyzer.infra.Bootstrap;

import java.io.File;

public class Application {
    public static void main(String[] args) {
        Config config = new Config();
        Bootstrap bootstrap = new Bootstrap(config);
        //Certificar que os diretórios existam
        if (!bootstrap.makeSureAllDirectoriesExist()) {
            System.out.println("Não foi possível localizar a pasta: "+config.getHomeDir());
            return;
        }



    }


        //Entrar em loop infinito
        //Ler lista de arquivos com extensão .dat no path ~/data/in
            //Interar pelos arquivos existentes no diretório
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
