package com.amazon.alexa;

import com.amazon.alexa.PYA;
import com.google.auto.value.AutoValue;
/* compiled from: PlayerStructure.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class Kyp {

    /* compiled from: PlayerStructure.java */
    @AutoValue.Builder
    /* loaded from: classes.dex */
    public static abstract class zZm {
        public abstract zZm zZm(long j);

        public abstract Kyp zZm();
    }

    public static zZm zZm(vQe vqe) {
        PYA.zZm zzm = new PYA.zZm();
        if (vqe != null) {
            zzm.zZm = vqe;
            zzm.zQM = rjL.zZm();
            zzm.zyO = AbstractC0197ddD.zZm;
            PYA.zZm zzm2 = (PYA.zZm) zzm.zZm(0L);
            zzm2.BIo = NdN.IDLE;
            zzm2.Qle = AKJ.NOT_SHUFFLED;
            zzm2.JTe = XSR.NOT_REPEATED;
            zzm2.LPk = MAh.NOT_RATED;
            return zzm2;
        }
        throw new NullPointerException("Null playerId");
    }
}
