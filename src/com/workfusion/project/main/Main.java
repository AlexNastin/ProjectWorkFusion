package com.workfusion.project.main;

import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;

import com.workfusion.project.stream.StreamValue;

public class Main {
	static {
		new DOMConfigurator().doConfigure("log4j.xml",
				LogManager.getLoggerRepository());
	}

	public static void main(String[] args) {

		Thread thread = new Thread(new StreamValue());
		thread.start();

	}
}
