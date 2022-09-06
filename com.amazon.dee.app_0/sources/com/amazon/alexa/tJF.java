package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.utils.TimeProvider;
import dagger.Lazy;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
/* compiled from: ReportSuccessResultTask.java */
/* loaded from: classes.dex */
public class tJF extends QIr {
    public static final String LPk = "tJF";
    public final long Mlj;
    public final DialogRequestIdentifier yPL;

    public tJF(AtomicReference<WSC> atomicReference, AlexaClientEventBus alexaClientEventBus, IUt iUt, Lazy<PersistentStorage> lazy, TimeProvider timeProvider, DialogRequestIdentifier dialogRequestIdentifier, long j, Map<XWx, WSC> map, Map<DialogRequestIdentifier, XWx> map2) {
        super(atomicReference, alexaClientEventBus, iUt, lazy, timeProvider, map, map2);
        this.yPL = dialogRequestIdentifier;
        this.Mlj = j;
    }

    @Override // com.amazon.alexa.LYb
    public String BIo() {
        return LPk;
    }

    @Override // java.lang.Runnable
    public void run() {
        WSC zZm = zZm();
        if (zZm(this.yPL)) {
            Log.i(LPk, String.format("Reporting success for %s", ((nFo) zZm).zQM));
            zZm(zZm, Pmp.SUCCESS, null, null, this.Mlj);
        }
        if (!BIo(this.yPL)) {
            Log.w(LPk, String.format("Attempted to abandon voice interaction %s that was not registered", this.yPL));
        }
    }
}
