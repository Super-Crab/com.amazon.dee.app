package com.amazon.deecomms.calling.model;

import com.amazon.alexa.smarthomecameras.routing.CamerasRouteParameter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonInclude(JsonInclude.Include.NON_NULL)
/* loaded from: classes12.dex */
public class RingCallDirective {
    @JsonProperty("displayInfo")
    public DisplayInfo displayInfo;
    @JsonProperty("sipCommand")
    public SipCommand sipCommand;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    /* loaded from: classes12.dex */
    public static class CalleePartyInfo {
        @JsonProperty("credit")
        public Object credit;
        @JsonProperty("dropInPermission")
        public boolean dropInPermission;
        @JsonProperty("endpointDescription")
        public Object endpointDescription;
        @JsonProperty("name")
        public String name;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    /* loaded from: classes12.dex */
    public static class CallerPartyInfo {
        @JsonProperty("credit")
        public Object credit;
        @JsonProperty("dropInPermission")
        public boolean dropInPermission;
        @JsonProperty("endpointDescription")
        public Object endpointDescription;
        @JsonProperty("name")
        public String name;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    /* loaded from: classes12.dex */
    public static class CspInfo {
        @JsonProperty("cspId")
        public String cspId;
        @JsonProperty("cspImage")
        public Object cspImage;
        @JsonProperty(CamerasRouteParameter.DISPLAY_NAME)
        public Object displayName;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    /* loaded from: classes12.dex */
    public static class DisplayInfo {
        @JsonProperty("calleePartyInfo")
        public CalleePartyInfo calleePartyInfo;
        @JsonProperty("callerPartyInfo")
        public CallerPartyInfo callerPartyInfo;
        @JsonProperty("cspInfo")
        public CspInfo cspInfo;
        @JsonProperty("dropInInfo")
        public Object dropInInfo;
        @JsonProperty("mediaSettingsInfo")
        public MediaSettingsInfo mediaSettingsInfo;
        @JsonProperty("systemTrayInfo")
        public SystemTrayInfo systemTrayInfo;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    /* loaded from: classes12.dex */
    public static class MediaSettingsInfo {
        @JsonProperty("enhancedProcessing")
        public String enhancedProcessing;
        @JsonProperty("isEnhancedProcessingOptInRequired")
        public boolean isEnhancedProcessingOptInRequired;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    /* loaded from: classes12.dex */
    public static class Payload {
        @JsonProperty("callId")
        public String callId;
        @JsonProperty("incomingTTSURL")
        public String incomingTTSURL;
        @JsonProperty("lightsOn")
        public boolean lightsOn;
        @JsonProperty("promptId")
        public Object promptId;
        @JsonProperty("soundOn")
        public boolean soundOn;
        @JsonProperty("timeouts")
        public Object timeouts;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    /* loaded from: classes12.dex */
    public static class SipCommand {
        @JsonProperty("audioCategory")
        public String audioCategory;
        @JsonProperty("clientIdentifier")
        public String clientIdentifier;
        @JsonProperty("componentInContentFocus")
        public String componentInContentFocus;
        @JsonProperty("isMixable")
        public boolean isMixable;
        @JsonProperty("payload")
        public Payload payload;
        @JsonProperty("playBehavior")
        public String playBehavior;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    /* loaded from: classes12.dex */
    public static class SystemTrayInfo {
        @JsonProperty("callDescription")
        public String callDescription;
    }
}
