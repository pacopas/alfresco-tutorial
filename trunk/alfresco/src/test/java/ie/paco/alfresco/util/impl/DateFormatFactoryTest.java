package ie.paco.alfresco.util.impl;

import static org.junit.Assert.*;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class DateFormatFactoryTest {
	
	private DateFormatFactory factory;
	private Map<String, Format> formats;
	private Format aFormat;

	@Before
	public void setUp() throws Exception {
		aFormat = new SimpleDateFormat("yyyy");
		factory = new DateFormatFactory();
		formats = new HashMap<String, Format>();
		formats.put("yyyy", aFormat);
		factory.setFormats(formats);
	}

	@Test
	public void testGetFormat_NOT_FOUND() {
		Format format = factory.getFormat("dd/MM/yyyy");
		assertNull(format);
	}

	@Test
	public void testGetFormat() {
		Format format = factory.getFormat("yyyy");
		assertEquals(aFormat, format);
	}

}
