package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import java.util.Iterator;
import javax.inject.Inject;
/* compiled from: LoadGenericMetricsFromPersistentStorageTask.java */
/* loaded from: classes.dex */
public class Fnd implements Runnable {
    public final IUt BIo;
    public final AlexaClientEventBus zZm;

    @Inject
    public Fnd(AlexaClientEventBus alexaClientEventBus, IUt iUt) {
        this.zZm = alexaClientEventBus;
        this.BIo = iUt;
    }

    @Override // java.lang.Runnable
    public void run() {
        Iterator<VZt> it2 = ((iQX) this.BIo).zZm().iterator();
        while (it2.hasNext()) {
            huZ huz = (huZ) it2.next();
            String str = huz.BIo;
            Long l = huz.Mlj;
            if (l != null && l.longValue() >= 0) {
                this.zZm.zyO(new gAT(str, huz.Mlj.longValue()));
            } else {
                this.zZm.zyO(new ELT(str, MNR.zZm(huz.zzR)));
            }
        }
    }
}
