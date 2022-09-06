package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.KPH;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
/* compiled from: VisualChannel.java */
/* loaded from: classes.dex */
public class dfe extends KPH<AOq> {
    public static final String zyO = "dfe";
    public final Mpk JTe;
    public final AlexaClientEventBus Qle;
    public final JiQ jiA;

    public dfe(JiQ jiQ, AlexaClientEventBus alexaClientEventBus, Mpk mpk, Set<dnp> set) {
        super(set, Collections.synchronizedList(new LinkedList()));
        this.jiA = jiQ;
        this.Qle = alexaClientEventBus;
        this.JTe = mpk;
    }

    @Nullable
    public synchronized aNh LPk() {
        return this.JTe.zyO(this.jiA);
    }

    @Override // com.amazon.alexa.KPH
    public void zZm(boolean z) {
        aNh anh;
        if (z) {
            aNh zyO2 = this.JTe.zyO(this.jiA);
            boolean zQM = zQM();
            dnp dnpVar = null;
            if (zQM) {
                StringBuilder zZm = C0179Pya.zZm("setting channel ");
                zZm.append(this.jiA.name());
                zZm.append(" to active");
                zZm.toString();
                AOq Qle = Qle();
                if (Qle != null) {
                    anh = aNh.zZm(Qle.zZm());
                    this.JTe.BIo(this.jiA, anh);
                } else {
                    throw new IllegalStateException("Channel is active but has no active interactions");
                }
            } else {
                if (zyO2 != null) {
                    hgr hgrVar = (hgr) zyO2;
                    if (hgrVar.zQM) {
                        StringBuilder zZm2 = C0179Pya.zZm("setting channel ");
                        zZm2.append(this.jiA.name());
                        zZm2.append(" to inactive");
                        zZm2.toString();
                        anh = aNh.zZm(hgrVar.zZm, 0L);
                        this.JTe.BIo(this.jiA, anh);
                    }
                }
                anh = null;
            }
            if (anh != null) {
                dnpVar = ((hgr) anh).zZm;
            }
            AlexaClientEventBus alexaClientEventBus = this.Qle;
            JiQ jiQ = this.jiA;
            if (dnpVar == null) {
                dnpVar = dnp.zZm;
            }
            alexaClientEventBus.zyO(new ofP(jiQ, zQM, dnpVar));
        }
        synchronized (this.BIo) {
            Iterator<KPH.zZm> it2 = this.BIo.iterator();
            while (it2.hasNext()) {
                QJr.this.Mlj();
            }
        }
    }
}
