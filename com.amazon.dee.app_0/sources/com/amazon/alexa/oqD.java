package com.amazon.alexa;

import android.app.Notification;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_EnterUndismissibleStateEvent.java */
/* loaded from: classes.dex */
public final class oqD extends MiL {
    public final int BIo;
    public final Notification zQM;

    public oqD(int i, Notification notification) {
        this.BIo = i;
        if (notification != null) {
            this.zQM = notification;
            return;
        }
        throw new NullPointerException("Null foregroundNotification");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MiL)) {
            return false;
        }
        oqD oqd = (oqD) obj;
        return this.BIo == oqd.BIo && this.zQM.equals(oqd.zQM);
    }

    public int hashCode() {
        return ((this.BIo ^ 1000003) * 1000003) ^ this.zQM.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("EnterUndismissibleStateEvent{foregroundNotificationId=");
        zZm.append(this.BIo);
        zZm.append(", foregroundNotification=");
        return C0179Pya.BIo(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
