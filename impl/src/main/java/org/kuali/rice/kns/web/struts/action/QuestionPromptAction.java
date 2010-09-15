/*
 * Copyright 2005-2007 The Kuali Foundation
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

package org.kuali.rice.kns.web.struts.action;

import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.rice.core.util.RiceConstants;
import org.kuali.rice.kns.exception.AuthorizationException;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.UrlFactory;
import org.kuali.rice.kns.web.struts.form.QuestionPromptForm;

/**
 * This class handles Actions for QuestionPromp.
 *
 *
 */
public class QuestionPromptAction extends KualiAction {
	
    /**
	 * This overridden method ...
	 * 
	 * @see org.kuali.rice.kns.web.struts.action.KualiAction#checkAuthorization(org.apache.struts.action.ActionForm, java.lang.String)
	 */
	@Override
	protected void checkAuthorization(ActionForm form, String methodToCall)
			throws AuthorizationException {
		// no authorization required
	}

	/**
     * This method is the entry point action for the question prompt component.
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward start(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // deal with the fact that some requests might be reposts from errors on the reason field
        processErrorMessages(request);

        return mapping.findForward(RiceConstants.MAPPING_BASIC);
    }

    /**
     * This method handles gathering all input and passing control back to the caller action.
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward processAnswer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        QuestionPromptForm questionPromptForm = (QuestionPromptForm) form;

        Properties parameters = new Properties();

        Map parameterMap = request.getParameterMap();
        for (Object parameterName : parameterMap.keySet()) {
        	//ensures that the current methodToCall is not added to the url...this is a bit hacky
        	if (!((String)parameterName).startsWith("methodToCall.processAnswer")) {
        		parameters.put(parameterName, parameterMap.get(parameterName));	
        	}
        }
        parameters.put(KNSConstants.DOC_FORM_KEY, questionPromptForm.getFormKey());
        parameters.put(KNSConstants.QUESTION_CLICKED_BUTTON, getSelectedButton(request));
        parameters.put(KNSConstants.METHOD_TO_CALL_ATTRIBUTE, questionPromptForm.getCaller());
        parameters.put(KNSConstants.REFRESH_CALLER, KNSConstants.QUESTION_REFRESH);
        parameters.put(KNSConstants.QUESTION_INST_ATTRIBUTE_NAME, questionPromptForm.getQuestionIndex());
        if(questionPromptForm.getDocNum() != null){
        	parameters.put(KNSConstants.DOC_NUM, questionPromptForm.getDocNum());
        }
        
        if (StringUtils.isNotBlank(questionPromptForm.getQuestionAnchor())) {
            parameters.put(KNSConstants.ANCHOR, questionPromptForm.getQuestionAnchor());
        }

        String context = questionPromptForm.getContext();
        if (StringUtils.isNotBlank(context)) {
            parameters.put(KNSConstants.QUESTION_CONTEXT, context);
        }
        String reason = questionPromptForm.getReason();
        if (StringUtils.isNotBlank(reason)) {
            parameters.put(KNSConstants.QUESTION_REASON_ATTRIBUTE_NAME, reason);
        }
        if (StringUtils.isNotBlank(questionPromptForm.getMethodToCallPath())) {
            // For header tab navigation. Leaving it blank will just kick user back to page.
            parameters.put(questionPromptForm.getMethodToCallPath(), "present");
        }

        String returnUrl = UrlFactory.parameterizeUrl(questionPromptForm.getBackLocation(), parameters);

        return new ActionForward(returnUrl, true);
    }

    /**
     * Parses the method to call attribute to pick off the button number that was pressed.
     *
     * @param request
     * @return int
     */
    private String getSelectedButton(HttpServletRequest request) {
        String selectedButton = "-1";
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            selectedButton = StringUtils.substringBetween(parameterName, ".button", ".");
        }

        return selectedButton;
    }

    /**
     * This method handles processing any error messages coming in the door.
     *
     * @param request
     */
    private void processErrorMessages(HttpServletRequest request) {
        String errorKey = request.getParameter(KNSConstants.QUESTION_ERROR_KEY);
        String errorPropertyName = request.getParameter(KNSConstants.QUESTION_ERROR_PROPERTY_NAME);
        String errorParameter = request.getParameter(KNSConstants.QUESTION_ERROR_PARAMETER);

        if (StringUtils.isNotBlank(errorKey)) {
            if (StringUtils.isBlank(errorPropertyName)) {
                throw new IllegalStateException("Both the errorKey and the errorPropertyName must be filled in, " + "in order for errors to be displayed by the question component.  Currently, " + "only the errorKey has a value specified.");
            }
            else {
                if (StringUtils.isBlank(errorParameter)) {
                    GlobalVariables.getMessageMap().putErrorWithoutFullErrorPath(errorPropertyName, errorKey);
                }
                else {
                    GlobalVariables.getMessageMap().putErrorWithoutFullErrorPath(errorPropertyName, errorKey, errorParameter);
                }
            }
        }
    }
}
