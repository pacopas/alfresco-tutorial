package ie.paco.ir.impl;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.IOException;

public class CsvRecordReaderTest {

	private CsvRecordReader recordReader;
	private final String filePath = "src/test/test.csv";
	private File testFile;
	
	@Before
	public void setUp() {
		recordReader = new CsvRecordReader();
		testFile = new File(filePath);
		testFile.setReadable(true, false);
	}
	
	@Test(expected = FileNotFoundException.class)
	public void testOpenResource_NOT_FOUND() throws IOException {
		boolean opened = recordReader.openResource(new String[]{"not_found.csv"});
	}
	
	@Test
	public void testOpenResource_NULL_CONFIG() throws IOException {
		boolean opened = recordReader.openResource(null);
		assertFalse(opened);
		assertNull(recordReader.getReader());
	}
	
	@Test
	public void testOpenResource_EMPTY_CONFIG() throws IOException {
		boolean opened = recordReader.openResource(new String[0]);
		assertFalse(opened);
		assertNull(recordReader.getReader());
	}
	
	@Test
	public void testOpenResource() throws IOException {
		boolean opened = recordReader.openResource(new String[]{filePath});
		assertTrue(opened);
		assertNotNull("reader shouldn't be null", recordReader.getReader());
	}
	
	@Test(expected = IOException.class)
	public void testNextRecord() throws IOException {
		try {
			recordReader.openResource(new String[]{filePath});
		} catch (IOException ioe) {}
		testFile.setReadable(false, false);
		recordReader.nextRecord();
	}
	
}
