package com.amazon.alexa;

import android.app.Notification;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_AlexaNotification.java */
/* loaded from: classes.dex */
public final class JfO extends qTx {
    public final int BIo;
    public final boolean zQM;
    public final Notification zZm;

    public JfO(Notification notification, int i, boolean z) {
        if (notification != null) {
            this.zZm = notification;
            this.BIo = i;
            this.zQM = z;
            return;
        }
        throw new NullPointerException("Null notification");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof qTx)) {
            return false;
        }
        JfO jfO = (JfO) obj;
        return this.zZm.equals(jfO.zZm) && this.BIo == jfO.BIo && this.zQM == jfO.zQM;
    }

    public int hashCode() {
        return ((((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo) * 1000003) ^ (this.zQM ? 1231 : 1237);
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("AlexaNotification{notification=");
        zZm.append(this.zZm);
        zZm.append(", id=");
        zZm.append(this.BIo);
        zZm.append(", mediaNotification=");
        return C0179Pya.zZm(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
