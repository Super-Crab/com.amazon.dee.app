package com.amazon.devicesetupservice.wififfs;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public abstract class DeviceAuthenticatedInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.wififfs.DeviceAuthenticatedInput");
    private String deviceCertificate;

    public boolean equals(Object obj) {
        if (!(obj instanceof DeviceAuthenticatedInput)) {
            return false;
        }
        return Helper.equals(this.deviceCertificate, ((DeviceAuthenticatedInput) obj).deviceCertificate);
    }

    public String getDeviceCertificate() {
        return this.deviceCertificate;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.deviceCertificate);
    }

    public void setDeviceCertificate(String str) {
        this.deviceCertificate = str;
    }
}
