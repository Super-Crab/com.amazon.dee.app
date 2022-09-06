package com.amazon.alexa;

import com.amazon.alexa.Kqq;
import com.amazon.alexa.WXj;
import com.amazon.alexa.client.core.messages.Message;
import com.google.auto.value.AutoValue;
/* compiled from: SendMessageEvent.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class JjI extends Kqq.zZm {

    /* compiled from: SendMessageEvent.java */
    @AutoValue.Builder
    /* loaded from: classes.dex */
    public static abstract class zZm {
        public abstract zZm zZm(TtM ttM);

        public abstract zZm zZm(Message message);

        public abstract zZm zZm(eOP eop);

        public abstract zZm zZm(boolean z);

        public JjI zZm() {
            WXj.zZm zzm = (WXj.zZm) this;
            if (zzm.Mlj != null) {
                String str = "";
                if (zzm.BIo == null) {
                    str = C0179Pya.zZm(str, " message");
                }
                if (zzm.LPk == null) {
                    str = C0179Pya.zZm(str, " sendMessageCallback");
                }
                if (zzm.Mlj == null) {
                    str = C0179Pya.zZm(str, " apiCallMetadata");
                }
                if (zzm.zzR == null) {
                    str = C0179Pya.zZm(str, " guaranteedDelivery");
                }
                if (str.isEmpty()) {
                    return new WXj(zzm.zZm, zzm.BIo, zzm.zQM, zzm.zyO, zzm.jiA, zzm.Qle, zzm.JTe, zzm.LPk, zzm.yPL, zzm.Mlj, zzm.zzR.booleanValue(), null);
                }
                throw new IllegalStateException(C0179Pya.zZm("Missing required properties:", str));
            }
            throw new IllegalStateException("Property \"apiCallMetadata\" has not been set");
        }
    }

    public static zZm BIo() {
        return new WXj.zZm().zZm(eOP.zZm).zZm(false).zZm(UcG.INSTANCE);
    }

    public boolean zQM() {
        return ((vhv) ((WXj) this).zzR).BIo != MNR.zZm;
    }
}
