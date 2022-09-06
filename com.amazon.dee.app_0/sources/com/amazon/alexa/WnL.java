package com.amazon.alexa;

import androidx.annotation.VisibleForTesting;
import javax.inject.Singleton;
/* compiled from: DownchannelStatusTracker.java */
@Singleton
/* loaded from: classes.dex */
public class WnL {
    public volatile Exception HvC;
    public volatile Integer Mlj;
    public volatile Exception dMe;
    public volatile Integer lOf;
    public volatile Integer uzr;
    public volatile Exception zzR;
    public volatile boolean zZm = true;
    public volatile boolean BIo = true;
    public volatile boolean zQM = false;
    public volatile boolean zyO = true;
    public volatile boolean jiA = true;
    public volatile boolean Qle = true;
    public volatile boolean JTe = true;
    public volatile boolean LPk = true;
    public volatile boolean yPL = true;

    /* compiled from: DownchannelStatusTracker.java */
    /* loaded from: classes.dex */
    public static class BIo extends zQM {
        public final Exception Mlj;

        @VisibleForTesting
        public BIo(zyO zyo, Exception exc) {
            super(zyo);
            this.Mlj = exc;
        }

        @Override // com.amazon.alexa.WnL.zQM
        public BIo BIo() {
            return this;
        }

        @Override // com.amazon.alexa.WnL.zQM
        public boolean zQM() {
            return true;
        }
    }

    /* compiled from: DownchannelStatusTracker.java */
    /* loaded from: classes.dex */
    public static class zQM {
        public final zyO yPL;
        public static final zQM zZm = new zQM(zyO.UNINITIALIZED);
        public static final zQM BIo = new zQM(zyO.UNAUTHORIZED);
        public static final zQM zQM = new zQM(zyO.NETWORK);
        public static final zQM zyO = new zQM(zyO.REFRESH_INTERNAL_CAPABILITIES);
        public static final zQM jiA = new zQM(zyO.REFRESH_EXTERNAL_CAPABILITIES);
        public static final zQM Qle = new zQM(zyO.CONNECTION_NOT_ESTABLISHED);
        public static final zQM JTe = new zQM(zyO.CONNECTION_NOT_CONNECTED);
        public static final zQM LPk = new zQM(zyO.AVAILABLE);

        @VisibleForTesting
        public zQM(zyO zyo) {
            this.yPL = zyo;
        }

        public BIo BIo() {
            throw new UnsupportedOperationException();
        }

        public boolean zQM() {
            return false;
        }

        public zZm zZm() {
            throw new UnsupportedOperationException();
        }

        public boolean zyO() {
            return false;
        }
    }

    /* compiled from: DownchannelStatusTracker.java */
    /* loaded from: classes.dex */
    public static class zZm extends zQM {
        public final int Mlj;

        @VisibleForTesting
        public zZm(zyO zyo, int i) {
            super(zyo);
            this.Mlj = i;
        }

        @Override // com.amazon.alexa.WnL.zQM
        public zZm zZm() {
            return this;
        }

        @Override // com.amazon.alexa.WnL.zQM
        public boolean zyO() {
            return true;
        }
    }

    /* compiled from: DownchannelStatusTracker.java */
    /* loaded from: classes.dex */
    public enum zyO {
        UNINITIALIZED,
        UNAUTHORIZED,
        REFRESH_INTERNAL_CAPABILITIES,
        REFRESH_EXTERNAL_CAPABILITIES,
        NETWORK,
        CONNECTION_NOT_ESTABLISHED,
        CONNECTION_NOT_CONNECTED,
        CAPABILITY_PUBLISH,
        SYNCHRONIZE_STATE,
        AVAILABLE
    }

    public final zQM BIo() {
        if (this.lOf != null) {
            return new zZm(zyO.CAPABILITY_PUBLISH, this.lOf.intValue());
        }
        if (this.dMe != null) {
            return new BIo(zyO.CAPABILITY_PUBLISH, this.dMe);
        }
        return new zQM(zyO.CAPABILITY_PUBLISH);
    }

    public void jiA() {
        this.yPL = true;
    }

    public boolean zQM() {
        return this.JTe;
    }

    public zQM zZm() {
        if (this.zZm) {
            return zQM.zZm;
        }
        if (this.zyO) {
            return zQM.zyO;
        }
        if (this.jiA) {
            return zQM.jiA;
        }
        if (this.zQM) {
            return zQM.BIo;
        }
        if (this.BIo) {
            return zQM.zQM;
        }
        if (this.JTe) {
            if (this.Mlj != null) {
                return new zZm(zyO.CONNECTION_NOT_ESTABLISHED, this.Mlj.intValue());
            }
            if (this.zzR != null) {
                return new BIo(zyO.CONNECTION_NOT_ESTABLISHED, this.zzR);
            }
            return zQM.Qle;
        } else if (this.Qle) {
            return zQM.JTe;
        } else {
            if (this.yPL) {
                if (this.uzr != null) {
                    if (this.LPk) {
                        return BIo();
                    }
                    return new zZm(zyO.SYNCHRONIZE_STATE, this.uzr.intValue());
                } else if (this.HvC != null) {
                    return new BIo(zyO.SYNCHRONIZE_STATE, this.HvC);
                } else {
                    if (this.LPk) {
                        return BIo();
                    }
                    return new zQM(zyO.SYNCHRONIZE_STATE);
                }
            }
            return zQM.LPk;
        }
    }

    public boolean zyO() {
        return this.yPL;
    }
}
