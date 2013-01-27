package ie.paco.alfresco_portlet.service.impl;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import ie.paco.alfresco_portlet.service.FactoryService;

public class FactoryServiceImpl implements FactoryService {

	public HttpMethod createGetMethod(String uri) {
		return new GetMethod(uri);
	}

}
