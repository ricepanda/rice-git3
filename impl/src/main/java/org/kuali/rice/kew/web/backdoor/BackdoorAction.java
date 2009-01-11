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
package org.kuali.rice.kew.web.backdoor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kew.util.Utilities;
import org.kuali.rice.kew.web.WorkflowAction;
import org.kuali.rice.kew.web.session.UserSession;
import org.kuali.rice.kim.bo.group.KimGroup;
import org.kuali.rice.kim.service.KIMServiceLocator;
import org.kuali.rice.kns.util.KNSConstants;


/**
 * A Struts Action which permits a user to execute a backdoor login to masquerade
 * as another user.
 *
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 */
public class BackdoorAction extends WorkflowAction {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(BackdoorAction.class);

    public ActionForward menu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOG.debug("start");
        BackdoorForm backdoorForm = (BackdoorForm) form;
        backdoorForm.setTargetName(Utilities.getKNSParameterValue(KEWConstants.DEFAULT_KIM_NAMESPACE, KNSConstants.DetailTypes.BACKDOOR_DETAIL_TYPE, KEWConstants.BACKDOOR_TARGET_FRAME_NAME));
        return mapping.findForward("viewBackdoor");
    }

    public ActionForward start(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOG.debug("start");
        return portal(mapping, form, request, response);
    }

    public ActionForward portal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
    	LOG.debug("portal started");
    	BackdoorForm backdoorForm=(BackdoorForm)form;
    	backdoorForm.setTargetName(Utilities.getKNSParameterValue(KEWConstants.DEFAULT_KIM_NAMESPACE, KNSConstants.DetailTypes.BACKDOOR_DETAIL_TYPE, KEWConstants.BACKDOOR_TARGET_FRAME_NAME));
    	//LOG.debug(backdoorForm.getGraphic());
    	return mapping.findForward("viewPortal");
    }

    public ActionForward administration(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOG.debug("administration");
        BackdoorForm backdoorForm = (BackdoorForm) form;
        backdoorForm.setTargetName(Utilities.getKNSParameterValue(KEWConstants.DEFAULT_KIM_NAMESPACE, KNSConstants.DetailTypes.BACKDOOR_DETAIL_TYPE, KEWConstants.BACKDOOR_TARGET_FRAME_NAME));
        return mapping.findForward("administration");
    }

    public ActionForward logout(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOG.debug("logout");
        UserSession uSession = getUserSession(request);
        uSession.clearBackdoor();
        setFormGroupPermission((BackdoorForm)form, request);
        //request.setAttribute("reloadPage","true");
        return mapping.findForward("viewPortal");

    }

    public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOG.debug("login");
        /*
        UserSession uSession = getUserSession(request);
        BackdoorForm backdoorForm = (BackdoorForm) form;
        try{
            uSession.setBackdoorId(backdoorForm.getBackdoorId());
            backdoorForm.setBackdoorId(uSession.getNetworkId());
            //setFormGroupPermission(backdoorForm, request);
            //set up preferences as backdoor person
            //uSession.setPreferences(SpringServiceLocator.getPreferencesService().getPreferences(uSession.getWorkflowUser()));
            //request.setAttribute("reloadPage","true");

        }catch(KEWUserNotFoundException ex){
        	uSession.setBackdoorId(null);
        	backdoorForm.setBackdoorId(null);
        	request.setAttribute("BackdoorException","No such user found. Login failure!");
        }
        setFormGroupPermission(backdoorForm, request);
        uSession.setPreferences(SpringServiceLocator.getPreferencesService().getPreferences(uSession.getWorkflowUser()));
        return mapping.findForward("viewPortal");
        */
        UserSession uSession = getUserSession(request);
        BackdoorForm backdoorForm = (BackdoorForm) form;
        if (!uSession.establishBackdoorWithPrincipalName(backdoorForm.getBackdoorId())) {
			request.setAttribute("badbackdoor", "Invalid backdoor Id given '" + backdoorForm.getBackdoorId() + "'");
        	return mapping.findForward("viewBackdoor");
        }
        uSession.getAuthentications().clear();
        setFormGroupPermission(backdoorForm, request);
        return mapping.findForward("viewBackdoor");

    }

    private void setFormGroupPermission(BackdoorForm backdoorForm, HttpServletRequest request) {
        String group = Utilities.getApplicationConstant(KEWConstants.WORKFLOW_ADMIN_WORKGROUP_NAME_KEY);
        KimGroup workflowAdminGroup = KIMServiceLocator.getIdentityManagementService().getGroupByName(Utilities.parseGroupNamespaceCode(group), Utilities.parseGroupName(group));
        if(workflowAdminGroup!=null) {
        	backdoorForm.setIsWorkflowAdmin(KIMServiceLocator.getIdentityManagementService().isMemberOfGroup(getUserSession(request).getPrincipalId(), workflowAdminGroup.getGroupId()));
        }
    }

    public ActionMessages establishRequiredState(HttpServletRequest request, ActionForm form) throws Exception {
    	BackdoorForm backdoorForm = (BackdoorForm) form;

    	// default to true if not defined
    	Boolean showBackdoorLogin = new Boolean(Utilities.getKNSParameterBooleanValue(KEWConstants.DEFAULT_KIM_NAMESPACE, KNSConstants.DetailTypes.BACKDOOR_DETAIL_TYPE, KEWConstants.SHOW_BACK_DOOR_LOGIN_IND));

        backdoorForm.setShowBackdoorLogin(showBackdoorLogin);
        setFormGroupPermission(backdoorForm, request);
        if (backdoorForm.getGraphic() != null) {
        	request.getSession().setAttribute("showGraphic", backdoorForm.getGraphic());
        }
        return null;
    }
}