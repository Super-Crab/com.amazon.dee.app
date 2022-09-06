package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.ARM;
import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.alexa.api.AlexaMetricsName;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.utils.TimeProvider;
import dagger.Lazy;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
/* compiled from: TextInteractionMetricTask.java */
@VisibleForTesting
/* loaded from: classes.dex */
public abstract class UeS extends LYb {
    public UeS(AtomicReference<WSC> atomicReference, AlexaClientEventBus alexaClientEventBus, IUt iUt, Lazy<PersistentStorage> lazy, TimeProvider timeProvider, Map<XWx, WSC> map, Map<DialogRequestIdentifier, XWx> map2) {
        this.zyO = atomicReference;
        this.BIo = alexaClientEventBus;
        this.jiA = iUt;
        this.zQM = lazy;
        this.zZm = timeProvider;
        this.Qle = map;
        this.JTe = map2;
    }

    @Override // com.amazon.alexa.LYb
    public void BIo(WSC wsc, Pmp pmp, @Nullable pRk prk, @Nullable Map<String, String> map, long j) {
        nFo nfo = (nFo) wsc;
        this.BIo.zyO(ARM.BIo.zZm(nfo.jiA, nfo.zyO, pmp, prk, map, j - nfo.JTe));
        zyO();
    }

    @Override // com.amazon.alexa.LYb
    public void zZm(WSC wsc) {
        nFo nfo = (nFo) wsc;
        Log.i(BIo(), String.format("Reporting attempt for %s (%s)", nfo.zQM, nfo.jiA));
        AlexaClientEventBus alexaClientEventBus = this.BIo;
        String str = nfo.jiA;
        alexaClientEventBus.zyO(new zaQ(AlexaMetricsName.TextInteraction.ATTEMPT.appendWith(str), str, nfo.zyO, nfo.Qle));
    }

    @Override // com.amazon.alexa.LYb
    public void BIo(WSC wsc) {
        this.zyO.set(wsc);
        nFo nfo = (nFo) wsc;
        this.zQM.mo358get().edit().set(AccessoryMetricsConstants.DIALOG_TURN_ID, nfo.zQM.getValue()).set("dialogRequestId", nfo.zyO.getValue()).set("textInteractionInvocationType", nfo.jiA).set("textInteractionAttemptTime", nfo.JTe).set("textInteractionSoftwareVersion", nfo.Qle).commitSynchronously();
    }

    @Nullable
    public pRk zZm(VZt vZt) {
        pRk zZm = zZm(vZt, (pRk[]) bij.values(), true);
        return zZm == null ? zZm(vZt, (pRk[]) mMl.values(), true) : zZm;
    }
}
