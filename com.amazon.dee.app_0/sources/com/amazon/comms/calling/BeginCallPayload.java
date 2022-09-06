package com.amazon.comms.calling;

import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public class BeginCallPayload {
    public DisplayInfo displayInfo;
    public SipCommand sipCommand;

    /* loaded from: classes10.dex */
    public static class Agent {
        public String id;
        public String name;
        public String sipInstance;
        public String uri;
    }

    /* loaded from: classes10.dex */
    public static class AuthenticationInfo {
        public String authToken;
        public String password;
        public String realm;
        public String user;
    }

    /* loaded from: classes10.dex */
    public static class CspInfo {
        public String cspId;
        public String cspImage;
        public String displayName;
    }

    /* loaded from: classes10.dex */
    public static class DisplayInfo {
        public Party calleePartyInfo;
        public Party callerPartyInfo;
        public CspInfo cspInfo;
        public Object dropInInfo;
        public MediaSettingsInfo mediaSettingsInfo;
        public SystemTrayInfo systemTrayInfo;
    }

    /* loaded from: classes10.dex */
    public static class Endpoint {
        public String credential;
        public String url;
        public String username;
    }

    /* loaded from: classes10.dex */
    public static class MediaInfo {
        public SrtpInfo srtp;
        public MediaRelayInfo stun;
        public MediaRelayInfo turnTCP;
        public MediaRelayInfo turnUDP;
    }

    /* loaded from: classes10.dex */
    public static class MediaRelayInfo {
        public Endpoint calleeEndpoint;
        public Endpoint callerEndpoint;
    }

    /* loaded from: classes10.dex */
    public static class MediaSettingsInfo {
        public String enhancedProcessing;
    }

    /* loaded from: classes10.dex */
    public static class MediaStream {
        public String direction;
        public String type;
    }

    /* loaded from: classes10.dex */
    public static class Offer {
        public List<MediaStream> mediaStreams;
    }

    /* loaded from: classes10.dex */
    public static class Party {
        public String credit;
        public Boolean dropInPermission;
        public String endpointDescription;
        public String name;
    }

    /* loaded from: classes10.dex */
    public static class SipCommand {
        public String audioCategory;
        public String clientIdentifier;
        public String componentInContentFocus;
        public Boolean isMixable;
        public SipPayload payload;
        public String playBehavior;
    }

    /* loaded from: classes10.dex */
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

    /* loaded from: classes10.dex */
    public static class SrtpInfo {
        public String keying;
    }

    /* loaded from: classes10.dex */
    public static class SystemTrayInfo {
        public String callDescription;
    }

    /* loaded from: classes10.dex */
    public static class UtteranceInfo {
        public Long endOfSpeechAbsoluteTimestampInMillis;
        public Long endOfSpeechRelativeTimestampInMillis;
        public String requestId;
    }
}
