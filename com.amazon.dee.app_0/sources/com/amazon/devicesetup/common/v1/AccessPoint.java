package com.amazon.devicesetup.common.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class AccessPoint implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetup.common.v1.AccessPoint");
    private String bssid;
    private String ssid;

    public boolean equals(Object obj) {
        if (!(obj instanceof AccessPoint)) {
            return false;
        }
        AccessPoint accessPoint = (AccessPoint) obj;
        return Helper.equals(this.ssid, accessPoint.ssid) && Helper.equals(this.bssid, accessPoint.bssid);
    }

    public String getBssid() {
        return this.bssid;
    }

    public String getSsid() {
        return this.ssid;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.ssid, this.bssid);
    }

    public void setBssid(String str) {
        this.bssid = str;
    }

    public void setSsid(String str) {
        this.ssid = str;
    }
}
