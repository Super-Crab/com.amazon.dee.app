package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.hFk;
/* compiled from: $AutoValue_PlaybackFailedEventPayload.java */
/* renamed from: com.amazon.alexa.Rbt  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractC0181Rbt extends hFk {
    public final Vma BIo;
    public final hFk.BIo zQM;
    public final Puy zZm;

    /* compiled from: $AutoValue_PlaybackFailedEventPayload.java */
    /* renamed from: com.amazon.alexa.Rbt$zZm */
    /* loaded from: classes.dex */
    static final class zZm extends hFk.zZm {
        public Vma BIo;
        public hFk.BIo zQM;
        public Puy zZm;
    }

    public AbstractC0181Rbt(@Nullable Puy puy, @Nullable Vma vma, @Nullable hFk.BIo bIo) {
        this.zZm = puy;
        this.BIo = vma;
        this.zQM = bIo;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof hFk)) {
            return false;
        }
        Puy puy = this.zZm;
        if (puy != null ? puy.equals(((AbstractC0181Rbt) obj).zZm) : ((AbstractC0181Rbt) obj).zZm == null) {
            Vma vma = this.BIo;
            if (vma != null ? vma.equals(((AbstractC0181Rbt) obj).BIo) : ((AbstractC0181Rbt) obj).BIo == null) {
                hFk.BIo bIo = this.zQM;
                if (bIo == null) {
                    if (((AbstractC0181Rbt) obj).zQM == null) {
                        return true;
                    }
                } else if (bIo.equals(((AbstractC0181Rbt) obj).zQM)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        Puy puy = this.zZm;
        int i = 0;
        int hashCode = ((puy == null ? 0 : puy.hashCode()) ^ 1000003) * 1000003;
        Vma vma = this.BIo;
        int hashCode2 = (hashCode ^ (vma == null ? 0 : vma.hashCode())) * 1000003;
        hFk.BIo bIo = this.zQM;
        if (bIo != null) {
            i = bIo.hashCode();
        }
        return hashCode2 ^ i;
    }

    public String toString() {
        StringBuilder zZm2 = C0179Pya.zZm("PlaybackFailedEventPayload{token=");
        zZm2.append(this.zZm);
        zZm2.append(", currentPlaybackState=");
        zZm2.append(this.BIo);
        zZm2.append(", error=");
        return C0179Pya.BIo(zZm2, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
