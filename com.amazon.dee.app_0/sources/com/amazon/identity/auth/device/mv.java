package com.amazon.identity.auth.device;

import android.text.TextUtils;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public abstract class mv {

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    static final class a extends mv {
        private static final String TAG = "com.amazon.identity.auth.device.mv$a";
        private final mu lz;
        private final String vC;
        private String vo;
        private boolean vr = false;
        private long vD = -1;
        private long vE = -1;

        public a(mu muVar, String str, String str2) {
            this.lz = muVar;
            this.vC = str;
            this.vo = str2;
        }

        @Override // com.amazon.identity.auth.device.mv
        public void eP(String str) {
            this.vo = str;
        }

        @Override // com.amazon.identity.auth.device.mv
        public void iK() {
            this.vr = true;
        }

        @Override // com.amazon.identity.auth.device.mv
        public void iL() {
            stop();
            iK();
        }

        @Override // com.amazon.identity.auth.device.mv
        public void iM() {
            this.vE = System.nanoTime();
        }

        @Override // com.amazon.identity.auth.device.mv
        public void start() {
            this.vD = System.nanoTime();
        }

        @Override // com.amazon.identity.auth.device.mv
        public void stop() {
            long nanoTime;
            if (TextUtils.isEmpty(this.vo)) {
                io.dm(TAG);
            } else if (this.vr) {
            } else {
                long j = this.vD;
                if (j < 0) {
                    String str = TAG;
                    new StringBuilder("Timer not started: ").append(this.vo);
                    io.dm(str);
                    return;
                }
                long j2 = this.vE;
                if (j2 > 0) {
                    nanoTime = (j2 - j) / 1000000;
                } else {
                    nanoTime = (System.nanoTime() - this.vD) / 1000000;
                }
                this.vD = -1L;
                this.vE = -1L;
                mu muVar = this.lz;
                if (muVar == null) {
                    io.w(TAG, "Could not record timer because no collector was set");
                } else {
                    muVar.a(this.vC, this.vo, nanoTime);
                }
            }
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static final class b extends mv {
        @Override // com.amazon.identity.auth.device.mv
        public void eP(String str) {
        }

        @Override // com.amazon.identity.auth.device.mv
        public void iK() {
        }

        @Override // com.amazon.identity.auth.device.mv
        public void iL() {
        }

        @Override // com.amazon.identity.auth.device.mv
        public void iM() {
        }

        @Override // com.amazon.identity.auth.device.mv
        public void start() {
        }

        @Override // com.amazon.identity.auth.device.mv
        public void stop() {
        }
    }

    public static mv a(mu muVar, String str, String str2) {
        if (muVar != null) {
            return new a(muVar, str, str2);
        }
        return new b();
    }

    public abstract void eP(String str);

    public abstract void iK();

    public abstract void iL();

    public abstract void iM();

    public abstract void start();

    public abstract void stop();
}
