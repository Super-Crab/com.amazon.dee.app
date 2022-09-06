package com.amazon.comms.models.sip.payloads;

import com.amazon.comms.models.scrubber.ScrubbedString;
import com.amazon.comms.models.sip.StateTransitionTimeoutRule;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes11.dex */
public class RingCallSIPPayload implements SIPPayload {
    private String callId;
    @JsonProperty
    private ScrubbedString incomingTTSURL;
    private boolean lightsOn;
    private String promptId;
    private boolean soundOn;
    private List<StateTransitionTimeoutRule> timeouts;

    /* loaded from: classes11.dex */
    public static class RingCallSIPPayloadBuilder {
        private String callId;
        private ScrubbedString incomingTTSURL;
        private boolean lightsOn;
        private String promptId;
        private boolean soundOn;
        private List<StateTransitionTimeoutRule> timeouts;

        RingCallSIPPayloadBuilder() {
        }

        public RingCallSIPPayload build() {
            return new RingCallSIPPayload(this.callId, this.soundOn, this.lightsOn, this.promptId, this.timeouts, this.incomingTTSURL);
        }

        public RingCallSIPPayloadBuilder callId(String str) {
            this.callId = str;
            return this;
        }

        public RingCallSIPPayloadBuilder incomingTTSURL(String str) {
            this.incomingTTSURL = new ScrubbedString(str);
            return this;
        }

        public RingCallSIPPayloadBuilder lightsOn(boolean z) {
            this.lightsOn = z;
            return this;
        }

        public RingCallSIPPayloadBuilder promptId(String str) {
            this.promptId = str;
            return this;
        }

        public RingCallSIPPayloadBuilder soundOn(boolean z) {
            this.soundOn = z;
            return this;
        }

        public RingCallSIPPayloadBuilder timeouts(List<StateTransitionTimeoutRule> list) {
            this.timeouts = list;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RingCallSIPPayload.RingCallSIPPayloadBuilder(callId=");
            outline107.append(this.callId);
            outline107.append(", soundOn=");
            outline107.append(this.soundOn);
            outline107.append(", lightsOn=");
            outline107.append(this.lightsOn);
            outline107.append(", promptId=");
            outline107.append(this.promptId);
            outline107.append(", timeouts=");
            outline107.append(this.timeouts);
            outline107.append(", incomingTTSURL=");
            outline107.append(this.incomingTTSURL);
            outline107.append(")");
            return outline107.toString();
        }
    }

    public RingCallSIPPayload() {
    }

    public static RingCallSIPPayloadBuilder builder() {
        return new RingCallSIPPayloadBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof RingCallSIPPayload;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RingCallSIPPayload)) {
            return false;
        }
        RingCallSIPPayload ringCallSIPPayload = (RingCallSIPPayload) obj;
        if (!ringCallSIPPayload.canEqual(this)) {
            return false;
        }
        String callId = getCallId();
        String callId2 = ringCallSIPPayload.getCallId();
        if (callId != null ? !callId.equals(callId2) : callId2 != null) {
            return false;
        }
        if (getSoundOn() != ringCallSIPPayload.getSoundOn() || getLightsOn() != ringCallSIPPayload.getLightsOn()) {
            return false;
        }
        String promptId = getPromptId();
        String promptId2 = ringCallSIPPayload.getPromptId();
        if (promptId != null ? !promptId.equals(promptId2) : promptId2 != null) {
            return false;
        }
        List<StateTransitionTimeoutRule> timeouts = getTimeouts();
        List<StateTransitionTimeoutRule> timeouts2 = ringCallSIPPayload.getTimeouts();
        if (timeouts != null ? !timeouts.equals(timeouts2) : timeouts2 != null) {
            return false;
        }
        String incomingTTSURL = getIncomingTTSURL();
        String incomingTTSURL2 = ringCallSIPPayload.getIncomingTTSURL();
        return incomingTTSURL != null ? incomingTTSURL.equals(incomingTTSURL2) : incomingTTSURL2 == null;
    }

    @Override // com.amazon.comms.models.sip.payloads.SIPPayload
    public String getCallId() {
        return this.callId;
    }

    @JsonIgnore
    public String getIncomingTTSURL() {
        ScrubbedString scrubbedString = this.incomingTTSURL;
        if (scrubbedString != null) {
            return scrubbedString.getValue();
        }
        return null;
    }

    public boolean getLightsOn() {
        return this.lightsOn;
    }

    public String getPromptId() {
        return this.promptId;
    }

    public boolean getSoundOn() {
        return this.soundOn;
    }

    public List<StateTransitionTimeoutRule> getTimeouts() {
        return this.timeouts;
    }

    public int hashCode() {
        String callId = getCallId();
        int i = 43;
        int i2 = 79;
        int hashCode = ((((callId == null ? 43 : callId.hashCode()) + 59) * 59) + (getSoundOn() ? 79 : 97)) * 59;
        if (!getLightsOn()) {
            i2 = 97;
        }
        String promptId = getPromptId();
        int hashCode2 = ((hashCode + i2) * 59) + (promptId == null ? 43 : promptId.hashCode());
        List<StateTransitionTimeoutRule> timeouts = getTimeouts();
        int hashCode3 = (hashCode2 * 59) + (timeouts == null ? 43 : timeouts.hashCode());
        String incomingTTSURL = getIncomingTTSURL();
        int i3 = hashCode3 * 59;
        if (incomingTTSURL != null) {
            i = incomingTTSURL.hashCode();
        }
        return i3 + i;
    }

    public void setCallId(String str) {
        this.callId = str;
    }

    @JsonIgnore
    public void setIncomingTTSURL(String str) {
        this.incomingTTSURL = new ScrubbedString(str);
    }

    public void setLightsOn(boolean z) {
        this.lightsOn = z;
    }

    public void setPromptId(String str) {
        this.promptId = str;
    }

    public void setSoundOn(boolean z) {
        this.soundOn = z;
    }

    public void setTimeouts(List<StateTransitionTimeoutRule> list) {
        this.timeouts = list;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RingCallSIPPayload(callId=");
        outline107.append(getCallId());
        outline107.append(", soundOn=");
        outline107.append(getSoundOn());
        outline107.append(", lightsOn=");
        outline107.append(getLightsOn());
        outline107.append(", promptId=");
        outline107.append(getPromptId());
        outline107.append(", timeouts=");
        outline107.append(getTimeouts());
        outline107.append(", incomingTTSURL=");
        outline107.append(getIncomingTTSURL());
        outline107.append(")");
        return outline107.toString();
    }

    private RingCallSIPPayload(String str, boolean z, boolean z2, String str2, List<StateTransitionTimeoutRule> list, ScrubbedString scrubbedString) {
        this.callId = str;
        this.soundOn = z;
        this.lightsOn = z2;
        this.promptId = str2;
        this.timeouts = list;
        this.incomingTTSURL = scrubbedString;
    }
}
