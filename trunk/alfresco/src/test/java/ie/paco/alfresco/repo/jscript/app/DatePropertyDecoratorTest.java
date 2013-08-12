package ie.paco.alfresco.repo.jscript.app;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import ie.paco.alfresco.util.FormatFactory;

import org.easymock.EasyMock;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.powermock.api.easymock.PowerMock;

import com.ibm.icu.util.Calendar;

public class DatePropertyDecoratorTest {
	
	private DatePropertyDecorator decorator;
	private FormatFactory formatFactory;
	private Date value;

	@Before
	public void setUp() throws Exception {
		decorator = new DatePropertyDecorator();
		formatFactory = PowerMock.createMock(FormatFactory.class);
		decorator.setFormatFactory(formatFactory);
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2013);
		cal.set(Calendar.MONTH, 7);
		cal.set(Calendar.DAY_OF_MONTH, 11);
		value = cal.getTime();
	}

	@After
	public void tearDown() throws Exception {
		PowerMock.verifyAll();
	}

	@Test
	public void testDecorate() {
		EasyMock.expect(formatFactory.getFormat("dd/MM/yy")).andReturn(new SimpleDateFormat("dd/MM/yy"));
		PowerMock.replayAll();
		
		JSONObject json = (JSONObject) decorator.decorate(null, null, value);
		assertEquals(2, json.size());
		assertEquals(value.toString(), json.get("raw"));
		assertEquals("11/08/13", json.get("formatted"));
	}

}
