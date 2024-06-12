package com.oiab.beans;

import com.oiab.beans.services.ColourPrinter;
import com.oiab.beans.services.implementation.ColourPrinterImpl;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
public class BeansApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BeansApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("BeansApplication...run");

		final ColourPrinter colourPrinter = new ColourPrinterImpl();
		log.info("Colour: " + colourPrinter.print());

		log.info("BeansApplication...done");
	}
}
