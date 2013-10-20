package ie.paco.ir.impl;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CsvRecordReaderTest {

	private CsvRecordReader recordReader;
	
	@Before
	public void setUp() {
		recordReader = new CsvRecordReader();
	}
	
	@Test(expected = FileNotFoundException.class)
	public void testOpenResource_NOT_FOUND() {
		boolean opened = recordReader.openResource(new String[]{"not_found.csv"});
	}
	
}
