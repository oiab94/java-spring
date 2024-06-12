package com.oiab.beans.services.implementation;

import com.oiab.beans.services.BluePrinter;
import org.springframework.stereotype.Component;

@Component
public class EnglishBluePrinterImpl implements BluePrinter {
	@Override
	public String print() {
		return "Blue";
	}
}
