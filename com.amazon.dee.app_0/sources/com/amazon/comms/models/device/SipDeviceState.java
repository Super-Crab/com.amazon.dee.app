package com.amazon.comms.models.device;

import com.amazon.comms.models.sip.SIPContact;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class SipDeviceState {
    private String CLIENT_IDENTIFIER;
    private String deviceSentAbsoluteTimestamp;
    private String deviceSentRelativeTimestamp;
    private SipCallState sipCallState;

    /* loaded from: classes11.dex */
    public static class SipCallState {
        private SIPContact callActive;
        private long callConnectDurationInMillis;
        private String callId;
        private String callProvider;
        private String callState;
        private long callTotalDurationInMillis;
        private SIPContact callee;
        private SIPContact caller;
        private boolean isDropIn;
        private String side;
        private String sipCallId;
        private boolean sipRegistered;

        /* loaded from: classes11.dex */
        public static class SipCallStateBuilder {
            private SIPContact callActive;
            private long callConnectDurationInMillis;
            private String callId;
            private String callProvider;
            private String callState;
            private long callTotalDurationInMillis;
            private SIPContact callee;
            private SIPContact caller;
            private boolean isDropIn;
            private String side;
            private String sipCallId;
            private boolean sipRegistered;

            SipCallStateBuilder() {
            }

            public SipCallState build() {
                return new SipCallState(this.callId, this.side, this.callState, this.sipCallId, this.callProvider, this.callTotalDurationInMillis, this.callConnectDurationInMillis, this.isDropIn, this.callee, this.caller, this.callActive, this.sipRegistered);
            }

            public SipCallStateBuilder callActive(SIPContact sIPContact) {
                this.callActive = sIPContact;
                return this;
            }

            public SipCallStateBuilder callConnectDurationInMillis(long j) {
                this.callConnectDurationInMillis = j;
                return this;
            }

            public SipCallStateBuilder callId(String str) {
                this.callId = str;
                return this;
            }

            public SipCallStateBuilder callProvider(String str) {
                this.callProvider = str;
                return this;
            }

            public SipCallStateBuilder callState(String str) {
                this.callState = str;
                return this;
            }

            public SipCallStateBuilder callTotalDurationInMillis(long j) {
                this.callTotalDurationInMillis = j;
                return this;
            }

            public SipCallStateBuilder callee(SIPContact sIPContact) {
                this.callee = sIPContact;
                return this;
            }

            public SipCallStateBuilder caller(SIPContact sIPContact) {
                this.caller = sIPContact;
                return this;
            }

            public SipCallStateBuilder isDropIn(boolean z) {
                this.isDropIn = z;
                return this;
            }

            public SipCallStateBuilder side(String str) {
                this.side = str;
                return this;
            }

            public SipCallStateBuilder sipCallId(String str) {
                this.sipCallId = str;
                return this;
            }

            public SipCallStateBuilder sipRegistered(boolean z) {
                this.sipRegistered = z;
                return this;
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SipDeviceState.SipCallState.SipCallStateBuilder(callId=");
                outline107.append(this.callId);
                outline107.append(", side=");
                outline107.append(this.side);
                outline107.append(", callState=");
                outline107.append(this.callState);
                outline107.append(", sipCallId=");
                outline107.append(this.sipCallId);
                outline107.append(", callProvider=");
                outline107.append(this.callProvider);
                outline107.append(", callTotalDurationInMillis=");
                outline107.append(this.callTotalDurationInMillis);
                outline107.append(", callConnectDurationInMillis=");
                outline107.append(this.callConnectDurationInMillis);
                outline107.append(", isDropIn=");
                outline107.append(this.isDropIn);
                outline107.append(", callee=");
                outline107.append(this.callee);
                outline107.append(", caller=");
                outline107.append(this.caller);
                outline107.append(", callActive=");
                outline107.append(this.callActive);
                outline107.append(", sipRegistered=");
                return GeneratedOutlineSupport1.outline97(outline107, this.sipRegistered, ")");
            }
        }

        public SipCallState() {
        }

        public static SipCallStateBuilder builder() {
            return new SipCallStateBuilder();
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof SipCallState;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof SipCallState)) {
                return false;
            }
            SipCallState sipCallState = (SipCallState) obj;
            if (!sipCallState.canEqual(this)) {
                return false;
            }
            String callId = getCallId();
            String callId2 = sipCallState.getCallId();
            if (callId != null ? !callId.equals(callId2) : callId2 != null) {
                return false;
            }
            String side = getSide();
            String side2 = sipCallState.getSide();
            if (side != null ? !side.equals(side2) : side2 != null) {
                return false;
            }
            String callState = getCallState();
            String callState2 = sipCallState.getCallState();
            if (callState != null ? !callState.equals(callState2) : callState2 != null) {
                return false;
            }
            String sipCallId = getSipCallId();
            String sipCallId2 = sipCallState.getSipCallId();
            if (sipCallId != null ? !sipCallId.equals(sipCallId2) : sipCallId2 != null) {
                return false;
            }
            String callProvider = getCallProvider();
            String callProvider2 = sipCallState.getCallProvider();
            if (callProvider != null ? !callProvider.equals(callProvider2) : callProvider2 != null) {
                return false;
            }
            if (getCallTotalDurationInMillis() != sipCallState.getCallTotalDurationInMillis() || getCallConnectDurationInMillis() != sipCallState.getCallConnectDurationInMillis() || getIsDropIn() != sipCallState.getIsDropIn()) {
                return false;
            }
            SIPContact callee = getCallee();
            SIPContact callee2 = sipCallState.getCallee();
            if (callee != null ? !callee.equals(callee2) : callee2 != null) {
                return false;
            }
            SIPContact caller = getCaller();
            SIPContact caller2 = sipCallState.getCaller();
            if (caller != null ? !caller.equals(caller2) : caller2 != null) {
                return false;
            }
            SIPContact callActive = getCallActive();
            SIPContact callActive2 = sipCallState.getCallActive();
            if (callActive != null ? !callActive.equals(callActive2) : callActive2 != null) {
                return false;
            }
            return getSipRegistered() == sipCallState.getSipRegistered();
        }

        public SIPContact getCallActive() {
            return this.callActive;
        }

        public long getCallConnectDurationInMillis() {
            return this.callConnectDurationInMillis;
        }

        public String getCallId() {
            return this.callId;
        }

        public String getCallProvider() {
            return this.callProvider;
        }

        public String getCallState() {
            return this.callState;
        }

        public long getCallTotalDurationInMillis() {
            return this.callTotalDurationInMillis;
        }

        public SIPContact getCallee() {
            return this.callee;
        }

        public SIPContact getCaller() {
            return this.caller;
        }

        public boolean getIsDropIn() {
            return this.isDropIn;
        }

        public String getSide() {
            return this.side;
        }

        public String getSipCallId() {
            return this.sipCallId;
        }

        public boolean getSipRegistered() {
            return this.sipRegistered;
        }

        public int hashCode() {
            String callId = getCallId();
            int i = 43;
            int hashCode = callId == null ? 43 : callId.hashCode();
            String side = getSide();
            int hashCode2 = ((hashCode + 59) * 59) + (side == null ? 43 : side.hashCode());
            String callState = getCallState();
            int hashCode3 = (hashCode2 * 59) + (callState == null ? 43 : callState.hashCode());
            String sipCallId = getSipCallId();
            int hashCode4 = (hashCode3 * 59) + (sipCallId == null ? 43 : sipCallId.hashCode());
            String callProvider = getCallProvider();
            int hashCode5 = (hashCode4 * 59) + (callProvider == null ? 43 : callProvider.hashCode());
            long callTotalDurationInMillis = getCallTotalDurationInMillis();
            int i2 = (hashCode5 * 59) + ((int) (callTotalDurationInMillis ^ (callTotalDurationInMillis >>> 32)));
            long callConnectDurationInMillis = getCallConnectDurationInMillis();
            int i3 = 79;
            int i4 = (((i2 * 59) + ((int) (callConnectDurationInMillis ^ (callConnectDurationInMillis >>> 32)))) * 59) + (getIsDropIn() ? 79 : 97);
            SIPContact callee = getCallee();
            int hashCode6 = (i4 * 59) + (callee == null ? 43 : callee.hashCode());
            SIPContact caller = getCaller();
            int hashCode7 = (hashCode6 * 59) + (caller == null ? 43 : caller.hashCode());
            SIPContact callActive = getCallActive();
            int i5 = hashCode7 * 59;
            if (callActive != null) {
                i = callActive.hashCode();
            }
            int i6 = (i5 + i) * 59;
            if (!getSipRegistered()) {
                i3 = 97;
            }
            return i6 + i3;
        }

        public void setCallActive(SIPContact sIPContact) {
            this.callActive = sIPContact;
        }

        public void setCallConnectDurationInMillis(long j) {
            this.callConnectDurationInMillis = j;
        }

        public void setCallId(String str) {
            this.callId = str;
        }

        public void setCallProvider(String str) {
            this.callProvider = str;
        }

        public void setCallState(String str) {
            this.callState = str;
        }

        public void setCallTotalDurationInMillis(long j) {
            this.callTotalDurationInMillis = j;
        }

        public void setCallee(SIPContact sIPContact) {
            this.callee = sIPContact;
        }

        public void setCaller(SIPContact sIPContact) {
            this.caller = sIPContact;
        }

        public void setIsDropIn(boolean z) {
            this.isDropIn = z;
        }

        public void setSide(String str) {
            this.side = str;
        }

        public void setSipCallId(String str) {
            this.sipCallId = str;
        }

        public void setSipRegistered(boolean z) {
            this.sipRegistered = z;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SipDeviceState.SipCallState(callId=");
            outline107.append(getCallId());
            outline107.append(", side=");
            outline107.append(getSide());
            outline107.append(", callState=");
            outline107.append(getCallState());
            outline107.append(", sipCallId=");
            outline107.append(getSipCallId());
            outline107.append(", callProvider=");
            outline107.append(getCallProvider());
            outline107.append(", callTotalDurationInMillis=");
            outline107.append(getCallTotalDurationInMillis());
            outline107.append(", callConnectDurationInMillis=");
            outline107.append(getCallConnectDurationInMillis());
            outline107.append(", isDropIn=");
            outline107.append(getIsDropIn());
            outline107.append(", callee=");
            outline107.append(getCallee());
            outline107.append(", caller=");
            outline107.append(getCaller());
            outline107.append(", callActive=");
            outline107.append(getCallActive());
            outline107.append(", sipRegistered=");
            outline107.append(getSipRegistered());
            outline107.append(")");
            return outline107.toString();
        }

        public SipCallState(String str, String str2, String str3, String str4, String str5, long j, long j2, boolean z, SIPContact sIPContact, SIPContact sIPContact2, SIPContact sIPContact3, boolean z2) {
            this.callId = str;
            this.side = str2;
            this.callState = str3;
            this.sipCallId = str4;
            this.callProvider = str5;
            this.callTotalDurationInMillis = j;
            this.callConnectDurationInMillis = j2;
            this.isDropIn = z;
            this.callee = sIPContact;
            this.caller = sIPContact2;
            this.callActive = sIPContact3;
            this.sipRegistered = z2;
        }
    }

    /* loaded from: classes11.dex */
    public static class SipDeviceStateBuilder {
        private String CLIENT_IDENTIFIER = "Domain:Application:Communications:Calling";
        private String deviceSentAbsoluteTimestamp;
        private String deviceSentRelativeTimestamp;
        private SipCallState sipCallState;

        SipDeviceStateBuilder() {
        }

        public SipDeviceStateBuilder CLIENT_IDENTIFIER(String str) {
            this.CLIENT_IDENTIFIER = str;
            return this;
        }

        public SipDeviceState build() {
            return new SipDeviceState(this.CLIENT_IDENTIFIER, this.sipCallState, this.deviceSentAbsoluteTimestamp, this.deviceSentRelativeTimestamp);
        }

        public SipDeviceStateBuilder deviceSentAbsoluteTimestamp(String str) {
            this.deviceSentAbsoluteTimestamp = str;
            return this;
        }

        public SipDeviceStateBuilder deviceSentRelativeTimestamp(String str) {
            this.deviceSentRelativeTimestamp = str;
            return this;
        }

        public SipDeviceStateBuilder sipCallState(SipCallState sipCallState) {
            this.sipCallState = sipCallState;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SipDeviceState.SipDeviceStateBuilder(CLIENT_IDENTIFIER=");
            outline107.append(this.CLIENT_IDENTIFIER);
            outline107.append(", sipCallState=");
            outline107.append(this.sipCallState);
            outline107.append(", deviceSentAbsoluteTimestamp=");
            outline107.append(this.deviceSentAbsoluteTimestamp);
            outline107.append(", deviceSentRelativeTimestamp=");
            return GeneratedOutlineSupport1.outline91(outline107, this.deviceSentRelativeTimestamp, ")");
        }
    }

    public SipDeviceState() {
    }

    public static SipDeviceStateBuilder builder() {
        return new SipDeviceStateBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof SipDeviceState;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SipDeviceState)) {
            return false;
        }
        SipDeviceState sipDeviceState = (SipDeviceState) obj;
        if (!sipDeviceState.canEqual(this)) {
            return false;
        }
        String client_identifier = getCLIENT_IDENTIFIER();
        String client_identifier2 = sipDeviceState.getCLIENT_IDENTIFIER();
        if (client_identifier != null ? !client_identifier.equals(client_identifier2) : client_identifier2 != null) {
            return false;
        }
        SipCallState sipCallState = getSipCallState();
        SipCallState sipCallState2 = sipDeviceState.getSipCallState();
        if (sipCallState != null ? !sipCallState.equals(sipCallState2) : sipCallState2 != null) {
            return false;
        }
        String deviceSentAbsoluteTimestamp = getDeviceSentAbsoluteTimestamp();
        String deviceSentAbsoluteTimestamp2 = sipDeviceState.getDeviceSentAbsoluteTimestamp();
        if (deviceSentAbsoluteTimestamp != null ? !deviceSentAbsoluteTimestamp.equals(deviceSentAbsoluteTimestamp2) : deviceSentAbsoluteTimestamp2 != null) {
            return false;
        }
        String deviceSentRelativeTimestamp = getDeviceSentRelativeTimestamp();
        String deviceSentRelativeTimestamp2 = sipDeviceState.getDeviceSentRelativeTimestamp();
        return deviceSentRelativeTimestamp != null ? deviceSentRelativeTimestamp.equals(deviceSentRelativeTimestamp2) : deviceSentRelativeTimestamp2 == null;
    }

    public String getCLIENT_IDENTIFIER() {
        return this.CLIENT_IDENTIFIER;
    }

    public String getDeviceSentAbsoluteTimestamp() {
        return this.deviceSentAbsoluteTimestamp;
    }

    public String getDeviceSentRelativeTimestamp() {
        return this.deviceSentRelativeTimestamp;
    }

    public SipCallState getSipCallState() {
        return this.sipCallState;
    }

    public int hashCode() {
        String client_identifier = getCLIENT_IDENTIFIER();
        int i = 43;
        int hashCode = client_identifier == null ? 43 : client_identifier.hashCode();
        SipCallState sipCallState = getSipCallState();
        int hashCode2 = ((hashCode + 59) * 59) + (sipCallState == null ? 43 : sipCallState.hashCode());
        String deviceSentAbsoluteTimestamp = getDeviceSentAbsoluteTimestamp();
        int hashCode3 = (hashCode2 * 59) + (deviceSentAbsoluteTimestamp == null ? 43 : deviceSentAbsoluteTimestamp.hashCode());
        String deviceSentRelativeTimestamp = getDeviceSentRelativeTimestamp();
        int i2 = hashCode3 * 59;
        if (deviceSentRelativeTimestamp != null) {
            i = deviceSentRelativeTimestamp.hashCode();
        }
        return i2 + i;
    }

    public void setCLIENT_IDENTIFIER(String str) {
        this.CLIENT_IDENTIFIER = str;
    }

    public void setDeviceSentAbsoluteTimestamp(String str) {
        this.deviceSentAbsoluteTimestamp = str;
    }

    public void setDeviceSentRelativeTimestamp(String str) {
        this.deviceSentRelativeTimestamp = str;
    }

    public void setSipCallState(SipCallState sipCallState) {
        this.sipCallState = sipCallState;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SipDeviceState(CLIENT_IDENTIFIER=");
        outline107.append(getCLIENT_IDENTIFIER());
        outline107.append(", sipCallState=");
        outline107.append(getSipCallState());
        outline107.append(", deviceSentAbsoluteTimestamp=");
        outline107.append(getDeviceSentAbsoluteTimestamp());
        outline107.append(", deviceSentRelativeTimestamp=");
        outline107.append(getDeviceSentRelativeTimestamp());
        outline107.append(")");
        return outline107.toString();
    }

    public SipDeviceState(String str, SipCallState sipCallState, String str2, String str3) {
        this.CLIENT_IDENTIFIER = str;
        this.sipCallState = sipCallState;
        this.deviceSentAbsoluteTimestamp = str2;
        this.deviceSentRelativeTimestamp = str3;
    }
}
