package ie.paco.common.service;

import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

public interface FactoryService {
	
	public GetMethod createGetMethod(String uri);
	public PostMethod createPostMethod(String uri);
	public RequestEntity createRequestEntity(String content, String contentType, String encoding)
			throws UnsupportedEncodingException;

}
