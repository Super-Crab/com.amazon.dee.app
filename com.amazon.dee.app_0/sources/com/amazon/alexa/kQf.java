package com.amazon.alexa;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.amazon.alexa.fcj;
import java.util.Objects;
/* compiled from: PlayItem.java */
/* loaded from: classes.dex */
public class kQf {
    public final xNT BIo;
    public final boolean JTe;
    public final fcj.zZm Qle;
    public final CiJ jiA;
    public final Uri zQM;
    public final Puy zZm;
    public final long zyO;

    public kQf(Puy puy, Uri uri, long j, @Nullable xNT xnt, @Nullable CiJ ciJ, @Nullable fcj.zZm zzm, boolean z) {
        this.zZm = puy;
        if (xnt == null) {
            this.BIo = xNT.BIo;
        } else {
            this.BIo = xnt;
        }
        this.zQM = uri;
        this.zyO = j;
        this.jiA = ciJ;
        if (zzm == null) {
            this.Qle = fcj.zZm.PAUSE;
        } else {
            this.Qle = zzm;
        }
        this.JTe = z;
    }

    /* renamed from: BIo */
    public Puy mo947BIo() {
        return this.zZm;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        kQf kqf = (kQf) obj;
        return this.zyO == kqf.zyO && Objects.equals(this.zZm, kqf.zZm) && Objects.equals(this.zQM, kqf.zQM) && Objects.equals(this.BIo, kqf.BIo) && Objects.equals(this.jiA, kqf.jiA) && Objects.equals(this.Qle, kqf.Qle);
    }

    public int hashCode() {
        return Objects.hash(this.zZm, this.zQM, Long.valueOf(this.zyO), this.jiA, this.BIo, this.Qle);
    }

    public boolean zQM() {
        return this.zQM.toString().startsWith("cid:");
    }

    public cIy zZm() {
        return cIy.zZm(this.zQM.toString().substring(4));
    }

    public static kQf zZm(Puy puy, Uri uri, long j, @Nullable xNT xnt, @Nullable CiJ ciJ, @Nullable fcj.zZm zzm) {
        return new kQf(puy, uri, j, xnt, ciJ, zzm, false);
    }
}
