package org.irods.http;

import java.net.http.HttpClient;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ExecutionContext {
	
	HttpClient httpClient;
	ObjectMapper objectMapper;
	
	String httpApiURL;
	
	public static ExecutionContext createDefault(String httpApiURL) {
		var ctx = new ExecutionContext();

		ctx.httpClient = HttpClient.newHttpClient();
		ctx.objectMapper = new ObjectMapper();
		ctx.httpApiURL = httpApiURL;

		return ctx;
	}

}
