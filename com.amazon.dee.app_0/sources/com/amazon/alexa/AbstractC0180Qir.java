package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_UserInactivityReportEventPayload.java */
/* renamed from: com.amazon.alexa.Qir  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractC0180Qir extends YiP {
    public final long zZm;

    public AbstractC0180Qir(long j) {
        this.zZm = j;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof YiP) && this.zZm == ((AbstractC0180Qir) obj).zZm;
    }

    public int hashCode() {
        long j = this.zZm;
        return ((int) (j ^ (j >>> 32))) ^ 1000003;
    }

    public String toString() {
        return C0179Pya.zZm(C0179Pya.zZm("UserInactivityReportEventPayload{inactiveTimeInSeconds="), this.zZm, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
