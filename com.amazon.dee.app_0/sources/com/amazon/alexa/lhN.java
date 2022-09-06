package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.componentstate.ComponentState;
import com.amazon.alexa.client.metrics.core.DeviceInformation;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Singleton;
/* compiled from: ComponentStateAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class lhN {
    public static final String zZm = "lhN";
    public final YKQ BIo;
    public final DeviceInformation JTe;
    public final Object LPk = new Object();
    public final zmg Qle;
    public final C0245zoo jiA;
    public final lEV zQM;
    public final Set<pna> zyO;

    public lhN(YKQ ykq, lEV lev, Set<pna> set, C0245zoo c0245zoo, zmg zmgVar, DeviceInformation deviceInformation) {
        this.BIo = ykq;
        this.zQM = lev;
        this.zyO = set;
        this.jiA = c0245zoo;
        this.Qle = zmgVar;
        this.JTe = deviceInformation;
    }

    public Set<ComponentState> BIo(boolean z) {
        HashSet hashSet = new HashSet();
        hashSet.addAll(this.BIo.zZm());
        hashSet.addAll(this.zQM.zZm(z));
        for (pna pnaVar : this.zyO) {
            pnaVar.zZm(hashSet);
        }
        return hashSet;
    }

    public Set<ComponentState> zZm() {
        return zZm(false);
    }

    public Set<ComponentState> zZm(boolean z) {
        boolean zZm2;
        Set<ComponentState> BIo = BIo(z);
        C0179Pya.zZm("Total states: ", (Object) BIo);
        synchronized (this.LPk) {
            this.jiA.zZm(BIo);
            zZm2 = this.jiA.zZm();
        }
        return (!zZm2 || !this.JTe.isFireOS()) ? BIo : this.Qle.zQM(BIo);
    }
}
