package com.thetechconsultant.tutorial.model;

import org.alfresco.service.namespace.QName;

public interface TutorialModel {
	
	public static final String TUTORIAL_URI = "http://www.thetechconsultant.com/model/tutorial";
	
	public QName TYPE_HC_CONTENT = QName.createQName(TUTORIAL_URI, "hcContent");

}
