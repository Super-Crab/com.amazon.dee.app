package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.utils.validation.Preconditions;
import dagger.Lazy;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
/* compiled from: ReportTextFailureResultTask.java */
/* loaded from: classes.dex */
public class gJe extends UeS {
    public static final String LPk = "gJe";
    public final pRk Mlj;
    public final long lOf;
    public final DialogRequestIdentifier yPL;
    public final Map<String, String> zzR;

    public gJe(AtomicReference<WSC> atomicReference, AlexaClientEventBus alexaClientEventBus, IUt iUt, Lazy<PersistentStorage> lazy, TimeProvider timeProvider, DialogRequestIdentifier dialogRequestIdentifier, pRk prk, @Nullable Map<String, String> map, long j, Map<XWx, WSC> map2, Map<DialogRequestIdentifier, XWx> map3) {
        super(atomicReference, alexaClientEventBus, iUt, lazy, timeProvider, map2, map3);
        Preconditions.notNull(prk, "Failure reason cannot be null");
        this.yPL = dialogRequestIdentifier;
        this.Mlj = prk;
        this.zzR = map;
        this.lOf = j;
    }

    @Override // com.amazon.alexa.LYb
    public String BIo() {
        return LPk;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (zZm(this.yPL)) {
            zZm(this.Mlj, this.zzR, this.lOf);
        }
        if (!BIo(this.yPL)) {
            Log.w(LPk, String.format("Attempted to report failure on text interaction %s that was not registered", this.yPL));
        }
    }
}
