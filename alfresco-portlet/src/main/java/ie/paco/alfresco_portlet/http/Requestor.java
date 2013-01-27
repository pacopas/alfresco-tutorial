package ie.paco.alfresco_portlet.http;

import java.util.Map;

import org.apache.commons.httpclient.HttpMethod;

public interface Requestor {

	/**
	 * Perform an HTTP request
	 * @param method
	 * @return the response as a String
	 * @throws Exception
	 */
	String doRequest(HttpMethod method) throws Exception;
	
	/**
	 * Make an HTTP request with method GET
	 * @param url
	 * @param headers
	 * @return
	 * @throws Exception
	 */
	String doGet(String url, Map<String, String> headers) throws Exception;

}
