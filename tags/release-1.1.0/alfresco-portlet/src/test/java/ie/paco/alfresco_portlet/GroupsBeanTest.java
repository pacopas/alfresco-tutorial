package ie.paco.alfresco_portlet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ie.paco.alfresco_portlet.http.Requestor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GroupsBeanTest {
	
	private GroupsBean bean;
	private Requestor requestor;
	private String response;
	private Map<String, Object> group;
	private List<Map<String, Object>> data;
	private String selectedGroup;

	@Before
	public void setUp() throws Exception {
		bean = new GroupsBean();
		requestor = mock(Requestor.class);
		bean.setRequestor(requestor);
		
		response = "{\"data\" : [ { \"shortName\" : \"DUMMY_GROUP\" } ] }";
		group = new HashMap<String, Object>();
		group.put("shortName", "DUMMY_GROUP");
		data = new ArrayList<Map<String, Object>>();
		data.add(group);
		selectedGroup = "group_name";
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInit() throws Exception {
		this.testGetAllGroups();
	}

	@Test
	public void testGetAllGroups() throws Exception {
		when(requestor.doGet(anyString(), anyMapOf(String.class, String.class))).thenReturn(response);
		
		bean.getAllGroups();
		assertNull(bean.getChildren());
		assertEquals(1, bean.getGroups().size());
		assertEquals(data, bean.getGroups().get("data"));
	}

	@Test
	public void testGetAllGroups_EXCEPTION() {
		try {
			when(requestor.doGet(anyString(), anyMapOf(String.class, String.class))).thenThrow(new Exception());
		} catch (Exception e) {}
		
		bean.getAllGroups();
		assertNull(bean.getChildren());
		assertNull(bean.getGroups());
	}

	@Test
	public void testClearSelection() {
		bean.clearSelection();
		assertNull(bean.getSelectedGroup());
		assertNull(bean.getChildren());
	}

	@Test
	public void testSetSelectedGroup_NULL() throws Exception {
		bean.setSelectedGroup(null);
		assertNull(bean.getChildren());
		assertNull(bean.getSelectedGroup());
	}

	@Test
	public void testSetSelectedGroup() throws Exception {
		when(requestor.doGet(anyString(), anyMapOf(String.class, String.class))).thenReturn(response);
		
		bean.setSelectedGroup(selectedGroup);
		assertEquals(selectedGroup, bean.getSelectedGroup());
		assertEquals(1, bean.getChildren().size());
		assertEquals(data, bean.getChildren().get("data"));
	}

	@Test
	public void testSetSelectedGroup_EXCEPTION() {
		try {
			when(requestor.doGet(anyString(), anyMapOf(String.class, String.class))).thenThrow(new Exception());
		} catch (Exception e) {}
		
		bean.setSelectedGroup(selectedGroup);
		assertNull(bean.getChildren());
		assertNull(bean.getSelectedGroup());
	}

}
