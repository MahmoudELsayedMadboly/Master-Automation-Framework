package com.framework.api.base;

import com.framework.core.config.ConfigManager;
import com.framework.core.logging.LogManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public abstract class BaseApi {
	
	
	/**
	 * BaseApi — REST Assured base configuration.
	 *
	 * Sets up the RequestSpecification once with:
	 * - Base URI from api.properties
	 * - Default content type (JSON)
	 * - Request/response logging for debugging
	 *
	 * Every API service class extends this and gets these defaults for free.
	 * Tests never deal with raw RestAssured.given() setup — they just call
	 * service methods.
	 */
	
	protected static RequestSpecification requestSpec;
	
    protected static ResponseSpecification responseSpec;
    
    static {
    	
        setupSpecifications();
        
    }
 
    private static void setupSpecifications() {
    	
        String baseUri = ConfigManager.getApi("api.base.url");
        
        LogManager.info("Initializing REST Assured with base URI: " + baseUri);
        
 
        requestSpec = new RequestSpecBuilder()
            .setBaseUri(baseUri)
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON)
            .log(LogDetail.METHOD)
            .log(LogDetail.URI)
            .build();
        
 
        responseSpec = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .build();
        
 
        // Apply globally so all RestAssured calls use these specs by default
        RestAssured.requestSpecification  = requestSpec;
        
        RestAssured.responseSpecification = responseSpec;
        
    }
 
    
    
    
    /**
     * 
     * Returns a fresh RequestSpecification based on the base spec.
     * Use this in service classes to add per-request customizations
     * (e.g. auth headers, path params) without polluting the base spec.
     */
    
    protected RequestSpecification given() {
    	
        return RestAssured.given().spec(requestSpec);
        
    }
 
    
    /**
     * Returns a RequestSpecification with a Bearer auth token added.
     * Useful for endpoints that require authentication.
     *
     * @param token Bearer token value
     */
    
    protected RequestSpecification givenWithAuth(String token) {
    	
        LogManager.debug("Adding auth token to request");
        
        return given().header("Authorization", "Bearer " + token);
        
    }

}
