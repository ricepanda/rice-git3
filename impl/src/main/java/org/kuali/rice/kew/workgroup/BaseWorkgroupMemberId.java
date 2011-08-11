/*
 * Copyright 2005-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl2.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.rice.kew.workgroup;

import java.io.Serializable;

import javax.persistence.Column;

import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * This Compound Primary Class has been generated by the rice ojb2jpa Groovy script.  Please
 * note that there are no setter methods, only getters.  This is done purposefully as cpk classes
 * can not change after they have been created.  Also note they require a public no-arg constructor.
 * TODO: Implement the equals() and hashCode() methods. 
 */
public class BaseWorkgroupMemberId implements Serializable {

    @Column(name="GRP_ID")
    private Long workgroupId;
    @Column(name="WRKGRP_MBR_PRSN_EN_ID")
    private String workflowId;
    @Column(name="WRKGRP_VER_NBR")
    private Integer workgroupVersionNumber;

    public BaseWorkgroupMemberId() {}

    public Long getWorkgroupId() { return workgroupId; }

    public String getWorkflowId() { return workflowId; }

    public Integer getWorkgroupVersionNumber() { return workgroupVersionNumber; }
    
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof BaseWorkgroupMemberId)) return false;
        if (o == null) return false;
        BaseWorkgroupMemberId pk = (BaseWorkgroupMemberId) o;
        return getWorkgroupId() != null && getWorkflowId() != null && getWorkgroupVersionNumber() != null && getWorkgroupId().equals(pk.getWorkgroupId()) && getWorkflowId().equals(pk.getWorkflowId()) && getWorkgroupVersionNumber().equals(pk.getWorkgroupVersionNumber());        
    }

    public int hashCode() {
    	return new HashCodeBuilder().append(workgroupId).append(workflowId).append(workgroupVersionNumber).toHashCode();
    }

}

