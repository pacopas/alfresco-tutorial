package ie.paco.alfresco.util.impl;

import java.text.Format;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ie.paco.alfresco.util.FormatFactory;

public class DateFormatFactory implements FormatFactory {
	
	private static Log logger = LogFactory.getLog(DateFormatFactory.class);
	
	private Map<String, Format> formats;

	@Override
	public Format getFormat(String key) {
		return formats.get(key);
	}

	public Map<String, Format> getFormats() {
		return formats;
	}

	public void setFormats(Map<String, Format> formats) {
		logger.debug("Setting date formats for patterns " + formats.keySet());
		this.formats = formats;
	}

}
