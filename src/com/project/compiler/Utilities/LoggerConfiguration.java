package com.project.compiler.Utilities;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.project.compiler.Config.ApplicationStatic;

/**
 * Logger
 * 
 * @author SajjadAshrafCan
 *
 */
public class LoggerConfiguration {
	private FileHandler fileHandler;
	private  ConsoleHandler consoleHandler;
	private SimpleFormatter loggerFormatter ;

	/**
	 * Constructor
	 * 
	 * @param LOGGER
	 */
	public LoggerConfiguration() {
		
	}
	
	public Logger configuredLogger(Logger LOGGER)
	{
		return configuredLogger(LOGGER, ApplicationStatic.LOG_PATH);
		
	}
	public Logger configuredLogger(Logger LOGGER, String logPath)
	{
		try {

			LOGGER.setLevel(Level.INFO);
			LOGGER.setUseParentHandlers(false);
			fileHandler = new FileHandler(logPath, true);

			loggerFormatter = new SimpleFormatter();
			fileHandler.setFormatter(loggerFormatter);
			
			consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.FINEST);
            consoleHandler.setFormatter(loggerFormatter);

			LOGGER.addHandler(fileHandler);
			LOGGER.addHandler(consoleHandler);

			Handler[] handlers = LOGGER.getHandlers();
			if (handlers[0] instanceof ConsoleHandler) {
				handlers[0].flush();
				handlers[0].close();
				LOGGER.removeHandler(handlers[0]);				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return LOGGER;
	}

}
