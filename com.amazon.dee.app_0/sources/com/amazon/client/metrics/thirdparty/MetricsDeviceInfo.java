package com.amazon.client.metrics.thirdparty;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes11.dex */
public class MetricsDeviceInfo {
    private final Map<String, String> mDeviceInfo = new HashMap();

    public void addDeviceInfoData(String str, String str2) {
        if (str != null) {
            this.mDeviceInfo.put(str, str2);
            return;
        }
        throw new IllegalArgumentException("device info key cannot be null");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && MetricsDeviceInfo.class == obj.getClass() && this.mDeviceInfo.equals(((MetricsDeviceInfo) obj).mDeviceInfo);
    }

    public String getDeviceInfo(String str) {
        return this.mDeviceInfo.get(str);
    }

    public int hashCode() {
        return this.mDeviceInfo.hashCode() + 31;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MetricsDeviceInfo [mDeviceInfo=");
        outline107.append(this.mDeviceInfo);
        outline107.append("]");
        return outline107.toString();
    }

    public Map<String, String> getDeviceInfo() {
        return this.mDeviceInfo;
    }
}
