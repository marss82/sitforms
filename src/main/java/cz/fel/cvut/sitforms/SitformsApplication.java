package cz.fel.cvut.sitforms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SitformsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SitformsApplication.class, args);
	}

}
