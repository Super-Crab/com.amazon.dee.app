package com.amazon.clouddrive.cdasdk.dps.common;
/* loaded from: classes11.dex */
public class DPSRequest {
    protected boolean canEqual(Object obj) {
        return obj instanceof DPSRequest;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof DPSRequest) && ((DPSRequest) obj).canEqual(this);
    }

    public int hashCode() {
        return 1;
    }

    public String toString() {
        return "DPSRequest()";
    }
}
