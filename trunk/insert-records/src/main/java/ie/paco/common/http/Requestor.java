package ie.paco.common.http;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.HttpMethod;

public interface Requestor {

	/**
	 * Perform an HTTP request
	 * @param method
	 * @return the response as a String
	 * @throws Exception
	 */
	String doRequest(HttpMethod method) throws IOException;
	
	/**
	 * Make an HTTP request with method GET
	 * @param url
	 * @param headers
	 * @return
	 * @throws Exception
	 */
	String doGet(String url, Map<String, String> headers) throws IOException;
	
	/**
	 * Make an HTTP request with method POST
	 * @param url
	 * @param headers
	 * @param params
	 * @return
	 * @throws Exception
	 */
	String doPost(String url, Map<String, String> headers, Map<String, String> params) throws IOException;

}
