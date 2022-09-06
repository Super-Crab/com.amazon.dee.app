package com.amazon.rtcsc.capabilityagent.common.core;
/* loaded from: classes13.dex */
public final class RtcscConstants {
    public static final String RTCSC_CAPABILITY_AGENT_APP_IDENTIFIER = "RTCSessionController";
    public static final String RTCSC_CAPABILITY_AGENT_INTERFACE_NAME = "Alexa.RTCSessionController";
    public static final String RTCSC_CAPABILITY_AGENT_INTERFACE_VERSION = "3.1";
    public static final String RTCSC_CONTEXT_STATE_NAME = "RTCSessionControllerState";
    public static final String RTCSC_DIRECTIVE_NAME_DISCONNECT_SESSION = "DisconnectSession";
    public static final String RTCSC_DIRECTIVE_NAME_GENERATE_ANSWER_FOR_SESSION = "GenerateAnswerForSession";
    public static final String RTCSC_DIRECTIVE_NAME_INITIATE_SESSION = "InitiateSession";
    public static final String RTCSC_DIRECTIVE_NAME_INITIATE_SESSION_WITH_OFFER = "InitiateSessionWithOffer";
    public static final String RTCSC_DIRECTIVE_NAME_SESSION_CONNECTED = "SessionConnected";
    public static final String RTCSC_DIRECTIVE_NAME_SESSION_DISCONNECTED = "SessionDisconnected";
    public static final String RTCSC_DIRECTIVE_NAME_START_PEER_CONNECTION = "StartPeerConnection";
    public static final String RTCSC_DIRECTIVE_NAME_UPDATE_PEER_CONNECTION = "UpdatePeerConnection";
    public static final String RTCSC_DIRECTIVE_NAME_UPDATE_SESSION = "UpdateSession";
    public static final String RTCSC_DIRECTIVE_NAME_UPDATE_SESSION_WITH_OFFER = "UpdateSessionWithOffer";
    public static final String RTCSC_DIRECTIVE_NAME_WARMUP_MEDIA_STACK = "WarmUpMediaStack";
    public static final String RTCSC_EVENT_NAME_INITIATE_SESSION_FAILED = "InitiateSessionFailed";
    public static final String RTCSC_EVENT_NAME_INITIATE_SESSION_WITH_OFFER_FAILED = "InitiateSessionWithOfferFailed";
    public static final String RTCSC_EVENT_NAME_SESSION_DISCONNECTED = "SessionDisconnected";

    private RtcscConstants() {
        throw new IllegalStateException("RTCSC Constants Utility class");
    }
}
