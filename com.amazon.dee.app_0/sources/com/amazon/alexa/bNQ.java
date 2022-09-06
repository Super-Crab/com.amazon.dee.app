package com.amazon.alexa;

import android.os.RemoteException;
import com.amazon.alexa.api.UserPerceivedLatencyData;
import com.amazon.alexa.api.UserPerceivedLatencyListenerProxy;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import java.util.Objects;
/* compiled from: UserPerceivedLatencyListenerWrapper.java */
/* loaded from: classes.dex */
public class bNQ {
    public final UserPerceivedLatencyListenerProxy zZm;

    public bNQ(UserPerceivedLatencyListenerProxy userPerceivedLatencyListenerProxy) {
        this.zZm = userPerceivedLatencyListenerProxy;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && bNQ.class == obj.getClass()) {
            return Objects.equals(this.zZm, ((bNQ) obj).zZm);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.zZm);
    }

    public void zZm(XWx xWx, DialogRequestIdentifier dialogRequestIdentifier, long j, long j2, long j3, long j4) throws RemoteException {
        this.zZm.onLatencyData(xWx.getValue(), UserPerceivedLatencyData.toBundle(new UserPerceivedLatencyData(dialogRequestIdentifier.getValue(), j, j2, j3, j4)));
    }
}
