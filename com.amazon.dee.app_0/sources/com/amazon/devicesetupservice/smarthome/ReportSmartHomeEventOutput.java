package com.amazon.devicesetupservice.smarthome;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class ReportSmartHomeEventOutput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.smarthome.ReportSmartHomeEventOutput");

    public boolean equals(Object obj) {
        return obj instanceof ReportSmartHomeEventOutput;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode));
    }
}
