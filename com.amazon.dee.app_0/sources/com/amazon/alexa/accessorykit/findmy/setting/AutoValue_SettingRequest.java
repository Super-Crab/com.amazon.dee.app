package com.amazon.alexa.accessorykit.findmy.setting;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public final class AutoValue_SettingRequest extends SettingRequest {
    private final String accountId;
    private final String deviceType;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_SettingRequest(String str, String str2) {
        if (str != null) {
            this.accountId = str;
            if (str2 != null) {
                this.deviceType = str2;
                return;
            }
            throw new NullPointerException("Null deviceType");
        }
        throw new NullPointerException("Null accountId");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SettingRequest)) {
            return false;
        }
        SettingRequest settingRequest = (SettingRequest) obj;
        return this.accountId.equals(settingRequest.getAccountId()) && this.deviceType.equals(settingRequest.getDeviceType());
    }

    @Override // com.amazon.alexa.accessorykit.findmy.setting.SettingRequest
    public String getAccountId() {
        return this.accountId;
    }

    @Override // com.amazon.alexa.accessorykit.findmy.setting.SettingRequest
    public String getDeviceType() {
        return this.deviceType;
    }

    public int hashCode() {
        return ((this.accountId.hashCode() ^ 1000003) * 1000003) ^ this.deviceType.hashCode();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SettingRequest{accountId=");
        outline107.append(this.accountId);
        outline107.append(", deviceType=");
        return GeneratedOutlineSupport1.outline91(outline107, this.deviceType, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
