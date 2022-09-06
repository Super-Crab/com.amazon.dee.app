package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.nFo;
import com.amazon.alexa.utils.TimeProvider;
import dagger.Lazy;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
/* compiled from: RegisterTextAttemptTask.java */
/* loaded from: classes.dex */
public class VlP extends UeS {
    public static final String LPk = APq.class.getSimpleName();
    public final String Mlj;
    public final long lOf;
    public final XWx yPL;
    public final String zzR;

    public VlP(AtomicReference<WSC> atomicReference, AlexaClientEventBus alexaClientEventBus, IUt iUt, Lazy<PersistentStorage> lazy, TimeProvider timeProvider, XWx xWx, String str, String str2, long j, Map<XWx, WSC> map, Map<DialogRequestIdentifier, XWx> map2) {
        super(atomicReference, alexaClientEventBus, iUt, lazy, timeProvider, map, map2);
        this.yPL = xWx;
        this.Mlj = str;
        this.zzR = str2;
        this.lOf = j;
    }

    @Override // com.amazon.alexa.LYb
    public String BIo() {
        return LPk;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (zQM()) {
            WSC zZm = zZm();
            XWx xWx = this.yPL;
            nFo nfo = (nFo) zZm;
            if (xWx == nfo.zQM) {
                String.format("Attempt %s is already registered", xWx);
                return;
            }
            String str = LPk;
            StringBuilder zZm2 = C0179Pya.zZm("Tried to register multiple text interaction attempts. ");
            zZm2.append(String.format("New: %s vs current: %s", this.yPL, nfo.zQM));
            Log.w(str, zZm2.toString());
        }
        nFo.zZm zzm = (nFo.zZm) WSC.zZm();
        zzm.zZm = this.yPL;
        zzm.zQM = this.Mlj;
        WSC BIo = zzm.zZm(this.zzR).zZm(this.lOf).zZm(YOj.NEW).BIo();
        if (!zZm(this.yPL, BIo)) {
            Log.w(LPk, String.format("Tried to register attempt %s that already exists.", this.yPL));
        }
        BIo(BIo);
    }
}
