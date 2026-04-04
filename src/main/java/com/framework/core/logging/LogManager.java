package com.framework.core.logging;

import org.apache.logging.log4j.Logger;

/**
 * LogManager — single entry point for all logging in the framework.
 *
 */



public class LogManager {
	
	 private LogManager() {}
	
	
	private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(org.apache.logging.log4j.LogManager.class);
	

 
    public static void info(String message) {
    	
        logger.info(message);
        
    }
    
 
    public static void debug(String message) {
    	
        logger.debug(message);
        
    }
    
 
    public static void warn(String message) {
    	
        logger.warn(message);
        
    }
    
 
    public static void error(String message) {
    	
        logger.error(message);
        
    }
    
 
    public static void error(String message, Throwable throwable) {
    	
        logger.error(message, throwable);
        
    }
    
 
    public static void fatal(String message) {
    	
        logger.fatal(message);
        
    }
}