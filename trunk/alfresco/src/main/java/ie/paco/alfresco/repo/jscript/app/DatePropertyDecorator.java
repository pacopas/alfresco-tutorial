package ie.paco.alfresco.repo.jscript.app;

import ie.paco.alfresco.util.FormatFactory;

import java.io.Serializable;
import java.text.Format;

import org.alfresco.repo.jscript.app.BasePropertyDecorator;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

public class DatePropertyDecorator extends BasePropertyDecorator {
	
	private static final Log logger = LogFactory.getLog(DatePropertyDecorator.class);
	
	private FormatFactory formatFactory;

	@SuppressWarnings("unchecked")
	@Override
	public JSONAware decorate(QName propertyName, NodeRef nodeRef, Serializable value) {
		logger.debug("Decorating property " + propertyName + " with value " + value);
        JSONObject map = new JSONObject();
        Format dateFormat = formatFactory.getFormat("dd/MM/yy");
        String formatted = dateFormat.format(value);
        logger.debug("Formatted value: " + formatted);
        map.put("raw", value.toString());
        map.put("formatted", formatted);
		return map;
	}

	public FormatFactory getFormatFactory() {
		return formatFactory;
	}

	public void setFormatFactory(FormatFactory formatFactory) {
		this.formatFactory = formatFactory;
	}

}
