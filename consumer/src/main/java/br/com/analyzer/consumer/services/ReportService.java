package br.com.analyzer.consumer.services;

import br.com.analyzer.consumer.domain.Analysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

@Service
public class ReportService {
    @Value("${spring.files.extension}")
    String extension;

    @Value("${spring.files.doneExtension}")
    String doneExtension;

    @Autowired
    FileService files;


    public void generate(String filename, Analysis analysis) throws IOException {
        String datetime = String.valueOf((new Date()).getTime());;

        String outFileName = String.join(
                File.separator, files.getDirOut(),
                filename.replace(extension, "") + "_" + datetime + doneExtension
        );
        BufferedWriter writer = new BufferedWriter(new FileWriter(outFileName));
        writer.write("Quantidade de clientes no arquivo de entrada: " + analysis.getQuantityCustomer());
        writer.newLine();
        writer.write("Quantidade de vendedores no arquivo de entrada: " + analysis.getQuantitySalesman());
        writer.newLine();
        writer.write("ID da venda mais cara: " + analysis.getSaleMoreExpensive().getSalesId());
        writer.newLine();
        writer.write("Pior vendedor: " + analysis.getWorstSeller().getName());
        writer.close();
        System.out.println("Relatório gerado: " + outFileName);
    }
}
