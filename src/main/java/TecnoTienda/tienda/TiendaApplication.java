package TecnoTienda.tienda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class TiendaApplication {

	public static void main(String[] args) {
		System.out.println("Hola mundo!");
		SpringApplication.run(TiendaApplication.class, args);
	}

}
