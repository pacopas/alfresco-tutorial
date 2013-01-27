package ie.paco.alfresco_portlet.http.impl;

import java.util.Map;

import org.apache.commons.httpclient.HttpMethod;

import ie.paco.alfresco_portlet.http.Requestor;

public class DummyRequestor implements Requestor {

	public String doRequest(HttpMethod method) throws Exception {
		return "{\"data\" : [ { \"shortName\" : \"DUMMY_GROUP\" } ] }";
	}

	public String doGet(String url, Map<String, String> headers)
			throws Exception {
		return this.doRequest(null);
	}

}
