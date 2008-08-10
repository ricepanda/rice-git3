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
package org.kuali.rice.kew.workgroup.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.kuali.rice.core.resourceloader.GlobalResourceLoader;
import org.kuali.rice.kew.KEWServiceLocator;
import org.kuali.rice.kew.WorkflowServiceErrorException;
import org.kuali.rice.kew.WorkflowServiceErrorImpl;
import org.kuali.rice.kew.attribute.Extension;
import org.kuali.rice.kew.attribute.ExtensionAttribute;
import org.kuali.rice.kew.attribute.ExtensionData;
import org.kuali.rice.kew.attribute.web.WebExtensions;
import org.kuali.rice.kew.clientapp.IDocHandler;
import org.kuali.rice.kew.clientapp.WorkflowDocument;
import org.kuali.rice.kew.dto.WorkflowIdDTO;
import org.kuali.rice.kew.exception.KEWUserNotFoundException;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kew.exception.WorkflowRuntimeException;
import org.kuali.rice.kew.export.ExportDataSet;
import org.kuali.rice.kew.export.ExportFormat;
import org.kuali.rice.kew.lookupable.Field;
import org.kuali.rice.kew.lookupable.Row;
import org.kuali.rice.kew.lookupable.WorkflowLookupable;
import org.kuali.rice.kew.routeheader.Routable;
import org.kuali.rice.kew.user.AuthenticationUserId;
import org.kuali.rice.kew.user.Recipient;
import org.kuali.rice.kew.user.WorkflowUser;
import org.kuali.rice.kew.user.WorkflowUserId;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kew.util.Utilities;
import org.kuali.rice.kew.validation.ValidationContext;
import org.kuali.rice.kew.validation.ValidationResults;
import org.kuali.rice.kew.web.WorkflowAction;
import org.kuali.rice.kew.workgroup.BaseWorkgroup;
import org.kuali.rice.kew.workgroup.BaseWorkgroupExtension;
import org.kuali.rice.kew.workgroup.BaseWorkgroupExtensionData;
import org.kuali.rice.kew.workgroup.GroupNameId;
import org.kuali.rice.kew.workgroup.WorkflowGroupId;
import org.kuali.rice.kew.workgroup.Workgroup;
import org.kuali.rice.kew.workgroup.WorkgroupRoutingService;
import org.kuali.rice.kew.workgroup.WorkgroupService;
import org.kuali.rice.kew.workgroup.WorkgroupType;
import org.kuali.rice.kew.workgroup.WorkgroupTypeAttribute;
import org.kuali.rice.kew.workgroup.web.WorkgroupForm.WorkgroupMember;


/**
 * A Struts Action which provides for creation, editing, and reporting on
 * {@link Workgroup}s.
 *
 * @see WorkgroupService
 *
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 */
public class WorkgroupAction extends WorkflowAction {

    protected final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(getClass());

    public ActionForward start(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WorkgroupForm workgroupForm = (WorkgroupForm) form;
        if (workgroupForm.getWorkgroup().getActiveInd() == null) {
        	workgroupForm.getWorkgroup().setActiveInd(Boolean.TRUE);
        }
        if (workgroupForm.getWorkflowDocument() == null) {
            workgroupForm.setWorkflowDocument(getWorkgroupRoutingService().createWorkgroupDocument(getUserSession(request), workgroupForm.getWorkgroupType()));
            workgroupForm.setDocId(workgroupForm.getWorkflowDocument().getRouteHeaderId());
            workgroupForm.establishVisibleActionRequestCds();
        }
        return mapping.findForward("basic");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	WorkgroupForm workgroupForm = (WorkgroupForm) form;
    	// clear out any incoming members so that they don't appear twice
    	workgroupForm.getWorkgroupMembers().clear();
    	workgroupForm.setWorkgroupType(workgroupForm.getExistingWorkgroupType());
        start(mapping, form, request, response);
        Workgroup existingWorkgroup = workgroupForm.getExistingWorkgroup();
        WorkgroupType existingWorkgroupType = workgroupForm.getExistingWorkgroupType();
        WebExtensions existingExtensions = workgroupForm.getExistingExtensions();
        if (existingWorkgroup != null) {
        	Long lockedDocumentId = getWorkgroupRoutingService().getLockingDocumentId(existingWorkgroup.getWorkflowGroupId());
        	if (lockedDocumentId != null && lockedDocumentId.longValue() > 0) {
        		ActionMessages messages = new ActionMessages();
        		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("workgroup.WorkgroupService.workgroupInRoute", "document " + lockedDocumentId));
        		saveMessages(request, messages);
        		workgroupForm.setWorkgroup(existingWorkgroup);
        		workgroupForm.setWorkgroupType(existingWorkgroupType);
        		workgroupForm.setExtensions(existingExtensions);
        		workgroupForm.setExistingWorkgroup(null);
        		workgroupForm.setExistingWorkgroupType(null);
        		workgroupForm.setExistingExtensions(null);
        		workgroupForm.setExistingWorkgroupMembers(null);
        		return mapping.findForward("summary");
        	}
        }
        // we are going to set the workgroup being edited as the existing workgroup, it is important to
        // note here that we are not making any changes to the existing workgroup (it could be an in-memory cached
        // version and should therefore be unmodifiable!  Instead it will effectively be
        // copied by the web tier and the form processing
        workgroupForm.setWorkgroup(existingWorkgroup);
        workgroupForm.setWorkgroupType(existingWorkgroupType);
        workgroupForm.setExtensions(existingExtensions);
        workgroupForm.loadWebWorkgroupValues();
        //initializeExtensions(workgroupForm);
        return mapping.findForward("basic");
    }

    public ActionForward copy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WorkgroupForm workgroupForm = (WorkgroupForm) form;
        workgroupForm.getWorkgroup().setWorkflowGroupId(null);
        workgroupForm.setWorkgroupId(null);
        workgroupForm.setExistingWorkgroup(null);
        workgroupForm.setExistingWorkgroupType(null);
        workgroupForm.setExistingExtensions(null);
        workgroupForm.setExistingWorkgroupMembers(null);
        workgroupForm.setAnnotation(null);
        loadWorkgroupType(workgroupForm);
        initializeExtensions(workgroupForm);
        return start(mapping, form, request, response);
    }

    public ActionForward addMember(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WorkgroupForm workgroupForm = (WorkgroupForm) form;
        if (workgroupForm.getWorkgroupMember() != null && !"".equals(workgroupForm.getWorkgroupMember())) {
            List errors = new ArrayList();
            if (KEWConstants.ACTION_REQUEST_USER_RECIPIENT_CD.equals(workgroupForm.getMemberType())) {
            	try {
            		WorkflowUser user = KEWServiceLocator.getUserService().getWorkflowUser(new AuthenticationUserId(workgroupForm.getWorkgroupMember()));
            		workgroupForm.getWorkgroup().getMembers().add(user);
            		workgroupForm.getWorkgroupMembers().add(new WorkgroupMember(user));
            	} catch (KEWUserNotFoundException e) {
            		LOG.warn("User " + workgroupForm.getWorkgroupMember() + " is invalid");
            		errors.add(new WorkflowServiceErrorImpl("User is invalid", "user.userservice.id.invalid"));
            	}
            } else if (KEWConstants.ACTION_REQUEST_WORKGROUP_RECIPIENT_CD.equals(workgroupForm.getMemberType())) {
            	Workgroup workgroup = KEWServiceLocator.getWorkgroupService().getWorkgroup(new GroupNameId(workgroupForm.getWorkgroupMember()));
            	if (workgroup == null) {
            		errors.add(new WorkflowServiceErrorImpl("Workgroup is invalid", "workgroup.WorkgroupService.name.invalid"));
            	} else {
            		workgroupForm.getWorkgroup().getMembers().add(workgroup);
            		workgroupForm.getWorkgroupMembers().add(new WorkgroupMember(workgroup));
            	}
            }
            if (!errors.isEmpty()) {
        		throw new WorkflowServiceErrorException("Error occurred when adding a member", errors);
            }
            workgroupForm.setWorkgroupMember(null);
        }
        return mapping.findForward("basic");
    }

    public ActionForward removeMember(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WorkgroupForm workgroupForm = (WorkgroupForm) form;
        int removeIndex = new Integer(workgroupForm.getRemovedMember()).intValue();
        workgroupForm.getWorkgroup().getMembers().remove(removeIndex);
        workgroupForm.getWorkgroupMembers().remove(removeIndex);
        workgroupForm.setRemovedMember(null);
        return mapping.findForward("basic");
    }

    public ActionForward route(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WorkgroupForm workgroupForm = (WorkgroupForm) form;
        establishFinalExtensionData(workgroupForm, createValidationContext(workgroupForm, request));
        getWorkgroupRoutingService().route(workgroupForm.getWorkgroup(), getUserSession(request), workgroupForm.getAnnotation());
        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("general.routing.routed", "Workgroup Document"));
        saveMessages(request, messages);
        return mapping.findForward("summary");
    }

    /**
     * The validation context simply includes the parameters that were submitted with the form.
     */
    private ValidationContext createValidationContext(WorkgroupForm workgroupForm, HttpServletRequest request) {
    	ValidationContext context = new ValidationContext();
    	// extract the extension values and add those as parameters
    	context.getParameters().put("extensions", workgroupForm.getExtensions().getData());
    	context.getParameters().put("operation", "entry");
    	return context;
    }

    private void establishFinalExtensionData(WorkgroupForm workgroupForm, ValidationContext validationContext) {
    	List<Object> errors = new ArrayList<Object>();
    	WebExtensions extensions = workgroupForm.getExtensions();
    	Workgroup workgroup = workgroupForm.getWorkgroup();
    	WorkgroupType workgroupType = workgroupForm.getWorkgroupType();
    	if (workgroupType != null) {
    		for (WorkgroupTypeAttribute workgroupTypeAttribute : workgroupForm.getWorkgroupType().getActiveAttributes()) {
    			Object attribute = workgroupTypeAttribute.loadAttribute();
    			if (attribute instanceof ExtensionAttribute) {
    				BaseWorkgroupExtension extension = new BaseWorkgroupExtension();
    				extension.setWorkgroup((BaseWorkgroup)workgroup);
    				extension.setWorkgroupTypeAttribute(workgroupTypeAttribute);
    				ExtensionAttribute collectionAttribute = (ExtensionAttribute)attribute;
    				ValidationResults results = collectionAttribute.validate(validationContext);
    				if (results != null && !results.getValidationResults().isEmpty()) {
    					errors.add(results);
    				}
    				for (Row row : collectionAttribute.getRows()) {
    					for (Field field : row.getFields()) {
    						if (extensions.getData().containsKey(field.getPropertyName())) {
    							String fieldValue = extensions.getData().get(field.getPropertyName());
    							BaseWorkgroupExtensionData extensionData = new BaseWorkgroupExtensionData();
    							extensionData.setKey(field.getPropertyName());
    							extensionData.setValue(fieldValue);
    							extensionData.setWorkgroupExtension(extension);
    							extension.getData().add(extensionData);
    						}
    					}
    				}
    				if (!extension.getData().isEmpty()) {
    					workgroup.getExtensions().add(extension);
    				}
    			}
    		}
    	}
    	if (!errors.isEmpty()) {
    		throw new WorkflowServiceErrorException("Workgroup extension errors", errors);
    	}
    }

    public ActionForward clear(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WorkgroupForm workgroupForm = (WorkgroupForm) form;
        if (workgroupForm.getShowEdit().equals("yes")) {
            workgroupForm.setWorkgroup(workgroupForm.getExistingWorkgroup());
            workgroupForm.setWorkgroupType(workgroupForm.getExistingWorkgroupType());
            workgroupForm.setWorkgroupName(workgroupForm.getWorkgroup().getGroupNameId().getNameId());
            workgroupForm.setWorkgroupMember(null);
            workgroupForm.setAnnotation(null);

        } else {
            workgroupForm.setWorkgroup(getWorkgroupService().getBlankWorkgroup());
            workgroupForm.setWorkgroupName("");
            workgroupForm.getWorkgroup().setActiveInd(new Boolean(true));
            workgroupForm.setWorkgroupMember(null);
            workgroupForm.setAnnotation(null);
            workgroupForm.getExtensions().clear();
            workgroupForm.setExistingWorkgroup(null);
            workgroupForm.setExistingWorkgroupType(null);
            workgroupForm.setExistingWorkgroupMembers(null);
        }
        return mapping.findForward("basic");
    }

    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WorkgroupForm workgroupForm = (WorkgroupForm) form;
        if (IDocHandler.INITIATE_COMMAND.equalsIgnoreCase(workgroupForm.getCommand())) {
            return start(mapping, form, request, response);
        } else {
            if (workgroupForm.getWorkflowDocument().stateIsInitiated() && workgroupForm.getWorkflowDocument().getRouteHeader().getInitiator().getWorkflowId().equals(getUserSession(request).getWorkflowUser().getWorkflowId())) {
                return start(mapping, form, request, response);
            }
            //workgroupForm.getWorkgroup().setRouteHeaderId(workgroupForm.getWorkflowDocument().getRouteHeaderId());
            Workgroup workgroup = getWorkgroupRoutingService().findByDocumentId(workgroupForm.getDocId());
            Routable routableWorkgroup = validateWorkgroupIsRoutable(workgroup);
            if (workgroup != null) {
                if (!routableWorkgroup.getCurrentInd().booleanValue()) {
                	// the existing workgroup will be the current workgroup from the cache
                    workgroupForm.setExistingWorkgroup(getWorkgroupService().getWorkgroup(workgroup.getWorkflowGroupId()));
                    loadWorkgroupType(workgroupForm);
                    initializeExtensions(workgroupForm.getExistingExtensions(), workgroupForm.getExistingWorkgroup(), workgroupForm.getExistingWorkgroupType());
                }
                workgroupForm.setWorkgroup(workgroup);
                workgroupForm.setWorkgroupName(workgroup.getGroupNameId().getNameId());
                workgroupForm.setWorkgroupId(workgroup.getWorkflowGroupId().getGroupId());
            }
            workgroupForm.loadWebWorkgroupValues();
            loadWorkgroupType(workgroupForm);
            initializeExtensions(workgroupForm);
            initializeExistingWorkgroupMembers(workgroupForm);
            return mapping.findForward("docHandler");
        }
    }

    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WorkgroupForm workgroupForm = (WorkgroupForm) form;
        if (request.getParameter("networkId") != null && !request.getParameter("networkId").equals("")) {
            workgroupForm.setWorkgroupMember(request.getParameter("networkId"));
        }

        return mapping.findForward("basic");
    }

    public ActionMessages establishRequiredState(HttpServletRequest request, ActionForm form) throws Exception {

    	request.getSession().getServletContext().setAttribute("WorkgroupCaps", KEWServiceLocator.getWorkgroupService().getCapabilities());

        WorkgroupForm workgroupForm = (WorkgroupForm) form;

        if (workgroupForm.getMethodToCall().equals("docHandler") || workgroupForm.getMethodToCall().equals("report")) {
        	workgroupForm.setReadOnly(true);
        }

        if (workgroupForm.getWorkgroupId() != null && workgroupForm.getWorkgroupId().longValue() > 0) {
            Workgroup existingWorkgroup = getWorkgroupService().getWorkgroup(new WorkflowGroupId(workgroupForm.getWorkgroupId()));
            workgroupForm.setExistingWorkgroup(existingWorkgroup);
        }

        workgroupForm.setWorkgroupTypes(KEWServiceLocator.getWorkgroupTypeService().findAllActive());
        workgroupForm.getWorkgroupTypes().add(0, WorkgroupForm.createDefaultWorkgroupType());
        loadWorkgroupType(workgroupForm);

        if (workgroupForm.getDocId() != null) {
            workgroupForm.setWorkflowDocument(new WorkflowDocument(new WorkflowIdDTO(getUserSession(request).getWorkflowUser().getWorkflowId()), workgroupForm.getDocId()));
        }

        boolean typeChanged = workgroupForm.getCurrentWorkgroupType() != null && !ObjectUtils.equals(workgroupForm.getCurrentWorkgroupType(), workgroupForm.getWorkgroup().getWorkgroupType());
        if (!workgroupForm.isReadOnly() && typeChanged && workgroupForm.getWorkflowDocument() != null) {
        	workgroupForm.getWorkflowDocument().cancel("Workgroup Type was changed from " + renderWorkgroupTypeName(workgroupForm.getCurrentWorkgroupType()) + " to " + renderWorkgroupTypeName(workgroupForm.getWorkgroup().getWorkgroupType()));
            workgroupForm.setWorkflowDocument(getWorkgroupRoutingService().createWorkgroupDocument(getUserSession(request), workgroupForm.getWorkgroupType()));
            workgroupForm.setDocId(workgroupForm.getWorkflowDocument().getRouteHeaderId());
            workgroupForm.establishVisibleActionRequestCds();
        }
        workgroupForm.setCurrentWorkgroupType(workgroupForm.getWorkgroup().getWorkgroupType());
        // set up the workgroup name and id on the workgroup from the form
        Workgroup workgroup = workgroupForm.getWorkgroup();
        workgroup.setWorkflowGroupId(workgroupForm.getWorkgroupId() == null ? null : new WorkflowGroupId(workgroupForm.getWorkgroupId()));
        workgroup.setGroupNameId(workgroupForm.getWorkgroupName() == null ? null : new GroupNameId(workgroupForm.getWorkgroupName()));
        // setup the workgroup's members
        workgroup.getMembers().clear();
        for (Iterator iterator = workgroupForm.getWorkgroupMembers().iterator(); iterator.hasNext();) {
			WorkgroupMember member = (WorkgroupMember) iterator.next();
			if (member.getMemberType().equals(KEWConstants.ACTION_REQUEST_USER_RECIPIENT_CD)) {
				workgroup.getMembers().add(KEWServiceLocator.getUserService().getWorkflowUser(new WorkflowUserId(member.getWorkflowId())));
			} else if (member.getMemberType().equals(KEWConstants.ACTION_REQUEST_WORKGROUP_RECIPIENT_CD)) {
				workgroup.getMembers().add(KEWServiceLocator.getWorkgroupService().getWorkgroup(new WorkflowGroupId(Long.parseLong(member.getWorkflowId()))));
			}
		}

        if (workgroupForm.getDocId() != null) {
            workgroupForm.establishVisibleActionRequestCds();
            if (workgroupForm.isRoutable()) {
            	workgroupForm.getRoutableWorkgroup().setDocumentId(workgroupForm.getDocId());
            }
        }
        // determine if blanket approve is cool
        if (workgroupForm.getWorkflowDocument() != null) {
        	workgroupForm.setShowBlanketApproveButton(workgroupForm.getWorkflowDocument().isBlanketApproveCapable());
        }

        initializeExtensions(workgroupForm);
        if (workgroupForm.getExistingWorkgroup() != null) {
        	initializeExtensions(workgroupForm.getExistingExtensions(), workgroupForm.getExistingWorkgroup(), workgroupForm.getExistingWorkgroupType());
        	initializeExistingWorkgroupMembers(workgroupForm);
        }
        return null;
    }



    @Override
	public ActionMessages establishFinalState(HttpServletRequest request, ActionForm form) throws Exception {
    	WorkgroupForm workgroupForm = (WorkgroupForm)form;
    	// we only want to be able to change the workgroup type if the doc is initated (the workgroup type specifies the doc type)
    	if (workgroupForm.getWorkflowDocument() != null) {
    		workgroupForm.setWorkgroupTypeEditable(workgroupForm.getWorkflowDocument().stateIsInitiated());
    	}
        return null;
	}

    private String renderWorkgroupTypeName(String workgroupTypeName) {
    	if (StringUtils.isEmpty(workgroupTypeName)) {
    		return "Default";
    	}
    	return workgroupTypeName;
    }

	private void loadWorkgroupType(WorkgroupForm workgroupForm) {
    	String workgroupTypeCode = workgroupForm.getWorkgroup().getWorkgroupType();
    	WorkgroupType workgroupType = null;
    	if (!isDefaultWorkgroupType(workgroupTypeCode)) {
    		workgroupType = KEWServiceLocator.getWorkgroupTypeService().findByName(workgroupTypeCode);
    		if (workgroupType == null) {
    			throw new WorkflowRuntimeException("Failed to locate workgroup type with the given code '" + workgroupTypeCode + "'");
    		}
    	}
    	workgroupForm.setWorkgroupType(workgroupType);

    	if (workgroupForm.getExistingWorkgroup() != null) {
    		WorkgroupType existingType = null;
    		if (!isDefaultWorkgroupType(workgroupForm.getExistingWorkgroup().getWorkgroupType())) {
    			existingType = KEWServiceLocator.getWorkgroupTypeService().findByName(workgroupForm.getExistingWorkgroup().getWorkgroupType());
    			if (existingType == null) {
        			throw new WorkflowRuntimeException("Failed to locate workgroup type with the given code '" + workgroupForm.getExistingWorkgroup().getWorkgroupType() + "'");
        		}
    		}
    		workgroupForm.setExistingWorkgroupType(existingType);
    	}

    }

	private void initializeExtensions(WebExtensions extensions, Workgroup workgroup, WorkgroupType workgroupType) {
		if (workgroup == null) {
			return;
		}
		// clear the rows out for the case when we are coming back from a lookup and the form was restored from session
		extensions.clearRows();
    	if (workgroupType != null) {
    		for (WorkgroupTypeAttribute workgroupTypeAttribute : workgroupType.getActiveAttributes()) {
    			Object attribute = workgroupTypeAttribute.loadAttribute();
    			if (attribute instanceof ExtensionAttribute) {
    				ExtensionAttribute collectionAttribute = (ExtensionAttribute)attribute;
    				extensions.load(collectionAttribute);
    			}
    		}
    	}
    	// copy workgroup extension data over
    	for (Extension extension : workgroup.getExtensions()) {
    		for (ExtensionData data : extension.getData()) {
    			if (extensions.getData().containsKey(data.getKey())) {
    				extensions.getData().put(data.getKey(), data.getValue());
    			}
    		}
    	}
    }

    private void initializeExtensions(WorkgroupForm workgroupForm) {
    	initializeExtensions(workgroupForm.getExtensions(), workgroupForm.getWorkgroup(), workgroupForm.getWorkgroupType());
    }

    private void initializeExistingWorkgroupMembers(WorkgroupForm workgroupForm) {
    	if (workgroupForm.getExistingWorkgroup() != null) {
    		List<WorkgroupMember> existingMembers = new ArrayList<WorkgroupMember>();
    		for (Recipient member : workgroupForm.getExistingWorkgroup().getMembers()) {
    			if (member instanceof WorkflowUser) {
    				WorkflowUser user = (WorkflowUser)member;
    				existingMembers.add(new WorkgroupMember(user));
    			} else if (member instanceof Workgroup) {
    				Workgroup nestedWorkgroup = (Workgroup)member;
    				existingMembers.add(new WorkgroupMember(nestedWorkgroup));
    			}
    		}
    		workgroupForm.setExistingWorkgroupMembers(existingMembers);
    	}
    }

    private boolean isDefaultWorkgroupType(String workgroupTypeCode) {
    	return StringUtils.isEmpty(workgroupTypeCode) || workgroupTypeCode.equals(KEWConstants.LEGACY_DEFAULT_WORKGROUP_TYPE);
    }

    public ActionForward report(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WorkgroupForm workgroupForm = (WorkgroupForm) form;
        // establish required state will load the workgroup which we desire into the existingWorkgroup property on the form
        workgroupForm.setWorkgroup(workgroupForm.getExistingWorkgroup());
        workgroupForm.loadWebWorkgroupValues();
        workgroupForm.setExistingWorkgroup(null);
        workgroupForm.setExistingWorkgroupType(null);
        workgroupForm.setExistingExtensions(null);
        workgroupForm.setExistingWorkgroupMembers(null);
        workgroupForm.setAnnotation(null);
        loadWorkgroupType(workgroupForm);
        initializeExtensions(workgroupForm);
        return mapping.findForward("report");
    }

    public ActionForward blanketApprove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOG.info("entering blanketApprove() method ...");
        WorkgroupForm workgroupForm = (WorkgroupForm) form;
        establishFinalExtensionData(workgroupForm, createValidationContext(workgroupForm, request));
        getWorkgroupRoutingService().blanketApprove(workgroupForm.getWorkgroup(), getUserSession(request), workgroupForm.getAnnotation());
        saveDocumentActionMessage("general.routing.blanketApproved", request);
        //workgroupForm.getWorkgroup().getMembersFromWorkgroupMembers();
        return mapping.findForward("summary");
    }

    private void saveDocumentActionMessage(String messageKey, HttpServletRequest request) {
        saveDocumentActionMessage(messageKey, request, null);
    }

    private void saveDocumentActionMessage(String messageKey, HttpServletRequest request, String secondMessageParameter) {
        ActionMessages messages = new ActionMessages();
        if (Utilities.isEmpty(secondMessageParameter)) {
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(messageKey, "document"));
        } else {
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(messageKey, "document", secondMessageParameter));
        }
        saveMessages(request, messages);
    }

    public ActionForward performLookup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WorkgroupForm workgroupForm = (WorkgroupForm) form;

        String lookupService = request.getParameter("lookupableImplServiceName");
        String conversionParameter = request.getParameter("conversionFields");
        StringBuffer conversionFields = new StringBuffer((conversionParameter != null) ? conversionParameter : "");
        if (lookupService.equals("memberLookup")) {
        	if (workgroupForm.getMemberType().equals(KEWConstants.ACTION_REQUEST_WORKGROUP_RECIPIENT_CD)) {
        		lookupService = "WorkGroupLookupableImplService";
        		conversionFields = new StringBuffer("workgroupName:workgroupMember,workgroupId:null");
        	} else if (workgroupForm.getMemberType().equals(KEWConstants.ACTION_REQUEST_USER_RECIPIENT_CD)) {
        		lookupService = "UserLookupableImplService";
        	}
        }
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + mapping.getModuleConfig().getPrefix();
        StringBuffer lookupUrl = new StringBuffer(basePath);
        lookupUrl.append("/Lookup.do?methodToCall=start&docFormKey=").append(getUserSession(request).addObject(form)).append("&lookupableImplServiceName=");
        lookupUrl.append(lookupService);

        String lookupType = workgroupForm.getLookupType();
        workgroupForm.setLookupType(null);
        if (lookupType != null && !lookupType.equals("")) {
            WorkflowLookupable workflowLookupable = (WorkflowLookupable) GlobalResourceLoader.getService(lookupService);// SpringServiceLocator.getExtensionService().getLookupable(request.getParameter("lookupableImplServiceName"));
            for (Iterator iterator = workflowLookupable.getDefaultReturnType().iterator(); iterator.hasNext();) {
                String returnType = (String) iterator.next();
                conversionFields.append(returnType).append(":").append(lookupType);
            }
        }

        lookupUrl.append("&conversionFields=").append(conversionFields);
        lookupUrl.append("&returnLocation=").append(basePath).append(mapping.getPath()).append(".do");
        return new ActionForward(lookupUrl.toString(), true);
    }

    public ActionForward export(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WorkgroupForm workgroupForm = (WorkgroupForm)form;
        Workgroup workgroup = workgroupForm.getExistingWorkgroup();
        ExportDataSet dataSet = new ExportDataSet(ExportFormat.XML);
        dataSet.getWorkgroups().add(workgroup);
        return exportDataSet(request, dataSet);
    }

    private Routable validateWorkgroupIsRoutable(Workgroup workgroup) throws WorkflowException {
    	if (!(workgroup instanceof Routable)) {
    		throw new WorkflowException("Workgroup instances must implement Routable in order to support document routing interfaces.");
    	}
    	return (Routable)workgroup;
    }

    private WorkgroupService getWorkgroupService() {
        return KEWServiceLocator.getWorkgroupService();
    }

    private WorkgroupRoutingService getWorkgroupRoutingService() {
    	return KEWServiceLocator.getWorkgroupRoutingService();
    }

}