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

import java.util.ArrayList;
import java.util.List;

import org.kuali.rice.kim.bo.impl.KimAttributes;
import org.kuali.rice.kim.bo.types.dto.AttributeSet;
import org.kuali.rice.kim.service.support.impl.KimRoleTypeServiceBase;
import org.kuali.rice.kim.util.KimCommonUtils;

/**
 * This is a description of what this class does - bhargavp don't forget to fill this in. 
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 *
 */
public class NamespaceRoleTypeServiceImpl extends KimRoleTypeServiceBase {

	protected List<String> requiredAttributes = new ArrayList<String>();
	{
		requiredAttributes.add(KimAttributes.NAMESPACE_CODE);
	}
	
	/**
	 * @see org.kuali.rice.kim.service.support.impl.KimRoleTypeServiceBase#performRoleQualifierQualificationMatch(org.kuali.rice.kim.bo.types.dto.AttributeSet, org.kuali.rice.kim.bo.types.dto.AttributeSet)
	 */
	@Override
	public boolean performMatch(AttributeSet qualification, AttributeSet roleQualifier) {
		validateRequiredAttributesAgainstReceived(requiredAttributes, qualification, QUALIFICATION_RECEIVED_ATTIBUTES_NAME);
		validateRequiredAttributesAgainstReceived(requiredAttributes, roleQualifier, ROLE_QUALIFIERS_RECEIVED_ATTIBUTES_NAME);
		return !qualification.containsKey(KimAttributes.NAMESPACE_CODE) || qualification.get(KimAttributes.NAMESPACE_CODE).matches(
				roleQualifier.get(KimAttributes.NAMESPACE_CODE)
						.replaceAll("\\*", ".*"));
	}
}
