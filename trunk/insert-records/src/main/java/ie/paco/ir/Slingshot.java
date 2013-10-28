package ie.paco.ir;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;

public class Slingshot {
	
	private static final Log LOG = LogFactory.getLog(Slingshot.class);
	
	private RecordReader recordReader;
	private Map<String, RecordWriter> recordWriters;
	
	public void go(String inputPath) throws IOException {
//		String[] record = {"C003", "Chuck Three", "M", "2/15/1999"};
//		String[] record = {"N004", "Dan Four", "M", "3/20/2000"};
//		String[] record = {"10/28/2013", "17.03", "17.03", "1"};
		
		boolean inputOpened = recordReader.openResource(new String[]{inputPath});
		if (inputOpened) {
			String[] record = null;
			String id = null;
			String patientId = null;
			String type = null;
			RecordWriter recordWriter = null;
			do {
				record = recordReader.nextRecord();
				if (record != null && record.length > 0) {
					type = record[0];
					recordWriter = recordWriters.get(type);
					for (int i = 1; i < record.length; i++) {
						record[i - 1] = record[i];
					}
					// Put the patient's id at the end. It is going to be needed my Measurement records
					record[record.length - 1] = patientId;
					try {
						id = recordWriter.insert(record);
						if (id == null) {
							LOG.error("Couldn't insert the record of type " + type);
						} else {
							if ("PATIENT".equals(type)) {
								patientId = id;
							}
						}
					} catch (JSONException e) {
						LOG.error("Couldn't insert the record of type " + type, e);
					}
				}
			} while (record != null);
		} else {
			LOG.error("Couldn't open the input source '" + inputPath + "'");
		}
	}

	public void setRecordReader(RecordReader recordReader) {
		this.recordReader = recordReader;
	}

	public void setRecordWriters(Map<String, RecordWriter> recordWriters) {
		this.recordWriters = recordWriters;
	}

}
