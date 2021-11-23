package com.revature.drivers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LoggingDriver {
	
	private static Logger log = LogManager.getRootLogger();

	public static void main(String[] args) {
		log.trace("This is a trace!");
		log.debug("This is a debug!");
		log.info("This is an info!");
		log.warn("This is a warning!");
		log.error("This is an error!");
		log.fatal("This is fatal!");
	}

}
