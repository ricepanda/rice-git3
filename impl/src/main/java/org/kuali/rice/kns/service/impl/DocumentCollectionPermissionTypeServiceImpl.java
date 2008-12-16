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
package org.kuali.rice.kns.service.impl;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.kim.bo.impl.KimAttributes;
import org.kuali.rice.kim.bo.role.KimPermission;
import org.kuali.rice.kim.bo.types.dto.AttributeSet;
import org.kuali.rice.kim.util.KimConstants;

/**
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 */
public class DocumentCollectionPermissionTypeServiceImpl extends DocumentTypePermissionTypeServiceImpl {

	{
		// TODO: uncomment following line of code
		// requiredAttributes.add(KimAttributes.COLLECTION_ITEM_TYPE_CODE);
	}
	
	/**
	 * @see org.kuali.rice.kns.service.impl.DocumentTypePermissionTypeServiceImpl#performPermissionMatch(org.kuali.rice.kim.bo.types.dto.AttributeSet, org.kuali.rice.kim.bo.role.KimPermission)
	 */
	@Override
	protected boolean performPermissionMatch(AttributeSet requestedDetails, KimPermission permission) {
		return true;
		/*if (!super.performPermissionMatch(requestedDetails, permission)) {
			return false;
		}
		
		boolean addTemplate = "Add Attachment".equals(permission.getTemplate().getName()) || "Add Note".equals(permission.getTemplate().getName());		
		if (!addTemplate && StringUtils.isEmpty(requestedDetails.get(KimAttributes.CREATE_BY_SELF_ONLY))) {
			throw new RuntimeException(KimAttributes.CREATE_BY_SELF_ONLY + " should not be blank or null.");
		}
		
		boolean match = requestedDetails.get(KimAttributes.COMPONENT_NAME).equals(permission.getDetails().get(KimAttributes.COMPONENT_NAME));
		match &=  requestedDetails.get(KimAttributes.COLLECTION_ITEM_TYPE_CODE).equals(permission.getDetails().get(KimAttributes.COLLECTION_ITEM_TYPE_CODE));
		if (!addTemplate) {
			match &= requestedDetails.get(KimAttributes.CREATE_BY_SELF_ONLY).startsWith(permission.getDetails().get(KimAttributes.CREATE_BY_SELF_ONLY));
		}
		return match;*/
	}
	
}
