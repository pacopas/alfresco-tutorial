package ie.paco.ir.impl;

import java.util.Map;

import ie.paco.common.http.Requestor;
import ie.paco.ir.RecordWriter;

public abstract class AbstractRecordWriter implements RecordWriter {
	
	private Requestor requestor;
	private String uri;
	private Map<String, String> header;

	public Requestor getRequestor() {
		return requestor;
	}

	public String getUri() {
		return uri;
	}

	public void setRequestor(Requestor requestor) {
		this.requestor = requestor;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Map<String, String> getHeader() {
		return header;
	}

	public void setHeader(Map<String, String> header) {
		this.header = header;
	}

}
