package com.amazon.deecomms.calling.controller;

import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.statemachine.StateMachine;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.core.CommsDaggerWrapper;
/* loaded from: classes12.dex */
public class CallViewStateMachine extends StateMachine<State, Event> {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CallViewStateMachine.class);

    /* loaded from: classes12.dex */
    public enum Event {
        OnUnbound,
        OnOutgoingInvitationSent,
        OnIncomingInvitationReceived,
        OnInvitationAccepted,
        OnInvitationRejected,
        OnDismissCallComplete,
        OnCallDisconnected
    }

    /* loaded from: classes12.dex */
    public enum State {
        Unbound,
        OutgoingCallView,
        IncomingCallView,
        CallEndedView,
        ActiveCallView
    }

    public CallViewStateMachine() {
        super(State.Unbound, new StateMachine.Configuration().withMetrics(CommsDaggerWrapper.getComponent().getMetricsManager(), CallViewStateMachine.class.getSimpleName()));
        StateMachine.TransitionBuilder transitionBuilder = new StateMachine.TransitionBuilder(this);
        transitionBuilder.at(State.Unbound).when(Event.OnOutgoingInvitationSent).go(State.OutgoingCallView).when(Event.OnIncomingInvitationReceived).go(State.IncomingCallView).when(Event.OnInvitationAccepted).go(State.ActiveCallView).when(Event.OnInvitationRejected).ignore().build();
        transitionBuilder.at(State.OutgoingCallView).when(Event.OnInvitationAccepted).go(State.ActiveCallView).when(Event.OnInvitationRejected).go(State.CallEndedView).when(Event.OnUnbound).go(State.Unbound).when(Event.OnOutgoingInvitationSent).ignore().build();
        transitionBuilder.at(State.IncomingCallView).when(Event.OnInvitationAccepted).go(State.ActiveCallView).when(Event.OnInvitationRejected).go(State.CallEndedView).when(Event.OnUnbound).go(State.Unbound).build();
        transitionBuilder.at(State.ActiveCallView).when(Event.OnCallDisconnected).go(State.CallEndedView).when(Event.OnUnbound).go(State.Unbound).when(Event.OnInvitationRejected).ignore().when(Event.OnInvitationAccepted).ignore().build();
        transitionBuilder.at(State.CallEndedView).when(Event.OnOutgoingInvitationSent).go(State.OutgoingCallView).when(Event.OnIncomingInvitationReceived).go(State.IncomingCallView).when(Event.OnInvitationRejected).ignore().when(Event.OnInvitationAccepted).ignore().when(Event.OnCallDisconnected).ignore().when(Event.OnUnbound).go(State.Unbound).build();
    }

    @Override // com.amazon.comms.statemachine.StateMachine
    protected CommsLogger getLogger() {
        return LOG;
    }
}
