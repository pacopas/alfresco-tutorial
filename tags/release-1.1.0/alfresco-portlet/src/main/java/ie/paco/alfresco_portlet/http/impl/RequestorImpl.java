package ie.paco.alfresco_portlet.http.impl;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ie.paco.alfresco_portlet.http.Requestor;
import ie.paco.alfresco_portlet.service.FactoryService;

public class RequestorImpl implements Requestor {

	private static final Log LOGGER = LogFactory.getLog(RequestorImpl.class);

	private HttpClient client;
	private FactoryService factoryService;

	public String doRequest(HttpMethod method) throws Exception {
		try {
			int statusCode = client.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				LOGGER.error("Method failed: " + method.getStatusLine());
			}
			return method.getResponseBodyAsString();
		} catch (HttpException e) {
			LOGGER.error("Fatal protocol violation:", e);
			throw e;
		} catch (IOException e) {
			LOGGER.error("Fatal transport error:", e);
			throw e;
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
	}

	public String doGet(String url, Map<String, String> headers)
			throws Exception {
		HttpMethod method = factoryService.createGetMethod(url);
		for (String key : headers.keySet()) {
			method.addRequestHeader(key, headers.get(key));
		}
		return this.doRequest(method);
	}

	public void setClient(HttpClient client) {
		this.client = client;
	}

	public void setFactoryService(FactoryService factoryService) {
		this.factoryService = factoryService;
	}

}
