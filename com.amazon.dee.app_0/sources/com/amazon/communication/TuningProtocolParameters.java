package com.amazon.communication;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
/* loaded from: classes12.dex */
public class TuningProtocolParameters {
    private Map<String, String> mParameters;
    private String mProtocolName;

    public TuningProtocolParameters() {
    }

    public Map<String, String> getParameters() {
        return this.mParameters;
    }

    public String getProtocolName() {
        return this.mProtocolName;
    }

    public void setParameters(Map<String, String> map) {
        this.mParameters = map;
    }

    public void setProtocolName(String str) {
        this.mProtocolName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TuningProtocolParameters [ProtocolName=");
        outline107.append(this.mProtocolName);
        outline107.append(", Parameters=");
        outline107.append(this.mParameters);
        outline107.append("]");
        return outline107.toString();
    }

    public TuningProtocolParameters(String str, Map<String, String> map) {
        this.mProtocolName = str;
        this.mParameters = map;
    }
}
