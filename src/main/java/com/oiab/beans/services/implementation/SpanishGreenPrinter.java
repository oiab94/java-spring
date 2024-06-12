package com.oiab.beans.services.implementation;

import com.oiab.beans.services.GreenPrinter;
import org.springframework.stereotype.Component;

@Component
public class SpanishGreenPrinter implements GreenPrinter {
	@Override
	public String print() {
		return "Verde";
	}
}
