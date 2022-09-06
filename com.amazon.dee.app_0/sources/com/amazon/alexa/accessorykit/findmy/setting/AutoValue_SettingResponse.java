package com.amazon.alexa.accessorykit.findmy.setting;

import com.amazon.alexa.accessorykit.findmy.setting.SettingResponse;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public final class AutoValue_SettingResponse extends SettingResponse {
    private final SettingResponse.Enablement settingValue;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_SettingResponse(SettingResponse.Enablement enablement) {
        if (enablement != null) {
            this.settingValue = enablement;
            return;
        }
        throw new NullPointerException("Null settingValue");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SettingResponse)) {
            return false;
        }
        return this.settingValue.equals(((SettingResponse) obj).getSettingValue());
    }

    @Override // com.amazon.alexa.accessorykit.findmy.setting.SettingResponse
    public SettingResponse.Enablement getSettingValue() {
        return this.settingValue;
    }

    public int hashCode() {
        return this.settingValue.hashCode() ^ 1000003;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SettingResponse{settingValue=");
        outline107.append(this.settingValue);
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }
}
