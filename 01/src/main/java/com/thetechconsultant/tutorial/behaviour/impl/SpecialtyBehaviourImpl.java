package com.thetechconsultant.tutorial.behaviour.impl;

import org.alfresco.repo.node.NodeServicePolicies;
import org.alfresco.repo.policy.Behaviour;
import org.alfresco.repo.policy.Behaviour.NotificationFrequency;
import org.alfresco.repo.policy.JavaBehaviour;
import org.alfresco.repo.policy.PolicyComponent;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.thetechconsultant.tutorial.behaviour.SpecialtyBehaviour;
import com.thetechconsultant.tutorial.model.TutorialModel;

public class SpecialtyBehaviourImpl implements SpecialtyBehaviour {
	
	private static final Log logger = LogFactory.getLog(SpecialtyBehaviourImpl.class);
	
	private Behaviour onCreateNode;
	private PolicyComponent policyComponent;
	
	public void init() {
		this.onCreateNode = new JavaBehaviour(this, "onCreateNode",
				NotificationFrequency.TRANSACTION_COMMIT);
		this.policyComponent.bindClassBehaviour(NodeServicePolicies.OnCreateNodePolicy.QNAME,
				TutorialModel.TYPE_HC_CONTENT, this.onCreateNode);
		logger.info("Behaviour bound to policy \"onCreateNode\"");
	}

	@Override
	public void onCreateNode(ChildAssociationRef arg0) {
		System.out.println("Just in case");
		if (logger.isDebugEnabled()) {
			logger.debug("Entering onCreateNode()...");
		}
	}

	public void setPolicyComponent(PolicyComponent policyComponent) {
		this.policyComponent = policyComponent;
	}

}
