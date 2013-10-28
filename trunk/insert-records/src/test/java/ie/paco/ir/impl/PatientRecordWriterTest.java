package ie.paco.ir.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ie.paco.common.http.Requestor;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

public class PatientRecordWriterTest {
	
	private PatientRecordWriter recordWriter;
	private Requestor requestor;
	private String uri;
	private Map<String, String> header;
	private Map<String, String> params;

	@Before
	public void setUp() throws Exception {
		recordWriter = new PatientRecordWriter();
		requestor = mock(Requestor.class);
		uri = "http://www.netsuite.com";
		header = new HashMap<String, String>();
		header.put("headerKey", "headerValue");
		recordWriter.setRequestor(requestor);
		recordWriter.setUri(uri);
		recordWriter.setHeader(header);

		params = new HashMap<String, String>();
		params.put("nhs", "1");
		params.put("name", "2");
		params.put("gender", "3");
		params.put("dateOfBirth", "4");
	}

	@Test
	public void testInsert_NULL_RECORD() throws IOException, JSONException {
		String result = recordWriter.insert(null);
		assertNull(result);
	}

	@Test
	public void testInsert_EMPTY_RECORD() throws IOException, JSONException {
		String result = recordWriter.insert(new String[]{});
		assertNull(result);
	}

	@Test
	public void testInsert_NOT_ENOUGH_RECORD() throws IOException, JSONException {
		String result = recordWriter.insert(new String[]{"1", "2", "3"});
		assertNull(result);
	}

	@Test(expected=JSONException.class)
	public void testInsert_ERROR() throws IOException, JSONException {
		when(requestor.doPost(uri, header, params)).thenReturn("{\"error\":\"error\"}");
		String result = recordWriter.insert(new String[]{"1", "2", "3", "4"});
		assertNull(result);
	}

	@Test(expected=JSONException.class)
	public void testInsert_NO_JSON() throws IOException, JSONException {
		when(requestor.doPost(uri, header, params)).thenReturn("no_json");
		recordWriter.insert(new String[]{"1", "2", "3", "4"});
	}

	@Test
	public void testInsert() throws IOException, JSONException {
		when(requestor.doPost(uri, header, params)).thenReturn("{\"id\":\"1000\"}");
		String result = recordWriter.insert(new String[]{"1", "2", "3", "4"});
		assertEquals("1000", result);
	}

}
