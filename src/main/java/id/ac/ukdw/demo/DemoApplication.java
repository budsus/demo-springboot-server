package id.ac.ukdw.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
@EnableJpaAuditing
@EnableAutoConfiguration
@ComponentScan({"id.ac.ukdw.aop", "id.ac.ukdw.demo.dao", 
				"id.ac.ukdw.auditor", "id.ac.ukdw.demo.controller", 
				"id.ac.ukdw.demo.service", "id.ac.ukdw.demo.model"})
@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		/*ApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
		for(String name:applicationContext.getBeanDefinitionNames()) {
			System.out.println(name);
		}*/
	}
}
