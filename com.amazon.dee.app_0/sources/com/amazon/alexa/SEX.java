package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.utils.TimeProvider;
import dagger.Lazy;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
/* compiled from: ReportCancelledResultTask.java */
/* loaded from: classes.dex */
public class SEX extends QIr {
    public static final String LPk = "SEX";
    public final pRk Mlj;
    public final XWx yPL;
    public final long zzR;

    public SEX(AtomicReference<WSC> atomicReference, AlexaClientEventBus alexaClientEventBus, IUt iUt, Lazy<PersistentStorage> lazy, TimeProvider timeProvider, XWx xWx, pRk prk, long j, Map<XWx, WSC> map, Map<DialogRequestIdentifier, XWx> map2) {
        super(atomicReference, alexaClientEventBus, iUt, lazy, timeProvider, map, map2);
        this.yPL = xWx;
        this.Mlj = prk;
        this.zzR = j;
    }

    @Override // com.amazon.alexa.LYb
    public String BIo() {
        return LPk;
    }

    @Override // java.lang.Runnable
    public void run() {
        WSC zZm = zZm();
        if (zZm(this.yPL)) {
            Log.i(LPk, String.format("Reporting cancelled for %s", this.yPL));
            zZm(zZm, Pmp.CANCEL, this.Mlj, this.zzR);
        }
        if (!BIo(this.yPL)) {
            Log.w(LPk, String.format("Attempted to cancel voice interaction %s that was not registered", this.yPL));
        }
    }
}
