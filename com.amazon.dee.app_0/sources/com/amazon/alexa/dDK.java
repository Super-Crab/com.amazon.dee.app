package com.amazon.alexa;

import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: SpeechPlayer.java */
@Singleton
/* loaded from: classes.dex */
public class dDK {
    public boolean BIo;
    public final uxN zZm;

    /* compiled from: SpeechPlayer.java */
    /* loaded from: classes.dex */
    static class zZm implements NTV {
        public boolean BIo;
        public final xkq zZm;

        public zZm(xkq xkqVar) {
            this.zZm = xkqVar;
        }

        @Override // com.amazon.alexa.NTV
        public void BIo(kQf kqf) {
        }

        @Override // com.amazon.alexa.NTV
        public void JTe(kQf kqf) {
        }

        @Override // com.amazon.alexa.NTV
        public void LPk(kQf kqf) {
            this.zZm.zyO(lOf(kqf));
        }

        @Override // com.amazon.alexa.NTV
        public void Mlj(kQf kqf) {
        }

        @Override // com.amazon.alexa.NTV
        public void Qle(kQf kqf) {
        }

        @Override // com.amazon.alexa.NTV
        public void jiA(kQf kqf) {
        }

        public final bqj lOf(kQf kqf) {
            if (kqf instanceof bqj) {
                return (bqj) kqf;
            }
            throw new IllegalArgumentException("PlayItem is not a SpeechItem");
        }

        @Override // com.amazon.alexa.NTV
        public void yPL(kQf kqf) {
            this.zZm.zZm(lOf(kqf));
        }

        @Override // com.amazon.alexa.NTV
        public void zQM(kQf kqf) {
            this.zZm.BIo(lOf(kqf));
        }

        @Override // com.amazon.alexa.NTV
        public void zZm(kQf kqf) {
        }

        @Override // com.amazon.alexa.NTV
        public void zZm(kQf kqf, long j, Exception exc) {
            this.zZm.zZm(lOf(kqf), exc);
        }

        @Override // com.amazon.alexa.NTV
        public void zyO(kQf kqf) {
        }

        @Override // com.amazon.alexa.NTV
        public void zzR(kQf kqf) {
            this.zZm.zQM(lOf(kqf));
        }

        @Override // com.amazon.alexa.NTV
        public void zZm(kQf kqf, long j, long j2) {
            if (!this.BIo) {
                this.BIo = true;
                this.zZm.zZm(lOf(kqf), j2 - j);
            }
        }
    }

    @Inject
    public dDK(uxN uxn) {
        this.zZm = uxn;
    }

    public void zZm(bqj bqjVar, xkq xkqVar) {
        ((VIX) this.zZm).zZm(bqjVar, new zZm(xkqVar));
        ((VIX) this.zZm).zzR();
        this.BIo = false;
    }

    public long zZm() {
        return ((VIX) this.zZm).Qle();
    }
}
