package com.amazon.clouddrive.cdasdk.onelens.common;
/* loaded from: classes11.dex */
public class OneLensRequest {
    protected boolean canEqual(Object obj) {
        return obj instanceof OneLensRequest;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof OneLensRequest) && ((OneLensRequest) obj).canEqual(this);
    }

    public int hashCode() {
        return 1;
    }

    public String toString() {
        return "OneLensRequest()";
    }
}
