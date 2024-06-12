package com.oiab.beans.services.implementation;

import com.oiab.beans.services.BluePrinter;
import com.oiab.beans.services.ColourPrinter;
import com.oiab.beans.services.GreenPrinter;
import com.oiab.beans.services.RedPrinter;

public class ColourPrinterImpl implements ColourPrinter {
	private RedPrinter redPrinter;
	private GreenPrinter greenPrinter;
	private BluePrinter bluePrinter;

	/**
	 * Constructor
	 * El problema que se oresenta aqui es que si queremos cambiar el idioma de la impresion
	 * tendriamos que cambiar el codigo de esta clase, lo cual no es una buena practica.
	 */
	public ColourPrinterImpl() {
		this.bluePrinter  = new EnglishBluePrinterImpl();
		this.greenPrinter = new EnglishGreenPrinterImpl();
		this.redPrinter  = new EnglishRedPrinterImpl();
	}

	@Override
	public String print() {
		return String.join(" ", this.redPrinter.print(), this.greenPrinter.print(), this.bluePrinter.print());
	}
}
