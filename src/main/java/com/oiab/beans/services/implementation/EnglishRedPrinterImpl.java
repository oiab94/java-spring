package com.oiab.beans.services.implementation;

import com.oiab.beans.services.RedPrinter;
import org.springframework.stereotype.Component;

@Component
public class EnglishRedPrinterImpl implements RedPrinter {
	@Override
	public String print() {
		return "Red";
	}
}
