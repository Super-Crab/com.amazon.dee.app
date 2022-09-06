package com.amazon.comms.models.sip.payloads;

import com.amazon.dee.application.service.common.logging.RedactInLogs;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import java.util.Map;
@RedactInLogs
/* loaded from: classes11.dex */
public class ConfigureCommsSIPPayload implements SIPPayload {
    private String callId;
    private List<Map<String, String>> headers;
    private SIPRegisterInfo registerInfo;
    private Boolean sendPresence;

    /* loaded from: classes11.dex */
    public static class ConfigureCommsSIPPayloadBuilder {
        private String callId;
        private List<Map<String, String>> headers;
        private SIPRegisterInfo registerInfo;
        private Boolean sendPresence;
        private boolean sendPresence$set;

        ConfigureCommsSIPPayloadBuilder() {
        }

        public ConfigureCommsSIPPayload build() {
            return new ConfigureCommsSIPPayload(this.callId, this.registerInfo, this.headers, this.sendPresence$set ? this.sendPresence : ConfigureCommsSIPPayload.$default$sendPresence());
        }

        public ConfigureCommsSIPPayloadBuilder callId(String str) {
            this.callId = str;
            return this;
        }

        public ConfigureCommsSIPPayloadBuilder headers(List<Map<String, String>> list) {
            this.headers = list;
            return this;
        }

        public ConfigureCommsSIPPayloadBuilder registerInfo(SIPRegisterInfo sIPRegisterInfo) {
            this.registerInfo = sIPRegisterInfo;
            return this;
        }

        public ConfigureCommsSIPPayloadBuilder sendPresence(Boolean bool) {
            this.sendPresence = bool;
            this.sendPresence$set = true;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ConfigureCommsSIPPayload.ConfigureCommsSIPPayloadBuilder(callId=");
            outline107.append(this.callId);
            outline107.append(", registerInfo=");
            outline107.append(this.registerInfo);
            outline107.append(", headers=");
            outline107.append(this.headers);
            outline107.append(", sendPresence=");
            outline107.append(this.sendPresence);
            outline107.append(")");
            return outline107.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Boolean $default$sendPresence() {
        return false;
    }

    public ConfigureCommsSIPPayload() {
    }

    public static ConfigureCommsSIPPayloadBuilder builder() {
        return new ConfigureCommsSIPPayloadBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof ConfigureCommsSIPPayload;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ConfigureCommsSIPPayload)) {
            return false;
        }
        ConfigureCommsSIPPayload configureCommsSIPPayload = (ConfigureCommsSIPPayload) obj;
        if (!configureCommsSIPPayload.canEqual(this)) {
            return false;
        }
        String callId = getCallId();
        String callId2 = configureCommsSIPPayload.getCallId();
        if (callId != null ? !callId.equals(callId2) : callId2 != null) {
            return false;
        }
        SIPRegisterInfo registerInfo = getRegisterInfo();
        SIPRegisterInfo registerInfo2 = configureCommsSIPPayload.getRegisterInfo();
        if (registerInfo != null ? !registerInfo.equals(registerInfo2) : registerInfo2 != null) {
            return false;
        }
        List<Map<String, String>> headers = getHeaders();
        List<Map<String, String>> headers2 = configureCommsSIPPayload.getHeaders();
        if (headers != null ? !headers.equals(headers2) : headers2 != null) {
            return false;
        }
        Boolean sendPresence = getSendPresence();
        Boolean sendPresence2 = configureCommsSIPPayload.getSendPresence();
        return sendPresence != null ? sendPresence.equals(sendPresence2) : sendPresence2 == null;
    }

    @Override // com.amazon.comms.models.sip.payloads.SIPPayload
    public String getCallId() {
        return this.callId;
    }

    public List<Map<String, String>> getHeaders() {
        return this.headers;
    }

    public SIPRegisterInfo getRegisterInfo() {
        return this.registerInfo;
    }

    public Boolean getSendPresence() {
        return this.sendPresence;
    }

    public int hashCode() {
        String callId = getCallId();
        int i = 43;
        int hashCode = callId == null ? 43 : callId.hashCode();
        SIPRegisterInfo registerInfo = getRegisterInfo();
        int hashCode2 = ((hashCode + 59) * 59) + (registerInfo == null ? 43 : registerInfo.hashCode());
        List<Map<String, String>> headers = getHeaders();
        int hashCode3 = (hashCode2 * 59) + (headers == null ? 43 : headers.hashCode());
        Boolean sendPresence = getSendPresence();
        int i2 = hashCode3 * 59;
        if (sendPresence != null) {
            i = sendPresence.hashCode();
        }
        return i2 + i;
    }

    public void setCallId(String str) {
        this.callId = str;
    }

    public void setHeaders(List<Map<String, String>> list) {
        this.headers = list;
    }

    public void setRegisterInfo(SIPRegisterInfo sIPRegisterInfo) {
        this.registerInfo = sIPRegisterInfo;
    }

    public void setSendPresence(Boolean bool) {
        this.sendPresence = bool;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ConfigureCommsSIPPayload(callId=");
        outline107.append(getCallId());
        outline107.append(", registerInfo=");
        outline107.append(getRegisterInfo());
        outline107.append(", headers=");
        outline107.append(getHeaders());
        outline107.append(", sendPresence=");
        outline107.append(getSendPresence());
        outline107.append(")");
        return outline107.toString();
    }

    private ConfigureCommsSIPPayload(String str, SIPRegisterInfo sIPRegisterInfo, List<Map<String, String>> list, Boolean bool) {
        this.callId = str;
        this.registerInfo = sIPRegisterInfo;
        this.headers = list;
        this.sendPresence = bool;
    }
}
