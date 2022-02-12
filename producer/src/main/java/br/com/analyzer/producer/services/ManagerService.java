package br.com.analyzer.producer.services;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

@Service
public class ManagerService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;

    @Value("${spring.rabbitmq.queue}")
    private String queue;

    @Autowired
    FilesService files;

    @Autowired
    public ManagerService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void process() {
        List<File> files = this.files.getAllFiles();

        if (files.size() == 0) return;

        files.forEach(f -> processFile(f));

    }

    private void processFile(File f) {
        try {
            byte[] fileData = Files.readAllBytes(f.toPath());
            Message message = MessageBuilder.withBody(fileData)
                    .setHeader("Content-type", "text/plain")
                    .setHeader("File-name", f.getName())
                    .build();
            this.toQueue(message);
            this.removeFiles(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeFiles(File file) {
        this.files.remove(file);
    }

    public void toQueue(Message message) {
         rabbitTemplate.send(exchange, routingkey, message);
    }
}
