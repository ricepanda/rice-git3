/*
 * Copyright 2005-2006 The Kuali Foundation.
 *
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.rice.kew.engine.simulation;

import java.util.ArrayList;
import java.util.List;

import org.kuali.rice.kew.actionrequest.Recipient;
import org.kuali.rice.kew.util.Utilities;
import org.kuali.rice.kim.bo.Person;


/**
 * Criteria which acts as input to the {@link SimulationEngine}.
 *
 * @see SimulationEngine
 *
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 */
public class SimulationCriteria {

	// fields related to document simulation
	private Long documentId;
    private String destinationNodeName;
    private List<Recipient> destinationRecipients = new ArrayList<Recipient>();

    // fields related to document type simulation
    private String documentTypeName;
    private String xmlContent;
    private List<String> nodeNames = new ArrayList<String>();
    private List<String> ruleTemplateNames = new ArrayList<String>();

    // fields related to both simulation types
    private Boolean activateRequests;
    private Person routingUser;
    private List<SimulationActionToTake> actionsToTake = new ArrayList<SimulationActionToTake>();
    private boolean flattenNodes;

    public SimulationCriteria() {
    	this.activateRequests = null;
    	this.flattenNodes = false;
    }

    public SimulationCriteria(Long documentId) {
    	this.documentId = documentId;
    }

    public SimulationCriteria(String documentTypeName) {
    	this.documentTypeName = documentTypeName;
    }

    public Boolean isActivateRequests() {
        return this.activateRequests;
    }

    public void setActivateRequests(Boolean activateRequests) {
        this.activateRequests = activateRequests;
    }

    public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public String getDestinationNodeName() {
        return destinationNodeName;
    }

    public void setDestinationNodeName(String destinationNodeName) {
        this.destinationNodeName = destinationNodeName;
    }

    public List<Recipient> getDestinationRecipients() {
        return destinationRecipients;
    }

    public void setDestinationRecipients(List<Recipient> destinationRecipients) {
        this.destinationRecipients = destinationRecipients;
    }

	public String getDocumentTypeName() {
		return documentTypeName;
	}

	public void setDocumentTypeName(String documentTypeName) {
		this.documentTypeName = documentTypeName;
	}

	public List<String> getRuleTemplateNames() {
		return ruleTemplateNames;
	}

	public void setRuleTemplateNames(List<String> ruleTemplateNames) {
		this.ruleTemplateNames = ruleTemplateNames;
	}

	public String getXmlContent() {
		return xmlContent;
	}

	public void setXmlContent(String xmlContent) {
		this.xmlContent = xmlContent;
	}

	public List<String> getNodeNames() {
		return nodeNames;
	}

	public void setNodeNames(List<String> nodeNames) {
		this.nodeNames = nodeNames;
	}

	public boolean isDocumentSimulation() {
		return documentId != null;
	}

	public boolean isDocumentTypeSimulation() {
		return !Utilities.isEmpty(documentTypeName);
	}

	public List<SimulationActionToTake> getActionsToTake() {
		return actionsToTake;
	}

	public void setActionsToTake(List<SimulationActionToTake> actionsToTake) {
		this.actionsToTake = actionsToTake;
	}

	public Person getRoutingUser() {
		return routingUser;
	}

	public void setRoutingUser(Person routingUser) {
		this.routingUser = routingUser;
	}

	public boolean isFlattenNodes() {
		return this.flattenNodes;
	}

	public void setFlattenNodes(boolean flattenNodes) {
		this.flattenNodes = flattenNodes;
	}

}
