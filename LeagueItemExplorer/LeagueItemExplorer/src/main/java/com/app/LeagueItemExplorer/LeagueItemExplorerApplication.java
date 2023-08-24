package com.app.LeagueItemExplorer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//Uso este decorador para que spring encuentre todas las clases del JAR y me permita usar @Autowired
@ComponentScan(basePackages = "com.app.LeagueItemExplorerJAR")
//Tuve que incluirlo porque no me reconocia el RestController en el Swagger(Haciendo que la clase SwaggerConfiguration no se lea)
@ComponentScan(basePackages = "com.app.LeagueItemExplorer")
public class LeagueItemExplorerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeagueItemExplorerApplication.class, args);
	}

}
