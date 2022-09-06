package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.nFo;
import com.google.auto.value.AutoValue;
/* compiled from: DialogInteractionState.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class WSC {
    public static final WSC BIo;
    public static final String zZm = "WSC";

    /* compiled from: DialogInteractionState.java */
    @AutoValue.Builder
    /* loaded from: classes.dex */
    public static abstract class zZm {
        public WSC BIo() {
            nFo.zZm zzm = (nFo.zZm) this;
            if (zzm.BIo == null) {
                zzm.BIo = DialogRequestIdentifier.NONE;
            }
            if (zzm.zZm == null) {
                zzm.zZm = XWx.zZm;
            }
            if (zzm.zQM == null) {
                zzm.zQM = "Unknown";
            }
            if (zzm.zyO != null) {
                return zZm();
            }
            throw new IllegalStateException("Property \"softwareVersion\" has not been set");
        }

        public abstract zZm zZm(long j);

        public abstract zZm zZm(YOj yOj);

        public abstract zZm zZm(String str);

        public abstract WSC zZm();
    }

    static {
        nFo.zZm zzm = (nFo.zZm) zZm();
        zzm.zQM = "";
        BIo = zzm.zZm("").zZm(0L).zZm(YOj.NEW).BIo();
    }

    public static zZm zZm() {
        return new nFo.zZm();
    }

    public static WSC zZm(WSC wsc, DialogRequestIdentifier dialogRequestIdentifier) {
        nFo.zZm zzm = (nFo.zZm) zZm();
        zzm.BIo = dialogRequestIdentifier;
        nFo nfo = (nFo) wsc;
        zzm.zZm = nfo.zQM;
        zzm.zQM = nfo.jiA;
        return zzm.zZm(nfo.Qle).zZm(nfo.JTe).zZm(nfo.LPk).BIo();
    }

    public static WSC zZm(WSC wsc, String str) {
        nFo nfo = (nFo) wsc;
        nFo.zZm zzm = (nFo.zZm) zZm();
        zzm.BIo = nfo.zyO;
        zzm.zZm = nfo.zQM;
        zzm.zQM = str;
        return zzm.zZm(nfo.Qle).zZm(nfo.JTe).zZm(nfo.LPk).BIo();
    }

    public static WSC zZm(WSC wsc, YOj yOj) {
        String str = zZm;
        Log.i(str, "progress update to: " + yOj);
        nFo nfo = (nFo) wsc;
        nFo.zZm zzm = (nFo.zZm) zZm();
        zzm.BIo = nfo.zyO;
        zzm.zZm = nfo.zQM;
        zzm.zQM = nfo.jiA;
        return zzm.zZm(nfo.Qle).zZm(nfo.JTe).zZm(yOj).BIo();
    }
}
