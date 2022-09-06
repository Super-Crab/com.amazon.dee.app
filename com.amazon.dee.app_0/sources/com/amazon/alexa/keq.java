package com.amazon.alexa;

import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.List;
/* compiled from: $AutoValue_StateReportEventPayload.java */
/* loaded from: classes.dex */
public abstract class keq extends rff {
    public final List<Message> zZm;

    public keq(List<Message> list) {
        if (list != null) {
            this.zZm = list;
            return;
        }
        throw new NullPointerException("Null settingStateList");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof rff)) {
            return false;
        }
        return this.zZm.equals(((keq) obj).zZm);
    }

    public int hashCode() {
        return this.zZm.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("StateReportEventPayload{settingStateList="), this.zZm, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
