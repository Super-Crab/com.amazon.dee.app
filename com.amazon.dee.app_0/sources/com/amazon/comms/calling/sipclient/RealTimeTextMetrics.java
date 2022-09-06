package com.amazon.comms.calling.sipclient;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class RealTimeTextMetrics {
    private String enablementState;
    private String requestState;

    /* loaded from: classes11.dex */
    public enum RealTimeTextEnablementState {
        DISABLED,
        ENABLED
    }

    /* loaded from: classes11.dex */
    public enum RealTimeTextRequestState {
        NOT_REQUESTED,
        REQUESTED
    }

    public RealTimeTextMetrics(String str, String str2) {
        this.requestState = str;
        this.enablementState = str2;
    }

    public String getEnablementState() {
        return this.enablementState;
    }

    public String getRequestState() {
        return this.requestState;
    }

    public void setEnablementState(String str) {
        this.enablementState = str;
    }

    public void setRequestState(String str) {
        this.requestState = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RealTimeTextMetrics(requestState=");
        outline107.append(getRequestState());
        outline107.append(", enablementState=");
        outline107.append(getEnablementState());
        outline107.append(")");
        return outline107.toString();
    }
}
