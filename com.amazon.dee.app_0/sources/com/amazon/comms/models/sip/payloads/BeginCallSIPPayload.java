package com.amazon.comms.models.sip.payloads;

import com.amazon.comms.models.scrubber.ScrubbedString;
import com.amazon.comms.models.sip.MediaConfiguration;
import com.amazon.comms.models.sip.MediaInfo;
import com.amazon.comms.models.sip.Offer;
import com.amazon.comms.models.sip.RDNEndpoint;
import com.amazon.comms.models.sip.SIPAuthenticationInfo;
import com.amazon.comms.models.sip.SIPContact;
import com.amazon.comms.models.sip.StateTransitionTimeoutRule;
import com.amazon.comms.models.sip.UtteranceInfo;
import com.amazon.dee.application.service.common.logging.RedactInLogs;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
@RedactInLogs
/* loaded from: classes11.dex */
public class BeginCallSIPPayload implements SIPPayload {
    private SIPAuthenticationInfo authenticationInfo;
    private String callId;
    private String callProvider;
    private SIPContact callee;
    private SIPContact caller;
    private List<Map<String, String>> headers;
    private boolean isDropIn;
    private String join;
    private String locationId;
    private MediaConfiguration mediaConfiguration;
    private MediaInfo mediaInfo;
    private RDNEndpoint mediaRelayInfo;
    private Offer offer;
    @JsonProperty
    private ScrubbedString outgoingRingtoneURL;
    @JsonProperty
    private ScrubbedString outgoingTTSURL;
    private String promptId;
    private List<StateTransitionTimeoutRule> timeouts;
    private UtteranceInfo utteranceInfo;

    /* loaded from: classes11.dex */
    public static class BeginCallSIPPayloadBuilder {
        private SIPAuthenticationInfo authenticationInfo;
        private String callId;
        private String callProvider;
        private SIPContact callee;
        private SIPContact caller;
        private List<Map<String, String>> headers;
        private boolean isDropIn;
        private String join;
        private String locationId;
        private MediaConfiguration mediaConfiguration;
        private MediaInfo mediaInfo;
        private RDNEndpoint mediaRelayInfo;
        private Offer offer;
        private ScrubbedString outgoingRingtoneURL;
        private ScrubbedString outgoingTTSURL;
        private String promptId;
        private List<StateTransitionTimeoutRule> timeouts;
        private UtteranceInfo utteranceInfo;

        BeginCallSIPPayloadBuilder() {
        }

        public BeginCallSIPPayloadBuilder authenticationInfo(SIPAuthenticationInfo sIPAuthenticationInfo) {
            this.authenticationInfo = sIPAuthenticationInfo;
            return this;
        }

        public BeginCallSIPPayload build() {
            return new BeginCallSIPPayload(this.callProvider, this.callId, this.caller, this.callee, this.authenticationInfo, this.mediaRelayInfo, this.mediaInfo, this.offer, this.locationId, this.isDropIn, this.timeouts, this.outgoingTTSURL, this.outgoingRingtoneURL, this.utteranceInfo, this.mediaConfiguration, this.promptId, this.headers, this.join);
        }

        public BeginCallSIPPayloadBuilder callId(String str) {
            this.callId = str;
            return this;
        }

        public BeginCallSIPPayloadBuilder callProvider(String str) {
            this.callProvider = str;
            return this;
        }

        public BeginCallSIPPayloadBuilder callee(SIPContact sIPContact) {
            this.callee = sIPContact;
            return this;
        }

        public BeginCallSIPPayloadBuilder caller(SIPContact sIPContact) {
            this.caller = sIPContact;
            return this;
        }

        public BeginCallSIPPayloadBuilder headers(List<Map<String, String>> list) {
            this.headers = list;
            return this;
        }

        public BeginCallSIPPayloadBuilder isDropIn(boolean z) {
            this.isDropIn = z;
            return this;
        }

        public BeginCallSIPPayloadBuilder join(String str) {
            this.join = str;
            return this;
        }

        public BeginCallSIPPayloadBuilder locationId(String str) {
            this.locationId = str;
            return this;
        }

        public BeginCallSIPPayloadBuilder mediaConfiguration(MediaConfiguration mediaConfiguration) {
            this.mediaConfiguration = mediaConfiguration;
            return this;
        }

        public BeginCallSIPPayloadBuilder mediaInfo(MediaInfo mediaInfo) {
            this.mediaInfo = mediaInfo;
            return this;
        }

        public BeginCallSIPPayloadBuilder mediaRelayInfo(RDNEndpoint rDNEndpoint) {
            this.mediaRelayInfo = rDNEndpoint;
            return this;
        }

        public BeginCallSIPPayloadBuilder offer(Offer offer) {
            this.offer = offer;
            return this;
        }

        public BeginCallSIPPayloadBuilder outgoingRingtoneURL(String str) {
            if (str == null) {
                this.outgoingRingtoneURL = null;
            } else {
                this.outgoingRingtoneURL = new ScrubbedString(str);
            }
            return this;
        }

        public BeginCallSIPPayloadBuilder outgoingTTSURL(String str) {
            this.outgoingTTSURL = new ScrubbedString(str);
            return this;
        }

        public BeginCallSIPPayloadBuilder promptId(String str) {
            this.promptId = str;
            return this;
        }

        public BeginCallSIPPayloadBuilder timeouts(List<StateTransitionTimeoutRule> list) {
            this.timeouts = list;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BeginCallSIPPayload.BeginCallSIPPayloadBuilder(callProvider=");
            outline107.append(this.callProvider);
            outline107.append(", callId=");
            outline107.append(this.callId);
            outline107.append(", caller=");
            outline107.append(this.caller);
            outline107.append(", callee=");
            outline107.append(this.callee);
            outline107.append(", authenticationInfo=");
            outline107.append(this.authenticationInfo);
            outline107.append(", mediaRelayInfo=");
            outline107.append(this.mediaRelayInfo);
            outline107.append(", mediaInfo=");
            outline107.append(this.mediaInfo);
            outline107.append(", offer=");
            outline107.append(this.offer);
            outline107.append(", locationId=");
            outline107.append(this.locationId);
            outline107.append(", isDropIn=");
            outline107.append(this.isDropIn);
            outline107.append(", timeouts=");
            outline107.append(this.timeouts);
            outline107.append(", outgoingTTSURL=");
            outline107.append(this.outgoingTTSURL);
            outline107.append(", outgoingRingtoneURL=");
            outline107.append(this.outgoingRingtoneURL);
            outline107.append(", utteranceInfo=");
            outline107.append(this.utteranceInfo);
            outline107.append(", mediaConfiguration=");
            outline107.append(this.mediaConfiguration);
            outline107.append(", promptId=");
            outline107.append(this.promptId);
            outline107.append(", headers=");
            outline107.append(this.headers);
            outline107.append(", join=");
            return GeneratedOutlineSupport1.outline91(outline107, this.join, ")");
        }

        public BeginCallSIPPayloadBuilder utteranceInfo(UtteranceInfo utteranceInfo) {
            this.utteranceInfo = utteranceInfo;
            return this;
        }
    }

    public BeginCallSIPPayload() {
    }

    public static BeginCallSIPPayloadBuilder builder() {
        return new BeginCallSIPPayloadBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof BeginCallSIPPayload;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BeginCallSIPPayload)) {
            return false;
        }
        BeginCallSIPPayload beginCallSIPPayload = (BeginCallSIPPayload) obj;
        if (!beginCallSIPPayload.canEqual(this)) {
            return false;
        }
        String callProvider = getCallProvider();
        String callProvider2 = beginCallSIPPayload.getCallProvider();
        if (callProvider != null ? !callProvider.equals(callProvider2) : callProvider2 != null) {
            return false;
        }
        String callId = getCallId();
        String callId2 = beginCallSIPPayload.getCallId();
        if (callId != null ? !callId.equals(callId2) : callId2 != null) {
            return false;
        }
        SIPContact caller = getCaller();
        SIPContact caller2 = beginCallSIPPayload.getCaller();
        if (caller != null ? !caller.equals(caller2) : caller2 != null) {
            return false;
        }
        SIPContact callee = getCallee();
        SIPContact callee2 = beginCallSIPPayload.getCallee();
        if (callee != null ? !callee.equals(callee2) : callee2 != null) {
            return false;
        }
        SIPAuthenticationInfo authenticationInfo = getAuthenticationInfo();
        SIPAuthenticationInfo authenticationInfo2 = beginCallSIPPayload.getAuthenticationInfo();
        if (authenticationInfo != null ? !authenticationInfo.equals(authenticationInfo2) : authenticationInfo2 != null) {
            return false;
        }
        RDNEndpoint mediaRelayInfo = getMediaRelayInfo();
        RDNEndpoint mediaRelayInfo2 = beginCallSIPPayload.getMediaRelayInfo();
        if (mediaRelayInfo != null ? !mediaRelayInfo.equals(mediaRelayInfo2) : mediaRelayInfo2 != null) {
            return false;
        }
        MediaInfo mediaInfo = getMediaInfo();
        MediaInfo mediaInfo2 = beginCallSIPPayload.getMediaInfo();
        if (mediaInfo != null ? !mediaInfo.equals(mediaInfo2) : mediaInfo2 != null) {
            return false;
        }
        Offer offer = getOffer();
        Offer offer2 = beginCallSIPPayload.getOffer();
        if (offer != null ? !offer.equals(offer2) : offer2 != null) {
            return false;
        }
        String locationId = getLocationId();
        String locationId2 = beginCallSIPPayload.getLocationId();
        if (locationId != null ? !locationId.equals(locationId2) : locationId2 != null) {
            return false;
        }
        if (getIsDropIn() != beginCallSIPPayload.getIsDropIn()) {
            return false;
        }
        List<StateTransitionTimeoutRule> timeouts = getTimeouts();
        List<StateTransitionTimeoutRule> timeouts2 = beginCallSIPPayload.getTimeouts();
        if (timeouts != null ? !timeouts.equals(timeouts2) : timeouts2 != null) {
            return false;
        }
        String outgoingTTSURL = getOutgoingTTSURL();
        String outgoingTTSURL2 = beginCallSIPPayload.getOutgoingTTSURL();
        if (outgoingTTSURL != null ? !outgoingTTSURL.equals(outgoingTTSURL2) : outgoingTTSURL2 != null) {
            return false;
        }
        String outgoingRingtoneURL = getOutgoingRingtoneURL();
        String outgoingRingtoneURL2 = beginCallSIPPayload.getOutgoingRingtoneURL();
        if (outgoingRingtoneURL != null ? !outgoingRingtoneURL.equals(outgoingRingtoneURL2) : outgoingRingtoneURL2 != null) {
            return false;
        }
        UtteranceInfo utteranceInfo = getUtteranceInfo();
        UtteranceInfo utteranceInfo2 = beginCallSIPPayload.getUtteranceInfo();
        if (utteranceInfo != null ? !utteranceInfo.equals(utteranceInfo2) : utteranceInfo2 != null) {
            return false;
        }
        MediaConfiguration mediaConfiguration = getMediaConfiguration();
        MediaConfiguration mediaConfiguration2 = beginCallSIPPayload.getMediaConfiguration();
        if (mediaConfiguration != null ? !mediaConfiguration.equals(mediaConfiguration2) : mediaConfiguration2 != null) {
            return false;
        }
        String promptId = getPromptId();
        String promptId2 = beginCallSIPPayload.getPromptId();
        if (promptId != null ? !promptId.equals(promptId2) : promptId2 != null) {
            return false;
        }
        List<Map<String, String>> headers = getHeaders();
        List<Map<String, String>> headers2 = beginCallSIPPayload.getHeaders();
        if (headers != null ? !headers.equals(headers2) : headers2 != null) {
            return false;
        }
        String join = getJoin();
        String join2 = beginCallSIPPayload.getJoin();
        return join != null ? join.equals(join2) : join2 == null;
    }

    public SIPAuthenticationInfo getAuthenticationInfo() {
        return this.authenticationInfo;
    }

    @Override // com.amazon.comms.models.sip.payloads.SIPPayload
    public String getCallId() {
        return this.callId;
    }

    public String getCallProvider() {
        return this.callProvider;
    }

    public SIPContact getCallee() {
        return this.callee;
    }

    public SIPContact getCaller() {
        return this.caller;
    }

    public List<Map<String, String>> getHeaders() {
        return this.headers;
    }

    public boolean getIsDropIn() {
        return this.isDropIn;
    }

    public String getJoin() {
        return this.join;
    }

    public String getLocationId() {
        return this.locationId;
    }

    public MediaConfiguration getMediaConfiguration() {
        return this.mediaConfiguration;
    }

    public MediaInfo getMediaInfo() {
        return this.mediaInfo;
    }

    public RDNEndpoint getMediaRelayInfo() {
        return this.mediaRelayInfo;
    }

    public Offer getOffer() {
        return this.offer;
    }

    @JsonIgnore
    public String getOutgoingRingtoneURL() {
        ScrubbedString scrubbedString = this.outgoingRingtoneURL;
        if (scrubbedString != null) {
            return scrubbedString.getValue();
        }
        return null;
    }

    @JsonIgnore
    public String getOutgoingTTSURL() {
        ScrubbedString scrubbedString = this.outgoingTTSURL;
        if (scrubbedString != null) {
            return scrubbedString.getValue();
        }
        return null;
    }

    public String getPromptId() {
        return this.promptId;
    }

    public List<StateTransitionTimeoutRule> getTimeouts() {
        return this.timeouts;
    }

    public UtteranceInfo getUtteranceInfo() {
        return this.utteranceInfo;
    }

    public int hashCode() {
        String callProvider = getCallProvider();
        int i = 43;
        int hashCode = callProvider == null ? 43 : callProvider.hashCode();
        String callId = getCallId();
        int hashCode2 = ((hashCode + 59) * 59) + (callId == null ? 43 : callId.hashCode());
        SIPContact caller = getCaller();
        int hashCode3 = (hashCode2 * 59) + (caller == null ? 43 : caller.hashCode());
        SIPContact callee = getCallee();
        int hashCode4 = (hashCode3 * 59) + (callee == null ? 43 : callee.hashCode());
        SIPAuthenticationInfo authenticationInfo = getAuthenticationInfo();
        int hashCode5 = (hashCode4 * 59) + (authenticationInfo == null ? 43 : authenticationInfo.hashCode());
        RDNEndpoint mediaRelayInfo = getMediaRelayInfo();
        int hashCode6 = (hashCode5 * 59) + (mediaRelayInfo == null ? 43 : mediaRelayInfo.hashCode());
        MediaInfo mediaInfo = getMediaInfo();
        int hashCode7 = (hashCode6 * 59) + (mediaInfo == null ? 43 : mediaInfo.hashCode());
        Offer offer = getOffer();
        int hashCode8 = (hashCode7 * 59) + (offer == null ? 43 : offer.hashCode());
        String locationId = getLocationId();
        int hashCode9 = (((hashCode8 * 59) + (locationId == null ? 43 : locationId.hashCode())) * 59) + (getIsDropIn() ? 79 : 97);
        List<StateTransitionTimeoutRule> timeouts = getTimeouts();
        int hashCode10 = (hashCode9 * 59) + (timeouts == null ? 43 : timeouts.hashCode());
        String outgoingTTSURL = getOutgoingTTSURL();
        int hashCode11 = (hashCode10 * 59) + (outgoingTTSURL == null ? 43 : outgoingTTSURL.hashCode());
        String outgoingRingtoneURL = getOutgoingRingtoneURL();
        int hashCode12 = (hashCode11 * 59) + (outgoingRingtoneURL == null ? 43 : outgoingRingtoneURL.hashCode());
        UtteranceInfo utteranceInfo = getUtteranceInfo();
        int hashCode13 = (hashCode12 * 59) + (utteranceInfo == null ? 43 : utteranceInfo.hashCode());
        MediaConfiguration mediaConfiguration = getMediaConfiguration();
        int hashCode14 = (hashCode13 * 59) + (mediaConfiguration == null ? 43 : mediaConfiguration.hashCode());
        String promptId = getPromptId();
        int hashCode15 = (hashCode14 * 59) + (promptId == null ? 43 : promptId.hashCode());
        List<Map<String, String>> headers = getHeaders();
        int hashCode16 = (hashCode15 * 59) + (headers == null ? 43 : headers.hashCode());
        String join = getJoin();
        int i2 = hashCode16 * 59;
        if (join != null) {
            i = join.hashCode();
        }
        return i2 + i;
    }

    public void setAuthenticationInfo(SIPAuthenticationInfo sIPAuthenticationInfo) {
        this.authenticationInfo = sIPAuthenticationInfo;
    }

    public void setCallId(String str) {
        this.callId = str;
    }

    public void setCallProvider(String str) {
        this.callProvider = str;
    }

    public void setCallee(SIPContact sIPContact) {
        this.callee = sIPContact;
    }

    public void setCaller(SIPContact sIPContact) {
        this.caller = sIPContact;
    }

    public void setHeaders(List<Map<String, String>> list) {
        this.headers = list;
    }

    public void setIsDropIn(boolean z) {
        this.isDropIn = z;
    }

    public void setJoin(String str) {
        this.join = str;
    }

    public void setLocationId(String str) {
        this.locationId = str;
    }

    public void setMediaConfiguration(MediaConfiguration mediaConfiguration) {
        this.mediaConfiguration = mediaConfiguration;
    }

    public void setMediaInfo(MediaInfo mediaInfo) {
        this.mediaInfo = mediaInfo;
    }

    public void setMediaRelayInfo(RDNEndpoint rDNEndpoint) {
        this.mediaRelayInfo = rDNEndpoint;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    @JsonIgnore
    public void setOutgoingRingtoneURL(String str) {
        this.outgoingRingtoneURL = new ScrubbedString(str);
    }

    @JsonIgnore
    public void setOutgoingTTSURL(String str) {
        this.outgoingTTSURL = new ScrubbedString(str);
    }

    public void setPromptId(String str) {
        this.promptId = str;
    }

    public void setTimeouts(List<StateTransitionTimeoutRule> list) {
        this.timeouts = list;
    }

    public void setUtteranceInfo(UtteranceInfo utteranceInfo) {
        this.utteranceInfo = utteranceInfo;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BeginCallSIPPayload(callProvider=");
        outline107.append(getCallProvider());
        outline107.append(", callId=");
        outline107.append(getCallId());
        outline107.append(", caller=");
        outline107.append(getCaller());
        outline107.append(", callee=");
        outline107.append(getCallee());
        outline107.append(", authenticationInfo=");
        outline107.append(getAuthenticationInfo());
        outline107.append(", mediaRelayInfo=");
        outline107.append(getMediaRelayInfo());
        outline107.append(", mediaInfo=");
        outline107.append(getMediaInfo());
        outline107.append(", offer=");
        outline107.append(getOffer());
        outline107.append(", locationId=");
        outline107.append(getLocationId());
        outline107.append(", isDropIn=");
        outline107.append(getIsDropIn());
        outline107.append(", timeouts=");
        outline107.append(getTimeouts());
        outline107.append(", outgoingTTSURL=");
        outline107.append(getOutgoingTTSURL());
        outline107.append(", outgoingRingtoneURL=");
        outline107.append(getOutgoingRingtoneURL());
        outline107.append(", utteranceInfo=");
        outline107.append(getUtteranceInfo());
        outline107.append(", mediaConfiguration=");
        outline107.append(getMediaConfiguration());
        outline107.append(", promptId=");
        outline107.append(getPromptId());
        outline107.append(", headers=");
        outline107.append(getHeaders());
        outline107.append(", join=");
        outline107.append(getJoin());
        outline107.append(")");
        return outline107.toString();
    }

    private BeginCallSIPPayload(String str, String str2, SIPContact sIPContact, SIPContact sIPContact2, SIPAuthenticationInfo sIPAuthenticationInfo, RDNEndpoint rDNEndpoint, MediaInfo mediaInfo, Offer offer, String str3, boolean z, List<StateTransitionTimeoutRule> list, ScrubbedString scrubbedString, ScrubbedString scrubbedString2, UtteranceInfo utteranceInfo, MediaConfiguration mediaConfiguration, String str4, List<Map<String, String>> list2, String str5) {
        this.callProvider = str;
        this.callId = str2;
        this.caller = sIPContact;
        this.callee = sIPContact2;
        this.authenticationInfo = sIPAuthenticationInfo;
        this.mediaRelayInfo = rDNEndpoint;
        this.mediaInfo = mediaInfo;
        this.offer = offer;
        this.locationId = str3;
        this.isDropIn = z;
        this.timeouts = list;
        this.outgoingTTSURL = scrubbedString;
        this.outgoingRingtoneURL = scrubbedString2;
        this.utteranceInfo = utteranceInfo;
        this.mediaConfiguration = mediaConfiguration;
        this.promptId = str4;
        this.headers = list2;
        this.join = str5;
    }
}
