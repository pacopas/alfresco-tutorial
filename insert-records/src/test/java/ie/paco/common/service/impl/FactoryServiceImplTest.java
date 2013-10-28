package ie.paco.common.service.impl;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import ie.paco.common.service.FactoryService;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
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

	@Test
	public void testCreatePostMethod() throws URIException {
		String uri = "http://www.google.com/";
		HttpMethod method = factoryService.createPostMethod(uri);
		assertTrue(method instanceof PostMethod);
		assertEquals(uri, method.getURI().toString());
	}
	
	@Test(expected=UnsupportedEncodingException.class)
	public void testCreateRequestEntity_WRONG_ENCODING() throws UnsupportedEncodingException {
		factoryService.createRequestEntity("content", "type", "no-way");
	}
	
	@Test
	public void testCreateRequestEntity() throws UnsupportedEncodingException {
		RequestEntity entity = factoryService.createRequestEntity("content", "type", "UTF-8");
		assertTrue(entity instanceof StringRequestEntity);
		assertEquals("content", ((StringRequestEntity) entity).getContent());
		assertEquals("type; charset=UTF-8", ((StringRequestEntity)entity).getContentType());
	}

}
