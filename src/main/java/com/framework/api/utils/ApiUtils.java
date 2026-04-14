package com.framework.api.utils;

import com.framework.core.logging.LogManager;
import io.restassured.response.Response; 
import java.util.List;

public class ApiUtils {
	
	/**
	 * ApiUtils — helper methods for working with REST Assured responses.
	 *
	 * Keeps service classes and test classes clean — no raw JsonPath
	 * or response handling scattered across the codebase.
	 */
	
	
	private ApiUtils() {}
	
	 
    // ── Status code ────────────────────────────────────────────────────────
 
    public static int getStatusCode(Response response) {
    	
        int code = response.getStatusCode();
        
        LogManager.debug("Response status code: " + code);
        
        return code;
        
    }
    
 
    public static boolean isSuccess(Response response) {
    	
        int code = response.getStatusCode();
        
        LogManager.debug("Checking if response is success: " + code);
        
        return code >= 200 && code < 300;
        
    }
    
    
 
    public static void logResponse(Response response) {
    	
        LogManager.info("Status : " + response.getStatusCode());
        
        LogManager.debug("Body   : " + response.getBody().asPrettyString());
        
    }
 
    
    // ── Extract values ─────────────────────────────────────────────────────
 
    /**
     * Extract a single value from the response body using JsonPath.
     * Example: extractValue(response, "user.id") returns "123"
     */
    public static <T> T extractValue(Response response, String jsonPath) {
    	
        LogManager.debug("Extracting value from path: " + jsonPath);
        
        return response.jsonPath().get(jsonPath);
        
    }
 
    /**
     * Extract a string value from the response body.
     * Example: extractString(response, "message") returns "success"
     */
    public static String extractString(Response response, String jsonPath) {
    	
        LogManager.debug("Extracting string from path: " + jsonPath);
        
        return response.jsonPath().getString(jsonPath);
        
    }
 
    /**
     * Extract an integer value from the response body.
     * Example: extractInt(response, "responseCode") returns 200
     */
    public static int extractInt(Response response, String jsonPath) {
    	
        LogManager.debug("Extracting int from path: " + jsonPath);
        
        return response.jsonPath().getInt(jsonPath);
        
    }
 
    /**
     * Extract a boolean value from the response body.
     */
    public static boolean extractBoolean(Response response, String jsonPath) {
    	
        LogManager.debug("Extracting boolean from path: " + jsonPath);
        
        return response.jsonPath().getBoolean(jsonPath);
        
    }
 
    /**
     * Extract a list of values from the response body.
     * Example: extractList(response, "products.name") returns ["Blue Top", "Men Tshirt"]
     */
    public static <T> List<T> extractList(Response response, String jsonPath) {
    	
        LogManager.debug("Extracting list from path: " + jsonPath);
        
        return response.jsonPath().getList(jsonPath);
        
    }
 
    // ── Headers ────────────────────────────────────────────────────────────
 
    public static String getHeader(Response response, String headerName) {
    	
        LogManager.debug("Getting header: " + headerName);
        
        return response.getHeader(headerName);
        
    }
 
    public static String getContentType(Response response) {
    	
        return response.getContentType();
        
    }
 
    // ── Deserialize ────────────────────────────────────────────────────────
 
    /**
     * Deserialize the full response body into a POJO.
     * The POJO must have Jackson annotations or matching field names.
     * Example: deserialize(response, UserResponse.class)
     */
    public static <T> T deserialize(Response response, Class<T> clazz) {
    	
        LogManager.debug("Deserializing response to: " + clazz.getSimpleName());
        
        return response.as(clazz);
        
    }
 
    // ── Response time ──────────────────────────────────────────────────────
 
    public static long getResponseTimeMs(Response response) {
    	
        long time = response.getTime();
        
        LogManager.debug("Response time: " + time + "ms");
        
        return time;
        
    }
 
    public static boolean isResponseFasterThan(Response response, long maxMs) {
    	
        boolean result = response.getTime() < maxMs;
        
        LogManager.debug("Response faster than " + maxMs + "ms: " + result);
        
        return result;
        
    }

}
