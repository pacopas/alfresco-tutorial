package ie.paco.common.http.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import ie.paco.common.service.FactoryService;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RequestorImplTest {
	
	private RequestorImpl requestor;
	private HttpClient client;
	private FactoryService fs;
	private GetMethod getMethod;
	private PostMethod postMethod;
	private String response;
	private Map<String, String> headers;
	private Map<String, String> params;
	private String url = "http://www.google.com/";
	private String content;
	private String contentType = "application/json";
	private String encoding = "UTF-8";
	private RequestEntity requestEntity;

	@Before
	public void setUp() throws Exception {
		requestor = new RequestorImpl();
		client = mock(HttpClient.class);
		fs = mock(FactoryService.class);
		requestor.setClient(client);
		requestor.setFactoryService(fs);
		
		getMethod = mock(GetMethod.class);
		postMethod = mock(PostMethod.class);
		response = "response";
		headers = new HashMap<String, String>();
		headers.put("headerKey", "headerValue");
		params = new HashMap<String, String>();
		params.put("paramName", "paramValue");
		content = "{\"paramName\" : \"paramValue\"}";
		requestEntity = new StringRequestEntity(content, contentType, encoding);
	}

	@After
	public void tearDown() {
		
	}

	@Test
	public void testDoRequest() throws Exception{ 
		when(client.executeMethod(getMethod)).thenReturn(HttpStatus.SC_OK);
		when(getMethod.getResponseBodyAsString()).thenReturn(response);
		
		String result = requestor.doRequest(getMethod);
		assertEquals(response, result);
		
		verify(getMethod).releaseConnection();
	}

	@Test
	public void testDoGet() throws Exception {
		when(fs.createGetMethod(url)).thenReturn(getMethod);
		when(client.executeMethod(getMethod)).thenReturn(HttpStatus.SC_OK);
		when(getMethod.getResponseBodyAsString()).thenReturn(response);
		
		String result = requestor.doGet(url, headers);
		assertEquals(response, result);
		
		verify(getMethod).addRequestHeader("headerKey", "headerValue");
		verify(getMethod).releaseConnection();
	}

	@Test
	public void testDoPost() throws Exception {
		when(fs.createPostMethod(url)).thenReturn(postMethod);
		when(fs.createRequestEntity(content, contentType, encoding)).thenReturn(requestEntity);
		when(client.executeMethod(postMethod)).thenReturn(HttpStatus.SC_OK);
		when(postMethod.getResponseBodyAsString()).thenReturn(response);
		
		String result = requestor.doPost(url, headers, params);
		assertEquals(response, result);
		
		verify(postMethod).addRequestHeader("headerKey", "headerValue");
		verify(postMethod).setRequestEntity(requestEntity);
		verify(postMethod).releaseConnection();
	}

}
