package com.amazon.comms.calling.instrumentation;

import com.amazon.comms.instrumentation.EventTracer;
import com.google.common.collect.ImmutableSet;
/* loaded from: classes10.dex */
public final class EventTracerConfig {
    public static final String LOG_TAG = "CALLING_EVENT_TRACER";
    @Deprecated
    public static final ImmutableSet<String> METRICS_DOMAINS = ImmutableSet.of("com.amazon.comms.knighttachyon", "com.amazon.comms.headlesstachyon", "com.amazon.comms.rooktachyon", "com.amazon.dee.app", "com.amazon.dee.app.beta");
    public static final String METRICS_SOURCE = "CallingEventTracer";

    /* loaded from: classes10.dex */
    public enum Event {
        Caller_GuiCallRequestTapped,
        Caller_GuiDropinRequestTapped,
        Caller_GuiOutboundCallRequestSent,
        Caller_BeginCall_Directive,
        Caller_DropIn_BeginCall_Directive,
        Caller_SetRingTone_Directive,
        Caller_INVITE_Sent,
        Caller_Received_200OK,
        Caller_OutgoingCallView_Bound,
        Caller_OutgoingCallView_Unbound,
        Caller_ActiveCallView_Bound,
        Caller_ActiveCallView_Unbound,
        Caller_OutgoingCallTtsSpeechStarted,
        Caller_200OK_Received_DropIn,
        Caller_Received_Warmup,
        Caller_Deferred_BeginCall_Received,
        Caller_Deferred_BeginCall_Processed,
        Callee_INVITE_Received,
        Callee_DropIn_INVITE_Received,
        Callee_User_Answers,
        Callee_DropIn_Accepted,
        Callee_IncomingCallView_Bound,
        Callee_IncomingCallView_Unbound,
        Callee_ActiveCallView_Bound,
        Callee_ActiveCallView_Unbound,
        Callee_NotifySpeechletOfInvite,
        Callee_AcceptCall_Directive_DropIn,
        Callee_NotifySpeechletOfCallerId,
        Callee_Received_CallToken,
        EAC_AcceptCall,
        EAC_Redundant_AcceptCall,
        EAC_EarlyAcceptCall,
        EAC_Redundant_EarlyAcceptCall,
        EAC_INVITE,
        EAC_Other_INVITE,
        Webrtc_media_warmup_started,
        Webrtc_media_warmup_completed,
        Webrtc_create_peer_connection,
        Webrtc_create_peer_connection_internal,
        Webrtc_peer_connection_created,
        Webrtc_icegatheringstate_gathering,
        Webrtc_icegatheringstate_complete,
        Webrtc_session_connected,
        Callee_Send_180Ringing,
        Caller_Received_180Ringing,
        Callee_Send_200OK,
        Call_Confirmed,
        Call_Hangup_Sent,
        Call_Hangup,
        Call_Disconnected,
        Callee_Received_Ring_tts,
        Call_MediaStop,
        Playing_Outgoing_Ringback_Tone,
        Playing_Disconnect_Tone,
        Preparing_Tts_Url,
        Playing_First_Incoming_tts,
        Playing_Second_Incoming_tts,
        Gui_Answer_Call_OnClicked,
        Call_Accepted_Notify,
        Gui_EndCall_OnClicked,
        Gui_EndInvite_OnClicked,
        Gui_Audio_OnClicked,
        Call_Early_Media_Notify,
        Callee_Sip_Info_Received,
        Ui_Launched,
        Ui_Shown,
        EndOfUtteranceBeginCallStandard,
        EndOfUtteranceBeginCallDropIn,
        EndOfUtteranceAcceptCall,
        EndOfUtteranceEndCall,
        EndOfUtteranceDTMF,
        call_ice_disconnected,
        call_ice_connected,
        call_ice_reconnected,
        ice_connection_receiving,
        ice_connection_notreceiving,
        reconnect_initiator_network_down,
        reconnect_initiator_network_up,
        reconnect_initiator_signaling_shutdown,
        reconnect_initiator_signaling_init,
        reconnect_initiator_signaling_registered,
        reconnect_initiator_ice_restarted,
        reconnect_initiator_signaling_offer,
        reconnect_receiver_signaling_offered,
        reconnect_receiver_signaling_hangup,
        reconnect_receiver_ice_restarted,
        reconnect_receiver_signaling_answer,
        reconnect_initiator_signaling_answered,
        reconnect_initiator_timeout,
        reconnect_receiver_timeout,
        reconnect_simultaneous_signaling_reset,
        reconnect_simultaneous_signaling_ignore,
        reconnect_initiator_fail,
        reconnect_receiver_fail,
        reconnect_initiator_updatecall_directive_received,
        SendDTMF_Directive_Received,
        DTMF_Sent_To_Sip,
        DTMF_Sent_RFC2833,
        Sip_Info_Response_Received
    }

    /* loaded from: classes10.dex */
    public enum Interval implements EventTracer.Interval<Event> {
        Upl_TTRB(true, Event.EndOfUtteranceBeginCallStandard, Event.Playing_Outgoing_Ringback_Tone),
        Upl_DITTM(true, Event.EndOfUtteranceBeginCallDropIn, Event.Webrtc_session_connected),
        Upl_TTM(true, Event.EndOfUtteranceAcceptCall, Event.Webrtc_session_connected),
        Upl_TTE_Media(true, Event.EndOfUtteranceEndCall, Event.Call_MediaStop),
        CallerSendCallLeg(true, Event.Caller_BeginCall_Directive, Event.Caller_INVITE_Sent),
        CallerReceiveAnswerLeg(true, Event.Caller_Received_200OK, Event.Caller_ActiveCallView_Bound),
        CallerInviteSentTo200OKDropIn(true, Event.Caller_INVITE_Sent, Event.Caller_200OK_Received_DropIn),
        CallerDropInBeginCallToMedia(true, Event.Caller_DropIn_BeginCall_Directive, Event.Webrtc_session_connected),
        CalleeReceiveCallLeg(true, Event.Callee_INVITE_Received, Event.Callee_IncomingCallView_Bound),
        CalleeSendAnswerLeg(true, Event.Callee_User_Answers, Event.Callee_ActiveCallView_Bound),
        CalleeInboundCallReceivedToAcceptCall(true, Event.Callee_NotifySpeechletOfInvite, Event.Callee_AcceptCall_Directive_DropIn),
        CalleeAcceptCallToMediaFlowing(true, Event.Callee_AcceptCall_Directive_DropIn, Event.Webrtc_session_connected),
        CalleeAcceptCallToDropInViewBound(true, Event.Callee_AcceptCall_Directive_DropIn, Event.Callee_ActiveCallView_Bound),
        CalleeDropInInviteToMedia(true, Event.Callee_DropIn_INVITE_Received, Event.Webrtc_session_connected),
        CalleeDropInInviteTo200ok(true, Event.Callee_DropIn_INVITE_Received, Event.Callee_Send_200OK),
        Webrtc_media_warmup(true, Event.Webrtc_media_warmup_started, Event.Webrtc_media_warmup_completed),
        Webrtc_AV_Capabilities(true, Event.Webrtc_create_peer_connection, Event.Webrtc_create_peer_connection_internal),
        Camera_audio_init(true, Event.Webrtc_create_peer_connection_internal, Event.Webrtc_peer_connection_created),
        SDP(true, Event.Webrtc_peer_connection_created, Event.Webrtc_icegatheringstate_gathering),
        ICE(true, Event.Webrtc_icegatheringstate_gathering, Event.Webrtc_icegatheringstate_complete),
        CallLatency_D_J(true, Event.Caller_INVITE_Sent, Event.Caller_Received_180Ringing),
        CallLatency_D_J_EarlyMedia(true, Event.Caller_INVITE_Sent, Event.Call_Early_Media_Notify),
        CallLatency_F_G(true, Event.Callee_INVITE_Received, Event.Callee_NotifySpeechletOfInvite),
        CallLatency_F_I(true, Event.Callee_INVITE_Received, Event.Callee_Send_180Ringing),
        CallLatency_O_S(true, Event.Caller_Received_200OK, Event.Webrtc_session_connected),
        CallLatency_N_S(true, Event.Callee_Send_200OK, Event.Webrtc_session_connected),
        CallLatency_R_S(true, Event.Call_Confirmed, Event.Webrtc_session_connected),
        CallLatency_T_X(true, Event.Call_Hangup, Event.Call_Disconnected),
        TTE_MediaStop(true, Event.Call_Hangup, Event.Call_MediaStop),
        GuiResponseTo200OKReceived_O_guiP(true, Event.Caller_Received_200OK, Event.Caller_ActiveCallView_Bound),
        GuiResponseToRingCall_H_guiH(true, Event.Callee_Received_Ring_tts, Event.Callee_IncomingCallView_Bound),
        GuiUplAnswerCall_guiM1_S(true, Event.Gui_Answer_Call_OnClicked, Event.Webrtc_session_connected),
        GuiUplEndCall_guiW1_X(true, Event.Gui_EndCall_OnClicked, Event.Call_Hangup),
        GuiUplIgnoreCall(true, Event.Gui_EndInvite_OnClicked, Event.Call_Hangup),
        DropInInviteToAutoAccept(true, Event.Callee_INVITE_Received, Event.Callee_DropIn_Accepted),
        DropInAutoAcceptToMediaStreaming(true, Event.Callee_DropIn_Accepted, Event.Webrtc_session_connected),
        GuiCallRequestToOutboundRequest_guiZ1_guiZ3(true, Event.Caller_GuiCallRequestTapped, Event.Caller_GuiOutboundCallRequestSent),
        GuiDropInRequestToOutboundRequest_guiZ1_guiZ3(true, Event.Caller_GuiDropinRequestTapped, Event.Caller_GuiOutboundCallRequestSent),
        GuiOutboundRequestToBeginCall_guiZ3_C2(true, Event.Caller_GuiOutboundCallRequestSent, Event.Caller_BeginCall_Directive),
        GuiUplCallRequestToOutboundTTS_guiZ1_C1(true, Event.Caller_GuiCallRequestTapped, Event.Caller_OutgoingCallTtsSpeechStarted),
        GuiUplDropinTTM_guiZ1_S(true, Event.Caller_GuiDropinRequestTapped, Event.Webrtc_session_connected),
        EAC_optimal_earlyAcceptCall_to_invite(true, Event.EAC_EarlyAcceptCall, Event.EAC_INVITE),
        EAC_semiOptimal_invite_to_earlyAcceptCall(true, Event.EAC_INVITE, Event.EAC_EarlyAcceptCall),
        EAC_semiOptimal_invite_to_redundantAcceptCall(true, Event.EAC_INVITE, Event.EAC_Redundant_AcceptCall),
        EAC_nonOptimal_invite_to_acceptCall(true, Event.EAC_INVITE, Event.EAC_AcceptCall),
        EAC_nonOptimal_invite_to_redundantEarlyAcceptCall(true, Event.EAC_INVITE, Event.EAC_Redundant_EarlyAcceptCall),
        EAC_subverted_earlyAcceptCall_to_otherInvite(true, Event.EAC_EarlyAcceptCall, Event.EAC_Other_INVITE),
        CallerWarmupToBeginCall(true, Event.Caller_Received_Warmup, Event.Caller_BeginCall_Directive),
        Caller_beginCallReceived_to_Processed(true, Event.Caller_Deferred_BeginCall_Received, Event.Caller_Deferred_BeginCall_Processed),
        Caller_beginCall_to_outboundRinging_msecs(true, Event.Caller_BeginCall_Directive, Event.Caller_Received_180Ringing),
        Caller_beginCall_to_setRingTone_msecs(true, Event.Caller_BeginCall_Directive, Event.Caller_SetRingTone_Directive),
        Caller_setRingTone_to_outboundRinging_msecs(true, Event.Caller_SetRingTone_Directive, Event.Playing_Outgoing_Ringback_Tone),
        Caller_beginCall_to_inviteSent_msecs(true, Event.Caller_BeginCall_Directive, Event.Caller_INVITE_Sent),
        Caller_180Received_to_outboundRinging_msecs(true, Event.Caller_Received_180Ringing, Event.Playing_Outgoing_Ringback_Tone),
        Caller_okReceived_to_okAckSent_msecs(true, Event.Caller_Received_200OK, Event.Call_Confirmed),
        Caller_okReceived_to_audioFlowing_msecs(true, Event.Caller_Received_200OK, Event.Webrtc_session_connected),
        Caller_beginCall_to_bye_msecs(true, Event.Caller_BeginCall_Directive, Event.Call_Hangup),
        Callee_inviteReceived_to_inboundCallReceived_msecs(true, Event.Callee_INVITE_Received, Event.Callee_NotifySpeechletOfInvite),
        Callee_inviteReceived_to_ringCall_msecs(true, Event.Callee_INVITE_Received, Event.Callee_Received_Ring_tts),
        Callee_ringCall_to_180Sent_msecs(true, Event.Callee_Received_Ring_tts, Event.Callee_Send_180Ringing),
        Callee_acceptCall_to_okSent_msecs(true, Event.Callee_User_Answers, Event.Callee_Send_200OK),
        Callee_okSent_to_audioFlowing_msecs(true, Event.Callee_Send_200OK, Event.Webrtc_session_connected),
        Callee_okAckreceived_to_audioFlowing_msecs(true, Event.Call_Confirmed, Event.Webrtc_session_connected),
        Callee_inviteReceived_to_bye_msecs(true, Event.Callee_INVITE_Received, Event.Call_Hangup),
        Call_endCall_to_audioStop_msecs(true, Event.Call_Hangup, Event.Call_MediaStop),
        Call_endCall_to_disconnect_msecs(true, Event.Call_Hangup, Event.Call_Disconnected),
        Call_audioFlowing_to_audioStop_msecs(true, Event.Webrtc_session_connected, Event.Call_MediaStop),
        Callee_PreparingIncomingTts_to_PlayingIncomingTts_msecs(true, Event.Preparing_Tts_Url, Event.Playing_First_Incoming_tts),
        Callee_PreparingSecondIncomingTts_to_PlayingSecondIncomingTts_msecs(true, Event.Preparing_Tts_Url, Event.Playing_Second_Incoming_tts),
        Callee_CallToken_to_InviteReceived_msecs(true, Event.Callee_Received_CallToken, Event.Callee_INVITE_Received),
        Ui_Launched_to_Shown(true, Event.Ui_Launched, Event.Ui_Shown),
        call_ice_disconnect_duration(true, Event.call_ice_disconnected, Event.call_ice_reconnected),
        call_ice_not_receiving_duration(true, Event.ice_connection_notreceiving, Event.ice_connection_receiving),
        reconnect_initiator_signaling_duration(true, Event.reconnect_initiator_network_down, Event.reconnect_initiator_signaling_answered),
        reconnect_receiver_signaling_duration(true, Event.reconnect_receiver_signaling_offered, Event.reconnect_receiver_signaling_answer),
        reconnect_initiator_network_duration(true, Event.reconnect_initiator_network_down, Event.reconnect_initiator_network_up),
        reconnect_initiator_signaling_shutdown_duration(true, Event.reconnect_initiator_network_down, Event.reconnect_initiator_signaling_shutdown),
        reconnect_initiator_signaling_init_duration(true, Event.reconnect_initiator_network_up, Event.reconnect_initiator_signaling_init),
        reconnect_initiator_signaling_registration_duration(true, Event.reconnect_initiator_signaling_init, Event.reconnect_initiator_signaling_registered),
        reconnect_initiator_signaling_offer_duration(true, Event.reconnect_initiator_signaling_registered, Event.reconnect_initiator_signaling_offer),
        reconnect_initiator_signaling_answered_duration(true, Event.reconnect_initiator_signaling_offer, Event.reconnect_initiator_signaling_answered),
        reconnect_receiver_signaling_hangup_duration(true, Event.reconnect_receiver_signaling_offered, Event.reconnect_receiver_signaling_hangup),
        reconnect_receiver_ice_restart_duration(true, Event.reconnect_receiver_signaling_hangup, Event.reconnect_receiver_ice_restarted),
        reconnect_receiver_signaling_answer_duration(true, Event.reconnect_receiver_ice_restarted, Event.reconnect_receiver_signaling_answer),
        reconnect_initiator_timeout_duration(true, Event.call_ice_disconnected, Event.reconnect_initiator_timeout),
        reconnect_receiver_timeout_duration(true, Event.call_ice_disconnected, Event.reconnect_receiver_timeout),
        reconnect_initiator_fail_duration(true, Event.reconnect_initiator_network_up, Event.reconnect_initiator_fail),
        reconnect_receiver_fail_duration(true, Event.reconnect_receiver_signaling_hangup, Event.reconnect_receiver_fail),
        reconnect_initiator_registered_updatecall_duration(true, Event.reconnect_initiator_signaling_registered, Event.reconnect_initiator_updatecall_directive_received),
        reconnect_initiator_updatecall_offer_duration(true, Event.reconnect_initiator_updatecall_directive_received, Event.reconnect_initiator_signaling_offer),
        Callee_sipInfoReceived_to_inboundCallerIdSent_msecs(true, Event.Callee_Sip_Info_Received, Event.Callee_NotifySpeechletOfCallerId),
        Callee_secondRingCall_to_callerIdTTSReady_msecs(true, Event.Callee_Received_Ring_tts, Event.Playing_Second_Incoming_tts),
        DTMF_DirectiveReceived_to_INFOSent(true, Event.SendDTMF_Directive_Received, Event.DTMF_Sent_To_Sip),
        DTMF_INFOSent_to_INFOReceived(true, Event.DTMF_Sent_To_Sip, Event.Sip_Info_Response_Received),
        DTMF_DirectiveReceived_to_RFC2833Sent(true, Event.SendDTMF_Directive_Received, Event.DTMF_Sent_RFC2833);
        
        private final Event mBegin;
        private final Event mEnd;
        private final boolean mRecordMetric;

        Interval(boolean z, Event event, Event event2) {
            this.mRecordMetric = z;
            this.mBegin = event;
            this.mEnd = event2;
        }

        @Override // com.amazon.comms.instrumentation.EventTracer.Interval
        public boolean recordMetric() {
            return this.mRecordMetric;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.comms.instrumentation.EventTracer.Interval
        /* renamed from: getBegin */
        public Event mo3233getBegin() {
            return this.mBegin;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.comms.instrumentation.EventTracer.Interval
        /* renamed from: getEnd */
        public Event mo3234getEnd() {
            return this.mEnd;
        }
    }
}
