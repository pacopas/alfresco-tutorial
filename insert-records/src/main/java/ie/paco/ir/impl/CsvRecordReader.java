package ie.paco.ir.impl;

import ie.paco.ir.RecordReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CsvRecordReader implements RecordReader {

	private static final Log LOG = LogFactory.getLog(CsvRecordReader.class);

	private BufferedReader reader;

	public boolean openResource(String[] config) throws IOException {
		boolean opened = false;
		if (config == null || config.length == 0) {
			LOG.error("Resource hasn't been specified");
		} else {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(config[0])));
			opened = true;
		}
		return opened;
	}
	
	public String[] nextRecord() throws IOException {
		if (reader == null) {
			throw new IllegalStateException("No BufferedReader has been opened for this RecordReader");
		}
		String[] record = null;
		String line = reader.readLine();
		if (line != null) {
			record = line.split(",");
		}
		return record;
	}
	
	public BufferedReader getReader() {
		return reader;
	}
}
