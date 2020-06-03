package com.youzelle.beatbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BeatboxApplication {

	public static void main(String[] args) {
		Gui gui = new Gui();
		gui.build();
		SpringApplication.run(BeatboxApplication.class, args);
	}

}
