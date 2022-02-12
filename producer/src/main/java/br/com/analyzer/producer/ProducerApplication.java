package br.com.analyzer.producer;

import br.com.analyzer.producer.services.FilesService;
import br.com.analyzer.producer.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class ProducerApplication {
	@Autowired
	ManagerService manager;

	@Autowired
	FilesService filesService;

	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}

	@Scheduled(fixedRate = 1000)
    public void greeting() {
		filesService.makeSureAllDirectoriesExist();
		manager.process();
    }
}
