package ie.paco.alfresco_portlet.http.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import ie.paco.alfresco_portlet.service.FactoryService;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RequestorImplTest {
	
	private RequestorImpl requestor;
	private HttpClient client;
	private FactoryService fs;
	private HttpMethod method;
	private String response;
	private Map<String, String> headers;
	private String url = "http://www.google.com/";

	@Before
	public void setUp() throws Exception {
		requestor = new RequestorImpl();
		client = mock(HttpClient.class);
		fs = mock(FactoryService.class);
		requestor.setClient(client);
		requestor.setFactoryService(fs);
		
		method = mock(GetMethod.class);
		response = "response";
		headers = new HashMap<String, String>();
		headers.put("field", "value");
	}

	@After
	public void tearDown() {
		
	}

	@Test
	public void testDoRequest() throws Exception{ 
		when(client.executeMethod(method)).thenReturn(HttpStatus.SC_OK);
		when(method.getResponseBodyAsString()).thenReturn(response);
		
		String result = requestor.doRequest(method);
		assertEquals(response, result);
		
		verify(method).releaseConnection();
	}

	@Test
	public void testDoGet() throws Exception {
		when(fs.createGetMethod(url)).thenReturn(method);
		when(client.executeMethod(method)).thenReturn(HttpStatus.SC_OK);
		when(method.getResponseBodyAsString()).thenReturn(response);
		
		String result = requestor.doGet(url, headers);
		assertEquals(response, result);
		
		verify(method).addRequestHeader("field", "value");
		verify(method).releaseConnection();
	}

}
