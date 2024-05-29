package com.rickandmorty.forum;

import com.rickandmorty.forum.services.EpisodeService;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication()
@RestController()
@EnableScheduling
public class RickAndMortyApplication {

	public static void main(String[] args) {
		SpringApplication.run(RickAndMortyApplication.class, args);
	}

}