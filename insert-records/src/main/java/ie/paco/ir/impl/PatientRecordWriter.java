package ie.paco.ir.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;

public class PatientRecordWriter extends AbstractRecordWriter {
	
	private static final Log LOG = LogFactory.getLog(PatientRecordWriter.class);

	public String insert(String[] record) throws IOException, JSONException {
		String id = null;
		if (record == null || record.length < 4) {
			LOG.error("Couldn't insert the record: 4 parameters expected.");
		} else {
			Map<String, String> params = new HashMap<String, String>();
			params.put("nhs", record[0]);
			params.put("name", record[1]);
			params.put("gender", record[2]);
			params.put("dateOfBirth", record[3]);
			String response = this.getRequestor().doPost(this.getUri(), this.getHeader(), params);
			LOG.info("Response: " + response);
			JSONObject json = new JSONObject(response);
			id = json.getString("id");
		}
		return id;
	}

}
