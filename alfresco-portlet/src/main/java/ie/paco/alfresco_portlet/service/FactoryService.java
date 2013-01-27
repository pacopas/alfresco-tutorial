package ie.paco.alfresco_portlet.service;

import org.apache.commons.httpclient.HttpMethod;

public interface FactoryService {
	
	HttpMethod createGetMethod(String uri);

}
