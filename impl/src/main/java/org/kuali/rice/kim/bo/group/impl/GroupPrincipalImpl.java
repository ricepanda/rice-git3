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
package org.kuali.rice.kim.bo.group.impl;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.kuali.rice.kim.bo.group.GroupPrincipal;
import org.kuali.rice.kns.bo.InactivateableFromTo;

/**
 * This is a description of what this class does - kellerj don't forget to fill this in. 
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 *
 */
@Entity
@Table(name="KRIM_GRP_PRNCPL_T")
public class GroupPrincipalImpl extends GroupMemberBase implements GroupPrincipal, InactivateableFromTo {

	@Column(name="PRNCPL_ID")
	protected String memberPrincipalId;
	@Column(name="ACTV_FRM_IND")
	protected Timestamp activeFromDate;
	@Column(name="ACTV_TO_IND")
	protected Timestamp activeToDate;
	
	/**
	 * @return the active
	 */
	public boolean isActive() {
		long now = System.currentTimeMillis();
		return (activeFromDate == null || activeFromDate.getTime() < now) && (activeToDate == null || activeToDate.getTime() > now);
	}

	public void setActiveFromDate(Timestamp from) {
		this.activeFromDate = from;
	}

	public void setActiveToDate(Timestamp to) {
		this.activeToDate = to;
	}
	
	public String getMemberPrincipalId() {
		return this.memberPrincipalId;
	}

	public void setMemberPrincipalId(String memberPrincipalId) {
		this.memberPrincipalId = memberPrincipalId;
	}
	
}
