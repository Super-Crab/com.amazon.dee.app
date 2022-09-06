package com.amazon.alexa;

import android.text.TextUtils;
import android.util.Log;
import com.amazon.alexa.BaP;
import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.alexa.api.AlexaMetricsName;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.kbp;
import com.amazon.alexa.nFo;
import com.amazon.alexa.utils.TimeProvider;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
/* compiled from: LoadVoiceMetricsFromPersistentStorageTask.java */
/* loaded from: classes.dex */
public class DTf extends QIr {
    public static final String LPk = "DTf";

    public DTf(AtomicReference<WSC> atomicReference, AlexaClientEventBus alexaClientEventBus, IUt iUt, Lazy<PersistentStorage> lazy, TimeProvider timeProvider, Map<XWx, WSC> map, Map<DialogRequestIdentifier, XWx> map2) {
        super(atomicReference, alexaClientEventBus, iUt, lazy, timeProvider, map, map2);
    }

    @Override // com.amazon.alexa.LYb
    public String BIo() {
        return LPk;
    }

    @Override // java.lang.Runnable
    public void run() {
        YOj yOj;
        kbp.zyO zZm;
        PersistentStorage mo358get = this.zQM.mo358get();
        String string = mo358get.getString(AccessoryMetricsConstants.DIALOG_TURN_ID, "");
        boolean z = false;
        XWx zZm2 = TextUtils.isEmpty(string) ? XWx.zZm : XWx.zZm(string, false);
        String string2 = mo358get.getString("dialogRequestId", "");
        DialogRequestIdentifier create = TextUtils.isEmpty(string2) ? DialogRequestIdentifier.NONE : DialogRequestIdentifier.create(string2);
        String string3 = mo358get.getString("voiceInteractionInvocationType", "");
        long j = mo358get.getLong("voiceInteractionAttemptTime", 0L);
        String string4 = mo358get.getString("voiceInteractionProgress", "");
        if (TextUtils.isEmpty(string4)) {
            yOj = YOj.NEW;
        } else {
            try {
                yOj = YOj.valueOf(string4);
            } catch (IllegalArgumentException e) {
                Log.e(LPk, String.format("failed to transform %s into VoiceInteractionProgress", string4), e);
                yOj = YOj.NEW;
            }
        }
        String string5 = mo358get.getString("voiceInteractionSoftwareVersion", "");
        nFo.zZm zzm = (nFo.zZm) WSC.zZm();
        zzm.zZm = zZm2;
        zzm.BIo = create;
        zzm.zQM = string3;
        WSC BIo = zzm.zZm(j).zZm(yOj).zZm(string5).BIo();
        List<VZt> zQM = ((iQX) this.jiA).zQM();
        for (VZt vZt : zQM) {
            pRk zZm3 = zZm(vZt);
            if (zZm3 == null) {
                zZm = null;
            } else {
                huZ huz = (huZ) vZt;
                zZm = kbp.zyO.zZm(huz.LPk, huz.JTe, zZm3);
            }
            if (zZm != null) {
                this.BIo.zyO(zZm);
            } else {
                GeneratedOutlineSupport1.outline179(C0179Pya.zZm("Client metric data is not voice interaction event. "), ((huZ) vZt).BIo, LPk);
            }
        }
        if (!WSC.BIo.equals(BIo)) {
            nFo nfo = (nFo) BIo;
            XWx xWx = nfo.zQM;
            if (xWx != null) {
                Iterator<VZt> it2 = zQM.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    } else if (xWx.getValue().equals(((huZ) it2.next()).yPL)) {
                        z = true;
                        break;
                    }
                }
            }
            if (!z) {
                String str = nfo.jiA;
                String str2 = nfo.Qle;
                Pmp pmp = Pmp.FAILURE;
                bij bijVar = bij.INTERNAL_CLIENT_ERROR_UNKNOWN_FAILURE;
                this.BIo.zyO(BaP.zZm.zZm(str, DialogRequestIdentifier.NONE, str2));
                this.BIo.zyO(BaP.zQM.zZm(str, DialogRequestIdentifier.NONE, pmp, bijVar, null, 0L));
                this.BIo.zyO(new hZA(AlexaMetricsName.VoiceInteraction.PROGRESS.appendWith(nfo.LPk.name()), "", DialogRequestIdentifier.NONE));
            }
            if (!WSC.BIo.equals(zZm())) {
                return;
            }
            zyO();
        }
    }
}
