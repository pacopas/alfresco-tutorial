package ie.paco.common.http.impl;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ie.paco.common.http.Requestor;
import ie.paco.common.service.FactoryService;

public class RequestorImpl implements Requestor {

	private static final Log LOGGER = LogFactory.getLog(RequestorImpl.class);

	private HttpClient client;
	private FactoryService factoryService;

	public String doRequest(HttpMethod method) throws IOException {
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
			throws IOException {
		HttpMethod method = factoryService.createGetMethod(url);
		for (String key : headers.keySet()) {
			method.addRequestHeader(key, headers.get(key));
		}
		return this.doRequest(method);
	}

	public String doPost(String url, Map<String, String> headers, Map<String, String> params)
			throws IOException {
		PostMethod method = factoryService.createPostMethod(url);
		for (Map.Entry<String, String> entry : headers.entrySet()) {
			method.addRequestHeader(entry.getKey(), entry.getValue());
		}
		StringBuilder contentBuilder = new StringBuilder("{");
		for (Map.Entry<String, String> entry : params.entrySet()) {
			if (contentBuilder.length() > 1) {
				contentBuilder.append(", ");
			}
			contentBuilder.append("\"").append(entry.getKey()).append("\" : \"").append(entry.getValue()).append("\"");
		}
		contentBuilder.append("}");
		String content = contentBuilder.toString();
		LOGGER.info("Content: " + content);
		RequestEntity requestEntity = factoryService.createRequestEntity(content, "application/json", "UTF-8");
		method.setRequestEntity(requestEntity);
		return this.doRequest(method);
	}

	public void setClient(HttpClient client) {
		this.client = client;
	}

	public void setFactoryService(FactoryService factoryService) {
		this.factoryService = factoryService;
	}

}
