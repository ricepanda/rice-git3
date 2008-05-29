/*
 * Copyright 2007 The Kuali Foundation
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
package edu.iu.uis.eden.actionitem;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Outbox item.  An extension of {@link ActionItemActionListExtension} for OJB.
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 *
 */
@Entity
@Table(name="EN_OUT_BOX_ITM_T")
@AttributeOverrides({
    @AttributeOverride(name="actionItemId", column=@Column(name="ACTN_ITM_ID")), 
    @AttributeOverride(name="workflowId", column=@Column(name="ACTN_ITM_PRSN_EN_ID")), 
    @AttributeOverride(name="dateAssigned", column=@Column(name="ACTN_ITM_ASND_DT")), 
    @AttributeOverride(name="actionRequestCd", column=@Column(name="ACTN_ITM_RQST_CD")), 
    @AttributeOverride(name="actionRequestId", column=@Column(name="ACTN_RQST_ID")), 
    @AttributeOverride(name="routeHeaderId", column=@Column(name="DOC_HDR_ID")), 
    @AttributeOverride(name="responsibilityId", column=@Column(name="ACTN_ITM_RESP_ID")), 
    @AttributeOverride(name="workgroupId", column=@Column(name="WRKGRP_ID")), 
    @AttributeOverride(name="roleName", column=@Column(name="ROLE_NM")), 
    @AttributeOverride(name="docTitle", column=@Column(name="DOC_TTL")), 
    @AttributeOverride(name="docLabel", column=@Column(name="DOC_TYP_LBL_TXT")), 
    @AttributeOverride(name="docHandlerURL", column=@Column(name="DOC_TYP_HDLR_URL_ADDR")), 
    @AttributeOverride(name="docName", column=@Column(name="DOC_TYP_NM")), 
    @AttributeOverride(name="delegatorWorkflowId", column=@Column(name="ACTN_ITM_DLGN_PRSN_EN_ID")), 
    @AttributeOverride(name="delegatorWorkgroupId", column=@Column(name="ACTN_ITM_DLGN_WRKGRP_ID")), 
    @AttributeOverride(name="delegationType", column=@Column(name="DLGN_TYP")), 
    @AttributeOverride(name="lockVerNbr", column=@Column(name="DB_LOCK_VER_NBR"))
})

public class OutboxItemActionListExtension extends ActionItemActionListExtension {

    private static final long serialVersionUID = 5776214610837858304L;

    public OutboxItemActionListExtension() {}
    
    public OutboxItemActionListExtension(ActionItem actionItem) {
	this.setActionRequestCd(actionItem.getActionRequestCd());
	this.setActionRequestId(actionItem.getActionRequestId());
	this.setActionToTake(actionItem.getActionToTake());
	this.setDateAssigned(actionItem.getDateAssigned());
	this.setDelegationType(actionItem.getDelegationType());
	this.setDelegatorWorkflowId(actionItem.getDelegatorWorkflowId());
	this.setDelegatorWorkgroupId(actionItem.getDelegatorWorkgroupId());
	this.setDocHandlerURL(actionItem.getDocHandlerURL());
	this.setDocLabel(actionItem.getDocLabel());
	this.setDocName(actionItem.getDocName());
	this.setDocTitle(actionItem.getDocTitle());
	this.setResponsibilityId(actionItem.getResponsibilityId());
	this.setRoleName(actionItem.getRoleName());
	this.setRouteHeader(actionItem.getRouteHeader());
	this.setRouteHeaderId(actionItem.getRouteHeaderId());
	this.setWorkflowId(actionItem.getWorkflowId());
	this.setWorkgroupId(actionItem.getWorkgroupId());
    }
    
}
