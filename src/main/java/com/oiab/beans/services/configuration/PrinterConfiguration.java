package com.oiab.beans.services.configuration;

import com.oiab.beans.services.BluePrinter;
import com.oiab.beans.services.ColourPrinter;
import com.oiab.beans.services.GreenPrinter;
import com.oiab.beans.services.RedPrinter;
import com.oiab.beans.services.implementation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Esta anotación indica que esta clase es una clase de configuración y que Spring debe leerla para buscar beans
public class PrinterConfiguration {
	/**
	 * Estos métodos crea un bean de  cada tipo Printer que tenga una implementación en inglésq
	 */
	@Bean
	public BluePrinter bluePrinter() { return new SpanishBluePrinterImpl(); }

	@Bean
	public GreenPrinter greenPrinter() {
		return new EnglishGreenPrinterImpl();
	}

	@Bean
	public RedPrinter redPrinter() { return new SpanishRedPrinterImpl(); }

	@Bean
	public ColourPrinter colourPrinter(BluePrinter bluePrinter, GreenPrinter greenPrinter, RedPrinter redPrinter) {
		return new ColourPrinterImpl(bluePrinter, greenPrinter, redPrinter);
	}
}
