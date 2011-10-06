package org.kuali.rice.kew.impl.peopleflow;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.rice.core.api.config.ConfigurationException;
import org.kuali.rice.kew.actionrequest.ActionRequestFactory;
import org.kuali.rice.kew.actionrequest.ActionRequestValue;
import org.kuali.rice.kew.actionrequest.KimGroupRecipient;
import org.kuali.rice.kew.actionrequest.KimPrincipalRecipient;
import org.kuali.rice.kew.actionrequest.Recipient;
import org.kuali.rice.kew.api.action.ActionRequestType;
import org.kuali.rice.kew.api.peopleflow.MemberType;
import org.kuali.rice.kew.api.peopleflow.PeopleFlowDefinition;
import org.kuali.rice.kew.api.peopleflow.PeopleFlowDelegate;
import org.kuali.rice.kew.api.peopleflow.PeopleFlowMember;
import org.kuali.rice.kew.engine.RouteContext;

import java.util.List;

/**
 * Reference implementation of the {@code PeopleFlowRequestGenerator} which is responsible for generating Action
 * Requests from a {@link PeopleFlowDefinition}.
 *
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class PeopleFlowRequestGeneratorImpl implements PeopleFlowRequestGenerator {

    @Override
    public List<ActionRequestValue> generateRequests(RouteContext routeContext, PeopleFlowDefinition peopleFlow, ActionRequestType actionRequested) {
        Context context = new Context(routeContext, peopleFlow, actionRequested);
        for (PeopleFlowMember member : peopleFlow.getMembers()) {
            generateRequestForMember(context, member);
        }
        return context.getActionRequestFactory().getRequestGraphs();
    }

    protected void generateRequestForMember(Context context, PeopleFlowMember member) {
        String actionRequestPolicyCode = null;
        if (member.getActionRequestPolicy() != null) {
            member.getActionRequestPolicy().getCode();
        }
        if (MemberType.ROLE == member.getMemberType()) {
            generateRequestForRoleMember(context, member);
        } else {
            ActionRequestValue actionRequest = context.getActionRequestFactory().addRootActionRequest(
                    context.getActionRequested().getCode(), member.getPriority(), toRecipient(member), "",
                    member.getResponsibilityId(), Boolean.TRUE, actionRequestPolicyCode, null);
            if (CollectionUtils.isNotEmpty(member.getDelegates())) {
                for (PeopleFlowDelegate delegate : member.getDelegates()) {
                    context.getActionRequestFactory().addDelegationRequest(actionRequest, toRecipient(delegate),
                            delegate.getResponsibilityId(), Boolean.TRUE, delegate.getDelegationType().getCode(), "", null);
                }
            }
        }
    }

    protected void generateRequestForRoleMember(Context context, PeopleFlowMember member) {
        // TODO...
        throw new UnsupportedOperationException("implement me!");
    }

    private Recipient toRecipient(PeopleFlowMember member) {
        Recipient recipient;
        if (MemberType.PRINCIPAL == member.getMemberType()) {
            recipient = new KimPrincipalRecipient(member.getMemberId());
        } else if (MemberType.GROUP == member.getMemberType()) {
            recipient = new KimGroupRecipient(member.getMemberId());
        } else {
            throw new IllegalStateException("encountered a member type which I did not understand: " + member.getMemberType());
        }
        return recipient;
    }

    private Recipient toRecipient(PeopleFlowDelegate delegate) {
        Recipient recipient;
        if (MemberType.PRINCIPAL == delegate.getMemberType()) {
            recipient = new KimPrincipalRecipient(delegate.getMemberId());
        } else if (MemberType.GROUP == delegate.getMemberType()) {
            recipient = new KimGroupRecipient(delegate.getMemberId());
        } else {
            throw new IllegalStateException("encountered a delegate member type which I did not understand: " + delegate.getMemberType());
        }
        return recipient;
    }

    /**
     * A simple class used to hold context during the PeopleFlow action request generation process.  Construction of
     * the context will validate that the given values are valid, non-null values where appropriate.
     */
    static final class Context {

        private final RouteContext routeContext;
        private final PeopleFlowDefinition peopleFlow;
        private final ActionRequestType actionRequested;
        private final ActionRequestFactory actionRequestFactory;

        Context(RouteContext routeContext, PeopleFlowDefinition peopleFlow, ActionRequestType actionRequested) {
            if (routeContext == null) {
                throw new IllegalArgumentException("routeContext was null");
            }
            if (peopleFlow == null) {
                throw new IllegalArgumentException("peopleFlow was null");
            }
            if (!peopleFlow.isActive()) {
                throw new ConfigurationException("Attempted to route to a PeopleFlow that is not active! " + peopleFlow);
            }
            if (actionRequested == null) {
                actionRequested = ActionRequestType.APPROVE;
            }
            this.routeContext = routeContext;
            this.peopleFlow = peopleFlow;
            this.actionRequested = actionRequested;
            this.actionRequestFactory = new ActionRequestFactory(routeContext);
        }

        RouteContext getRouteContext() {
            return routeContext;
        }

        PeopleFlowDefinition getPeopleFlow() {
            return peopleFlow;
        }

        ActionRequestType getActionRequested() {
            return actionRequested;
        }

        ActionRequestFactory getActionRequestFactory() {
            return actionRequestFactory;
        }

    }
}
