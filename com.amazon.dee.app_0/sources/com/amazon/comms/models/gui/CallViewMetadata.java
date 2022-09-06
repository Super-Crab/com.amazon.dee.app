package com.amazon.comms.models.gui;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes11.dex */
public class CallViewMetadata {
    private String callId;
    private List<IdentityMetadata> peersInfo;
    private Setting setting;
    private IdentityMetadata userInfo;

    /* loaded from: classes11.dex */
    public static class CallViewMetadataBuilder {
        private String callId;
        private List<IdentityMetadata> peersInfo;
        private Setting setting;
        private IdentityMetadata userInfo;

        CallViewMetadataBuilder() {
        }

        public CallViewMetadata build() {
            return new CallViewMetadata(this.callId, this.userInfo, this.peersInfo, this.setting);
        }

        public CallViewMetadataBuilder callId(String str) {
            this.callId = str;
            return this;
        }

        public CallViewMetadataBuilder peersInfo(List<IdentityMetadata> list) {
            this.peersInfo = list;
            return this;
        }

        public CallViewMetadataBuilder setting(Setting setting) {
            this.setting = setting;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CallViewMetadata.CallViewMetadataBuilder(callId=");
            outline107.append(this.callId);
            outline107.append(", userInfo=");
            outline107.append(this.userInfo);
            outline107.append(", peersInfo=");
            outline107.append(this.peersInfo);
            outline107.append(", setting=");
            outline107.append(this.setting);
            outline107.append(")");
            return outline107.toString();
        }

        public CallViewMetadataBuilder userInfo(IdentityMetadata identityMetadata) {
            this.userInfo = identityMetadata;
            return this;
        }
    }

    public CallViewMetadata() {
    }

    public static CallViewMetadataBuilder builder() {
        return new CallViewMetadataBuilder();
    }

    public String getCallId() {
        return this.callId;
    }

    public List<IdentityMetadata> getPeersInfo() {
        return this.peersInfo;
    }

    public Setting getSetting() {
        return this.setting;
    }

    public IdentityMetadata getUserInfo() {
        return this.userInfo;
    }

    public void setCallId(String str) {
        this.callId = str;
    }

    public void setPeersInfo(List<IdentityMetadata> list) {
        this.peersInfo = list;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }

    public void setUserInfo(IdentityMetadata identityMetadata) {
        this.userInfo = identityMetadata;
    }

    private CallViewMetadata(String str, IdentityMetadata identityMetadata, List<IdentityMetadata> list, Setting setting) {
        this.callId = str;
        this.userInfo = identityMetadata;
        this.peersInfo = list;
        this.setting = setting;
    }
}
