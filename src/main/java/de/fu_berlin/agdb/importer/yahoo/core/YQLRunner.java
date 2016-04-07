package de.fu_berlin.agdb.importer.yahoo.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

public class YQLRunner {
	
	private static final String BASE_URL = "http://query.yahooapis.com/v1/public/yql";
	private static final String QUERY_PARAMETER = "?q=";
	private static final String OUTPUT_FORMAT = "&format=json";
	
	private HttpClient httpClient;
	
	public YQLRunner() {
		httpClient = HttpClientBuilder.create().build();
	}
	
	public JSONObject getYQLResponseJSON(String query) throws IOException{
		JSONObject jsonResponse = new JSONObject(getYQLResponseString(query));
		return jsonResponse.getJSONObject("query").getJSONObject("results");
	}
	
	public String getYQLResponseString(String query) throws IOException{
		HttpGet getRequest = new HttpGet(prepareURL(query));
		getRequest.addHeader("accept", "application/json");
		
		HttpResponse response = httpClient.execute(getRequest);
		
		String output = "";
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String tmp = null;
		while ((tmp= bufferedReader.readLine()) != null) {
			output += tmp;
		}
		return output;
	}
	
	private String prepareURL(String query) throws UnsupportedEncodingException{
		return BASE_URL + QUERY_PARAMETER + URLEncoder.encode(query, "UTF-8") + OUTPUT_FORMAT;
	}
}
