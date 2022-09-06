package com.amazon.alexa;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.huZ;
import com.google.auto.value.AutoValue;
/* compiled from: ClientMetricData.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class VZt {
    @VisibleForTesting
    public static final Long zZm = -1L;

    /* compiled from: ClientMetricData.java */
    @AutoValue.Builder
    /* loaded from: classes.dex */
    public static abstract class zZm {
        public abstract zZm BIo(String str);

        public abstract zZm jiA(String str);

        public abstract zZm zQM(String str);

        public abstract zZm zZm(long j);

        public abstract zZm zZm(@Nullable String str);

        public VZt zZm() {
            huZ.zZm zzm = (huZ.zZm) this;
            if (zzm.yPL == null) {
                zzm.yPL = VZt.zZm;
            }
            if (zzm.Mlj != null) {
                String str = "";
                if (zzm.zZm == null) {
                    str = C0179Pya.zZm(str, " eventName");
                }
                if (zzm.BIo == null) {
                    str = C0179Pya.zZm(str, " eventId");
                }
                if (zzm.zQM == null) {
                    str = C0179Pya.zZm(str, " eventTimestampMs");
                }
                if (zzm.zyO == null) {
                    str = C0179Pya.zZm(str, " sourcePackageName");
                }
                if (zzm.jiA == null) {
                    str = C0179Pya.zZm(str, " clientPackageName");
                }
                if (zzm.Qle == null) {
                    str = C0179Pya.zZm(str, " softwareVersion");
                }
                if (zzm.Mlj == null) {
                    str = C0179Pya.zZm(str, " apiCallId");
                }
                if (str.isEmpty()) {
                    return new huZ(zzm.zZm, zzm.BIo, zzm.zQM.longValue(), zzm.zyO, zzm.jiA, zzm.Qle, zzm.JTe, zzm.LPk, zzm.yPL, zzm.Mlj, null);
                }
                throw new IllegalStateException(C0179Pya.zZm("Missing required properties:", str));
            }
            throw new IllegalStateException("Property \"apiCallId\" has not been set");
        }

        public abstract zZm zyO(String str);
    }

    public static zZm zZm() {
        return new huZ.zZm().zZm(((C0185YfC) MNR.zZm).BIo);
    }
}
