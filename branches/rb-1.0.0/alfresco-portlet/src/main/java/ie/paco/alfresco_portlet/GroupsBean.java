package ie.paco.alfresco_portlet;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class GroupsBean {
	
	private static final Log LOGGER = LogFactory.getLog(GroupsBean.class);
	
	private static final String ALFRESCO_GROUPS_URL = "http://192.168.145.168:8080/alfresco/wcservice/api/groups";
	
	private Map<String, Object> groups = null;
	
	public GroupsBean() {
    	HttpClient client = new HttpClient();
    	GetMethod method = new GetMethod(ALFRESCO_GROUPS_URL);
    	method.addRequestHeader("X-Alfresco-Remote-User", "admin");
        try {
            // Execute the method.
            int statusCode = client.executeMethod(method);

            if (statusCode != HttpStatus.SC_OK) {
              LOGGER.error("Method failed: " + method.getStatusLine());
            }

            // Read the response body.
            String response = method.getResponseBodyAsString();

            // Deal with the response.
            LOGGER.debug(response);
            ObjectMapper mapper = new ObjectMapper();
            groups = mapper.readValue(response, new TypeReference<Map<String, Object>>(){});

          } catch (HttpException e) {
            LOGGER.error("Fatal protocol violation:", e);
          } catch (IOException e) {
            LOGGER.error("Fatal transport error:", e);
          } finally {
            // Release the connection.
            method.releaseConnection();
          }  
	}

	public Map<String, Object> getGroups() {
		return groups;
	}

}
