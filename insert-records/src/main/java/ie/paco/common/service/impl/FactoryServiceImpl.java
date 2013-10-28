package ie.paco.common.service.impl;

import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import ie.paco.common.service.FactoryService;

public class FactoryServiceImpl implements FactoryService {

	public GetMethod createGetMethod(String uri) {
		return new GetMethod(uri);
	}

	public PostMethod createPostMethod(String uri) {
		return new PostMethod(uri);
	}

	public RequestEntity createRequestEntity(String content,
			String contentType, String encoding) throws UnsupportedEncodingException {
		return new StringRequestEntity(content, contentType, encoding);
	}

}
