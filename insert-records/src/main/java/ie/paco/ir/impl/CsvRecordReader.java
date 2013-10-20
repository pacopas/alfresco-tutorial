package ie.paco.ir.impl;

import ie.paco.ir.RecordReader;
import java.io.BufferedReader;

public class CsvRecordReader implements RecordReader {

	private BufferedReader reader;

	@Override
	public boolean openResource(String[] config) {
		return false;
	}
	
	@Override
	public String[] nextRecord() {
		return null;
	}
}
