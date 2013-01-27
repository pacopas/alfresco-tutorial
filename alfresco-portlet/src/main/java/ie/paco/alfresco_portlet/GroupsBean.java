package ie.paco.alfresco_portlet;

import ie.paco.alfresco_portlet.http.Requestor;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class GroupsBean {
	
	private static final Log LOGGER = LogFactory.getLog(GroupsBean.class);
	
	private static final String ALFRESCO_GROUPS_URL;
	private static final String ALFRESCO_BASE_URL = "alfresco.service.url.base";
	private static final String ALFRESCO_SERVICE_GROUPS= "alfresco.service.groups";
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("alfresco");
	private static final Map<String, String> HEADERS;
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	static {
		ALFRESCO_GROUPS_URL = BUNDLE.getString(ALFRESCO_BASE_URL) + BUNDLE.getString(ALFRESCO_SERVICE_GROUPS);
		HEADERS = new HashMap<String, String>();
		HEADERS.put("X-Alfresco-Remote-User", "admin");
	}
	
	private Map<String, Object> groups;
	private Requestor requestor;
	private String selectedGroup;
	private Map<String, Object> children;
	
    @PostConstruct
    public void init() {
		this.getAllGroups();
    }
	
	public void getAllGroups() {
		try {
			String response = requestor.doGet(ALFRESCO_GROUPS_URL, HEADERS);
			groups = MAPPER.readValue(response, new TypeReference<Map<String, Object>>(){});
		} catch (Exception e) {
			LOGGER.error("Excepcion getting the root groups", e);
			groups = null;
		} finally {
			children = null;
		}
	}
	
	public void clearSelection() {
		this.setSelectedGroup(null);
	}

	public Map<String, Object> getGroups() {
		return groups;
	}

	public void setRequestor(Requestor requestor) {
		this.requestor = requestor;
	}

	public String getSelectedGroup() {
		return selectedGroup;
	}

	public void setSelectedGroup(String selectedGroup) {
		this.selectedGroup = selectedGroup;
		if (selectedGroup == null || selectedGroup.length() == 0) {
			children = null;
		} else {
			String url = ALFRESCO_GROUPS_URL + "/" + selectedGroup + "/children";
			try {
				String response = requestor.doGet(url, HEADERS);
				children = MAPPER.readValue(response, new TypeReference<Map<String, Object>>(){});
			} catch (Exception e) {
				LOGGER.error("Exception getting the content of group '" + selectedGroup + "'", e);
				children = null;
				this.selectedGroup = null;
			}
		}
	}

	public Map<String, Object> getChildren() {
		return children;
	}

}
