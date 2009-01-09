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
package org.kuali.rice.kew.actions;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.MDC;
import org.kuali.rice.kew.actionrequest.ActionRequestValue;
import org.kuali.rice.kew.actiontaken.ActionTakenValue;
import org.kuali.rice.kew.exception.InvalidActionTakenException;
import org.kuali.rice.kew.exception.ResourceUnavailableException;
import org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue;
import org.kuali.rice.kew.service.KEWServiceLocator;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kew.util.Utilities;
import org.kuali.rice.kim.bo.entity.KimPrincipal;


/**
 * CompleteAction records and process a complete action
 *
 * The routeheader is first checked to make sure the action is valid for the document.
 * Next the user is checked to make sure he/she has not taken a previous action on this
 * document at the actions responsibility or below. The action is recorded. Any requests
 * related to this user are deactivated.
 *
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 */
public class CompleteAction extends ActionTakenEvent {
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(CompleteAction.class);

    /**
     * @param rh
     *            RouteHeader for the document upon which the action is taken.
     * @param user
     *            User taking the action.
     */
    public CompleteAction(DocumentRouteHeaderValue rh, KimPrincipal principal) {
        super(KEWConstants.ACTION_TAKEN_COMPLETED_CD, rh, principal);
    }

    /**
     * @param rh
     *            RouteHeader for the document upon which the action is taken.
     * @param user
     *            User taking the action.
     * @param annotation
     *            User comment on the action taken
     */
    public CompleteAction(DocumentRouteHeaderValue rh, KimPrincipal principal, String annotation) {
        super(KEWConstants.ACTION_TAKEN_COMPLETED_CD, rh, principal, annotation);
    }

    /* (non-Javadoc)
     * @see org.kuali.rice.kew.actions.ActionTakenEvent#isActionCompatibleRequest(java.util.List)
     */
    @Override
    public String validateActionRules() {
        return validateActionRules(getActionRequestService().findAllValidRequests(getPrincipal().getPrincipalId(), routeHeader.getRouteHeaderId(), KEWConstants.ACTION_REQUEST_COMPLETE_REQ));
    }

    private String validateActionRules(List<ActionRequestValue> actionRequests) {
        if (!getRouteHeader().isValidActionToTake(getActionPerformedCode())) {
            return "Document is not in a state to be completed";
        }
        if (!isActionCompatibleRequest(actionRequests)) {
            return "No request for the user is compatible " + "with the COMPLETE action";
        }
        return "";
    }

    /* (non-Javadoc)
     * @see org.kuali.rice.kew.actions.ActionTakenEvent#isActionCompatibleRequest(java.util.List)
     */
    @Override
    public boolean isActionCompatibleRequest(List requests) {
        // we allow pre-approval
        if (requests.isEmpty()) {
            return true;
        }

        // can always cancel saved or initiated document
        if (routeHeader.isStateInitiated() || routeHeader.isStateSaved()) {
            return true;
        }

        boolean actionCompatible = false;
        Iterator ars = requests.iterator();
        ActionRequestValue actionRequest = null;

        while (ars.hasNext()) {
            actionRequest = (ActionRequestValue) ars.next();
            String request = actionRequest.getActionRequested();

            // Complete action matches Complete, Approve, FYI, and ACK requests
            if ( (KEWConstants.ACTION_REQUEST_FYI_REQ.equals(request)) ||
                    (KEWConstants.ACTION_REQUEST_ACKNOWLEDGE_REQ.equals(request)) ||
                    (KEWConstants.ACTION_REQUEST_APPROVE_REQ.equals(request)) ||
                    (KEWConstants.ACTION_REQUEST_COMPLETE_REQ.equals(request)) ) {
                actionCompatible = true;
                break;
            }
        }
        return actionCompatible;
    }

    /**
     * Records the complete action. - Checks to make sure the document status allows the action. - Checks that the user has not taken a previous action. - Deactivates the pending requests for this user - Records the action
     *
     * @throws InvalidActionTakenException
     * @throws ResourceUnavailableException
     */
    public void recordAction() throws org.kuali.rice.kew.exception.InvalidActionTakenException {
        MDC.put("docId", getRouteHeader().getRouteHeaderId());
        updateSearchableAttributesIfPossible();
        LOG.debug("Completing document : " + annotation);

        List actionRequests = getActionRequestService().findAllValidRequests(getPrincipal().getPrincipalId(), getRouteHeaderId(), KEWConstants.ACTION_REQUEST_COMPLETE_REQ);
        LOG.debug("Checking to see if the action is legal");
        String errorMessage = validateActionRules(actionRequests);
        if (!Utilities.isEmpty(errorMessage)) {
            throw new InvalidActionTakenException(errorMessage);
        }

        LOG.debug("Record the complete action");
        ActionTakenValue actionTaken = saveActionTaken(findDelegatorForActionRequests(actionRequests));

        LOG.debug("Deactivate all pending action requests");
        getActionRequestService().deactivateRequests(actionTaken, actionRequests);
        notifyActionTaken(actionTaken);

        boolean isException = getRouteHeader().isInException();
        boolean isSaved = getRouteHeader().isStateSaved();
        if (isException || isSaved) {
            String oldStatus = getRouteHeader().getDocRouteStatus();
            LOG.debug("Moving document back to Enroute from "+KEWConstants.DOCUMENT_STATUSES.get(oldStatus));
            getRouteHeader().markDocumentEnroute();
            String newStatus = getRouteHeader().getDocRouteStatus();
            notifyStatusChange(newStatus, oldStatus);
            KEWServiceLocator.getRouteHeaderService().saveRouteHeader(getRouteHeader());
        }
    }

}