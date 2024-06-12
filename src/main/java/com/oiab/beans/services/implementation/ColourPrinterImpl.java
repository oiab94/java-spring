package com.oiab.beans.services.implementation;

import com.oiab.beans.services.BluePrinter;
import com.oiab.beans.services.ColourPrinter;
import com.oiab.beans.services.GreenPrinter;
import com.oiab.beans.services.RedPrinter;
import org.springframework.stereotype.Component;

@Component // Esta anotación permite que Spring puedad detectar nuestros beans y los pueda inyectar
public class ColourPrinterImpl implements ColourPrinter {
	private RedPrinter redPrinter;
	private GreenPrinter greenPrinter;
	private BluePrinter bluePrinter;

	/**
	 * Constructor
	 * Lo que hacemos ahora es inyectar las dependencias de los objetos que implementan las interfaces
	 * RedPrinter, GreenPrinter y BluePrinter
	 */
	public ColourPrinterImpl(BluePrinter bluePrinter, GreenPrinter greenPrinter, RedPrinter redPrinter) {
		this.bluePrinter  = bluePrinter;
		this.greenPrinter = greenPrinter;
		this.redPrinter  = redPrinter;
	}

	@Override
	public String print() {
		return String.join(" ", this.redPrinter.print(), this.greenPrinter.print(), this.bluePrinter.print());
	}
}
