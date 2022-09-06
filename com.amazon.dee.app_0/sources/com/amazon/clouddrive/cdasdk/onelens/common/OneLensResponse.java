package com.amazon.clouddrive.cdasdk.onelens.common;
/* loaded from: classes11.dex */
public class OneLensResponse {
    protected boolean canEqual(Object obj) {
        return obj instanceof OneLensResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof OneLensResponse) && ((OneLensResponse) obj).canEqual(this);
    }

    public int hashCode() {
        return 1;
    }

    public String toString() {
        return "OneLensResponse()";
    }
}
