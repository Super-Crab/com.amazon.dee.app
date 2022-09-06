package com.amazon.clouddrive.cdasdk.cdds;
/* loaded from: classes11.dex */
public class ServiceRequest {
    protected boolean canEqual(Object obj) {
        return obj instanceof ServiceRequest;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof ServiceRequest) && ((ServiceRequest) obj).canEqual(this);
    }

    public int hashCode() {
        return 1;
    }

    public String toString() {
        return "ServiceRequest()";
    }
}
