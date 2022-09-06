package com.amazon.deecomms.calling.model;

import java.util.List;
import java.util.Map;
/* loaded from: classes12.dex */
public class BeginCallPayload {
    public DisplayInfo displayInfo;
    public SipCommand sipCommand;

    /* loaded from: classes12.dex */
    public static class Agent {
        public String id;
        public String name;
        public String sipInstance;
        public String uri;
    }

    /* loaded from: classes12.dex */
    public static class AuthenticationInfo {
        public String authToken;
        public String password;
        public String realm;
        public String user;
    }

    /* loaded from: classes12.dex */
    public static class CspInfo {
        public String cspId;
        public String cspImage;
        public String displayName;
    }

    /* loaded from: classes12.dex */
    public static class DisplayInfo {
        public Party calleePartyInfo;
        public Party callerPartyInfo;
        public CspInfo cspInfo;
        public Object dropInInfo;
        public MediaSettingsInfo mediaSettingsInfo;
        public SystemTrayInfo systemTrayInfo;
    }

    /* loaded from: classes12.dex */
    public static class Endpoint {
        public String credential;
        public String url;
        public String username;
    }

    /* loaded from: classes12.dex */
    public static class MediaInfo {
        public SrtpInfo srtp;
        public MediaRelayInfo stun;
        public MediaRelayInfo turnTCP;
        public MediaRelayInfo turnUDP;
    }

    /* loaded from: classes12.dex */
    public static class MediaRelayInfo {
        public Endpoint calleeEndpoint;
        public Endpoint callerEndpoint;
    }

    /* loaded from: classes12.dex */
    public static class MediaSettingsInfo {
        public String enhancedProcessing;
        public boolean isEnhancedProcessingOptInRequired;
    }

    /* loaded from: classes12.dex */
    public static class MediaStream {
        public String direction;
        public String type;
    }

    /* loaded from: classes12.dex */
    public static class Offer {
        public List<MediaStream> mediaStreams;
        public String rtcpMuxPolicy;
        public Boolean trickleICE;
    }

    /* loaded from: classes12.dex */
    public static class Party {
        public String credit;
        public Boolean dropInPermission;
        public String endpointDescription;
        public String name;
    }

    /* loaded from: classes12.dex */
    public static class SipCommand {
        public String audioCategory;
        public String clientIdentifier;
        public String componentInContentFocus;
        public Boolean isMixable;
        public SipPayload payload;
        public String playBehavior;
    }

    /* loaded from: classes12.dex */
    public static class SipPayload {
        public AuthenticationInfo authenticationInfo;
        public String callId;
        public String callProvider;
        public Agent callee;
        public Agent caller;
        public List<Map<String, Object>> headers;
        public Boolean isDropIn;
        public MediaInfo mediaInfo;
        public MediaRelayInfo mediaRelayInfo;
        public Offer offer;
        public String outgoingTTSURL;
        public String promptId;
        public UtteranceInfo utteranceInfo;
    }

    /* loaded from: classes12.dex */
    public static class SrtpInfo {
        public String keying;
    }

    /* loaded from: classes12.dex */
    public static class SystemTrayInfo {
        public String callDescription;
    }

    /* loaded from: classes12.dex */
    public static class UtteranceInfo {
        public Long endOfSpeechAbsoluteTimestampInMillis;
        public Long endOfSpeechRelativeTimestampInMillis;
        public String requestId;
    }

    public String getCallId() {
        return this.sipCommand.payload.callId;
    }

    public String getCallProvider() {
        return this.sipCommand.payload.callProvider;
    }

    public String getCspId() {
        CspInfo cspInfo;
        DisplayInfo displayInfo = this.displayInfo;
        return (displayInfo == null || (cspInfo = displayInfo.cspInfo) == null) ? "" : cspInfo.cspId;
    }
}
