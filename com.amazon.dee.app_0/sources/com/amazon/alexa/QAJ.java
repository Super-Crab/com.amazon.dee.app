package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.utils.TimeProvider;
import dagger.Lazy;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
/* compiled from: TeardownTask.java */
/* loaded from: classes.dex */
public class QAJ extends QIr {
    public static final String LPk = "QAJ";

    public QAJ(AtomicReference<WSC> atomicReference, AlexaClientEventBus alexaClientEventBus, IUt iUt, Lazy<PersistentStorage> lazy, TimeProvider timeProvider, Map<XWx, WSC> map, Map<DialogRequestIdentifier, XWx> map2) {
        super(atomicReference, alexaClientEventBus, iUt, lazy, timeProvider, map, map2);
    }

    @Override // com.amazon.alexa.LYb
    public String BIo() {
        return LPk;
    }

    @Override // java.lang.Runnable
    public void run() {
        String str = LPk;
        Object[] objArr = new Object[1];
        objArr[0] = zQM() ? "in progress" : "idle";
        Log.i(str, String.format("Tearing down while state is %s", objArr));
        for (XWx xWx : this.Qle.keySet()) {
            List<VZt> BIo = ((iQX) this.jiA).BIo(xWx);
            if (BIo.isEmpty()) {
                zZm(bij.INTERNAL_CLIENT_ERROR_INCOMPLETE_INTERACTION, this.zZm.elapsedRealTime());
            } else {
                if (BIo.size() > 1) {
                    String BIo2 = BIo();
                    Log.w(BIo2, "Multiple metric entries in client database for " + xWx);
                }
                zZm(zZm(BIo.get(0)), 0L);
            }
        }
    }
}
