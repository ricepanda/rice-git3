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
package org.kuali.rice.kns.exception;

import org.kuali.rice.kns.util.RiceKeyConstants;

/**
 * This class represents an exception that is thrown when a given user is not authorized to take a given action on the given
 * document type.
 * 
 * 
 */
public class DocumentAuthorizationException extends AuthorizationException {

    /**
     * Constructs a DocumentAuthorizationException for a specific document rather than a documentType.
     * 
     * @param userId
     * @param action
     * @param documentId
     */
    public DocumentAuthorizationException(String userId, String action, String documentId) {
        super(userId, action, documentId.toString(), "user '" + userId + "' is not authorized to " + action + " document '" + documentId + "'");
    }

    /**
     * @see org.kuali.rice.kns.exception.AuthorizationException#getErrorMessageKey()
     */
    public String getErrorMessageKey() {
        return RiceKeyConstants.AUTHORIZATION_ERROR_DOCUMENT;
    }

}
