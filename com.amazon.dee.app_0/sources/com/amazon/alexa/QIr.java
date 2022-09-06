package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.BaP;
import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.utils.TimeProvider;
import dagger.Lazy;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
/* compiled from: VoiceInteractionMetricTask.java */
@VisibleForTesting
/* loaded from: classes.dex */
public abstract class QIr extends LYb {
    public QIr(AtomicReference<WSC> atomicReference, AlexaClientEventBus alexaClientEventBus, IUt iUt, Lazy<PersistentStorage> lazy, TimeProvider timeProvider, Map<XWx, WSC> map, Map<DialogRequestIdentifier, XWx> map2) {
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
        this.BIo.zyO(BaP.zQM.zZm(nfo.jiA, nfo.zyO, pmp, prk, map, j - nfo.JTe));
        zyO();
    }

    @Override // com.amazon.alexa.LYb
    public WSC zZm() {
        return this.zyO.get();
    }

    @Override // com.amazon.alexa.LYb
    public void zZm(WSC wsc) {
        nFo nfo = (nFo) wsc;
        Log.i(BIo(), String.format("Reporting attempt for %s (%s)", nfo.zQM, nfo.jiA));
        this.BIo.zyO(BaP.zZm.zZm(nfo.jiA, nfo.zyO, nfo.Qle));
    }

    @Override // com.amazon.alexa.LYb
    public void BIo(WSC wsc) {
        this.zyO.set(wsc);
        nFo nfo = (nFo) wsc;
        this.zQM.mo358get().edit().set(AccessoryMetricsConstants.DIALOG_TURN_ID, nfo.zQM.getValue()).set("dialogRequestId", nfo.zyO.getValue()).set("voiceInteractionInvocationType", nfo.jiA).set("voiceInteractionAttemptTime", nfo.JTe).set("voiceInteractionProgress", nfo.LPk.name()).set("voiceInteractionSoftwareVersion", nfo.Qle).commitSynchronously();
    }

    @Nullable
    public pRk zZm(VZt vZt) {
        pRk zZm = zZm(vZt, (pRk[]) bij.values(), false);
        return zZm == null ? zZm(vZt, (pRk[]) mMl.values(), false) : zZm;
    }
}
