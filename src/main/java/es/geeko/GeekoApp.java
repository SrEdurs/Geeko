package es.geeko;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeekoApp {
//CUIDADO!!!! SE BORRAN LOS DATOS DE LA BBDD SI SE EJECUTA
	public static void main(String[] args) {
		SpringApplication.run(GeekoApp.class, args);
	}

}
