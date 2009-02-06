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
package org.kuali.rice.kim.rule.event.ui;

import org.kuali.rice.kim.bo.ui.KimDocumentRolePermission;
import org.kuali.rice.kim.document.IdentityManagementRoleDocument;
import org.kuali.rice.kim.rule.ui.AddPermissionRule;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.BusinessRule;
import org.kuali.rice.kns.rule.event.KualiDocumentEventBase;
import org.kuali.rice.kns.util.ObjectUtils;

/**
 * This is a description of what this class does - shyu don't forget to fill this in. 
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 *
 */
public class AddPermissionEvent extends KualiDocumentEventBase {
	private KimDocumentRolePermission permission;

	public AddPermissionEvent(String errorPathPrefix, IdentityManagementRoleDocument document) {
        super("adding Permission document " + getDocumentId(document), errorPathPrefix, document);
    }

    public AddPermissionEvent(String errorPathPrefix, Document document, KimDocumentRolePermission permission) {
        this(errorPathPrefix, (IdentityManagementRoleDocument) document);
        this.permission = (KimDocumentRolePermission) ObjectUtils.deepCopy(permission);
    }

    public Class getRuleInterfaceClass() {
        return AddPermissionRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AddPermissionRule) rule).processAddPermission(this);
    }

	/**
	 * @return the permission
	 */
	public KimDocumentRolePermission getPermission() {
		return this.permission;
	}

	/**
	 * @param permission the permission to set
	 */
	public void setPermission(KimDocumentRolePermission permission) {
		this.permission = permission;
	}

}