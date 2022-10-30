package br.com.szella.gerenciadordecontas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class GerenciadorDeContasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorDeContasApplication.class, args);
	}

}
