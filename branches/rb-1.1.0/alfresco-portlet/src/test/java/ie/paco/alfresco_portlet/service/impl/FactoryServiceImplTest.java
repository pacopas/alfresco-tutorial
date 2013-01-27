package ie.paco.alfresco_portlet.service.impl;

import static org.junit.Assert.*;
import ie.paco.alfresco_portlet.service.FactoryService;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Test;

public class FactoryServiceImplTest {
	
	private FactoryService factoryService = new FactoryServiceImpl();

	@Test
	public void testCreateGetMethod() throws URIException {
		String uri = "http://www.google.com/";
		HttpMethod method = factoryService.createGetMethod(uri);
		assertTrue(method instanceof GetMethod);
		assertEquals(uri, method.getURI().toString());
	}

}
