package com.amazon.alexa;

import android.os.Bundle;
import com.amazon.alexa.api.UiEventName;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_UiEventReceivedEvent.java */
/* renamed from: com.amazon.alexa.vPD  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0236vPD extends AQg {
    public final UiEventName BIo;
    public final Bundle zQM;

    public C0236vPD(UiEventName uiEventName, Bundle bundle) {
        if (uiEventName != null) {
            this.BIo = uiEventName;
            if (bundle != null) {
                this.zQM = bundle;
                return;
            }
            throw new NullPointerException("Null eventData");
        }
        throw new NullPointerException("Null uiEventName");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AQg)) {
            return false;
        }
        C0236vPD c0236vPD = (C0236vPD) obj;
        return this.BIo.equals(c0236vPD.BIo) && this.zQM.equals(c0236vPD.zQM);
    }

    public int hashCode() {
        return ((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("UiEventReceivedEvent{uiEventName=");
        zZm.append(this.BIo);
        zZm.append(", eventData=");
        return C0179Pya.BIo(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
