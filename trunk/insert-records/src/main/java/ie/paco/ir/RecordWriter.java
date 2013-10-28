package ie.paco.ir;

import java.io.IOException;

import org.json.JSONException;

public interface RecordWriter {

	public String insert(String[] record) throws IOException, JSONException;
	
}
