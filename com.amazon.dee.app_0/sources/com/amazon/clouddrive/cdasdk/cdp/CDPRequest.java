package com.amazon.clouddrive.cdasdk.cdp;
/* loaded from: classes11.dex */
public class CDPRequest {
    protected boolean canEqual(Object obj) {
        return obj instanceof CDPRequest;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof CDPRequest) && ((CDPRequest) obj).canEqual(this);
    }

    public int hashCode() {
        return 1;
    }

    public String toString() {
        return "CDPRequest()";
    }
}
