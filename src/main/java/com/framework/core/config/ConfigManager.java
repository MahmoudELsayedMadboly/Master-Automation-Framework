package com.framework.core.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
* ConfigManager — reads .properties files using portable relative paths.
*
* Key design decisions:
* 1. Uses System.getProperty("user.dir") — works on any machine, any OS.
*    No hardcoded paths like "/Users/mahmoud/..." that break on other machines.
* 2. Loads both config.properties and api.properties once at startup.
* 3. System properties override file values — allows CLI override:
*    mvn test -Dbrowser=firefox overrides browser=chrome in config.properties
*/

public class ConfigManager {
	
	private static final String CONFIG_PATH = "/src/test/resources/config/config.properties";
    private static final String API_PATH    = "/src/test/resources/config/api.properties";
 
    private static Properties configProperties;
    private static Properties apiProperties;
 
    private ConfigManager() {}
    
    
    
 // ── UI config ──────────────────────────────────────────────────────────
    /**
     * Returns a value from config.properties.
     * System property takes precedence — useful for CI/CD overrides.
     */
    
    public static String getString(String key) {
    	
        String systemValue = System.getProperty(key);
        
        if (systemValue != null && !systemValue.isBlank()) {
        	
            return systemValue;
        }
        
        return getConfigProperties().getProperty(key);
        
    }
    
    
    //Returns a value from config.properties as an integer.
     
    public static int getInt(String key) {
    	
        return Integer.parseInt(getString(key));
        
    }
    
    
    
    
    //  Returns a value from config.properties as a boolean.
     
    public static boolean getBoolean(String key) {
    	
        return Boolean.parseBoolean(getString(key));
        
    }
	
    
    
    
    
    
    
    
 // ── API config ─────────────────────────────────────────────────────────
    
    
    //Returns a value from api.properties.
    
    public static String getApi(String key) {
    	
    	String systemValue = System.getProperty(key);
    	
    	if(systemValue != null && !systemValue.isBlank()) {
    		
    		return systemValue;
    		
    	}
    	
    	 return getApiProperties().getProperty(key);
    	
    }
    
    
    
    
    
    
    // ── Private loaders ────────────────────────────────────────────────────
    
    
    //this method will load UI properties from the UI file
    private static Properties getConfigProperties() {
    	
        if (configProperties == null) {
        	
            configProperties = loadFile(CONFIG_PATH);
            
        }
        
        return configProperties;
        
    }
    
    
    
  //this method will load api properties from the api file
    private static Properties getApiProperties() {
    	
        if (apiProperties == null) {
        	
            apiProperties = loadFile(API_PATH);
            
        }
        
        return apiProperties;
        
    }

    
    
    private static Properties loadFile(String relativePath) {
    	
    	
    	//store the file pass
        String fullPath = System.getProperty("user.dir") + relativePath;
        
        //create the propertise object (empty container)
        Properties props = new Properties();
        
        try (
        		
        		//open the file
        		FileInputStream fis = new FileInputStream(fullPath)
        		
        		) 
        
        {
        	
        	//load file content into the propertise object
            props.load(fis);
            
        } catch (IOException e) {
        	
            throw new RuntimeException("Failed to load properties file: " + fullPath, e);
            
        }
        
        //return the propertise object 
        return props;
        
    }
}














































