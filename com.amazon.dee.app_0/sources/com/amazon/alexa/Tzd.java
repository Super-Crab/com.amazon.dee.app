package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.C0235ujQ;
import com.amazon.alexa.KPH;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.utils.TimeProvider;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
/* compiled from: AudioChannel.java */
/* loaded from: classes.dex */
public class Tzd extends KPH<tYL> {
    public static final String zyO = "Tzd";
    public final AzE JTe;
    public final TimeProvider LPk;
    public final AlexaClientEventBus Qle;
    public final aVo jiA;

    public Tzd(aVo avo, AlexaClientEventBus alexaClientEventBus, AzE azE, TimeProvider timeProvider, Set<dnp> set) {
        super(set, Collections.synchronizedList(new LinkedList()));
        this.jiA = avo;
        this.Qle = alexaClientEventBus;
        this.JTe = azE;
        this.LPk = timeProvider;
    }

    @Override // com.amazon.alexa.KPH
    public synchronized void JTe() {
        for (T t : this.zZm) {
            if (!t.JTe() && !t.BIo()) {
                t.zQM();
            }
        }
    }

    public synchronized void LPk() {
        for (T t : this.zZm) {
            if (!t.JTe() && !t.LPk()) {
                t.zyO();
            }
        }
    }

    public synchronized void Mlj() {
        Iterator it2 = this.zZm.iterator();
        while (it2.hasNext()) {
            if (((tYL) it2.next()).Qle.zZm()) {
                it2.remove();
            }
        }
    }

    public synchronized void dMe() {
        for (T t : this.zZm) {
            if (!t.JTe()) {
                if (!(t.zZm == C0235ujQ.zZm.PAUSED)) {
                    t.yPL();
                }
            }
        }
    }

    public synchronized boolean lOf() {
        boolean z;
        Iterator it2 = this.zZm.iterator();
        while (true) {
            if (!it2.hasNext()) {
                z = false;
                break;
            } else if (((tYL) it2.next()).Qle.zZm()) {
                z = true;
                break;
            }
        }
        return z;
    }

    public synchronized void uzr() {
        for (T t : this.zZm) {
            if (!t.JTe() && t.Qle.zZm()) {
                t.zQM();
            }
        }
    }

    public synchronized void yPL() {
        tYL jiA = jiA();
        for (T t : this.zZm) {
            if (!t.JTe() && !t.equals(jiA) && !t.LPk()) {
                t.zyO();
            }
        }
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
                tYL Qle = Qle();
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
                        anh = aNh.zZm(hgrVar.zZm, this.LPk.currentTimeMillis());
                        this.JTe.BIo(this.jiA, anh);
                    }
                }
                anh = null;
            }
            if (anh != null) {
                dnpVar = ((hgr) anh).zZm;
            }
            AlexaClientEventBus alexaClientEventBus = this.Qle;
            aVo avo = this.jiA;
            if (dnpVar == null) {
                dnpVar = dnp.zZm;
            }
            alexaClientEventBus.zyO(new C0169GLa(avo, zQM, dnpVar));
        }
        synchronized (this.BIo) {
            Iterator<KPH.zZm> it2 = this.BIo.iterator();
            while (it2.hasNext()) {
                QJr.this.Mlj();
            }
        }
    }

    @Nullable
    public synchronized aNh zzR() {
        return this.JTe.zyO(this.jiA);
    }
}
