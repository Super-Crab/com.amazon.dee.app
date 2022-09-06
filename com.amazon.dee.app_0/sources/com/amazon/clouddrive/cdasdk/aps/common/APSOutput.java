package com.amazon.clouddrive.cdasdk.aps.common;
/* loaded from: classes11.dex */
public class APSOutput {
    protected boolean canEqual(Object obj) {
        return obj instanceof APSOutput;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof APSOutput) && ((APSOutput) obj).canEqual(this);
    }

    public int hashCode() {
        return 1;
    }

    public String toString() {
        return "APSOutput()";
    }
}
