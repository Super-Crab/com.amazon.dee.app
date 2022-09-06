package com.amazon.clouddrive.cdasdk.dps.event;
/* loaded from: classes11.dex */
public class RecordEventResponse {
    protected boolean canEqual(Object obj) {
        return obj instanceof RecordEventResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof RecordEventResponse) && ((RecordEventResponse) obj).canEqual(this);
    }

    public int hashCode() {
        return 1;
    }

    public String toString() {
        return "RecordEventResponse()";
    }
}
