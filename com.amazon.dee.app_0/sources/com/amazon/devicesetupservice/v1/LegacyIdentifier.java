package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class LegacyIdentifier implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.LegacyIdentifier");
    private String deviceType;
    private String dsn;

    public boolean equals(Object obj) {
        if (!(obj instanceof LegacyIdentifier)) {
            return false;
        }
        LegacyIdentifier legacyIdentifier = (LegacyIdentifier) obj;
        return Helper.equals(this.deviceType, legacyIdentifier.deviceType) && Helper.equals(this.dsn, legacyIdentifier.dsn);
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public String getDsn() {
        return this.dsn;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.deviceType, this.dsn);
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public void setDsn(String str) {
        this.dsn = str;
    }
}
