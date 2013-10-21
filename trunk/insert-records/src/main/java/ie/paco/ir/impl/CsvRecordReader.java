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

	@Override
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
	
	@Override
	public String[] nextRecord() throws IOException {
		return reader.readLine().split(",");
	}
	
	public BufferedReader getReader() {
		return reader;
	}
}
