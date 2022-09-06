package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
/* compiled from: AudioMetadata.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class PJz {

    /* compiled from: AudioMetadata.java */
    /* loaded from: classes.dex */
    public enum BIo {
        PERSISTENT(1),
        TRANSIENT_EXCLUSIVE(4),
        TRANSIENT(2),
        TRANSIENT_MAY_DUCK(3),
        NO_AUDIOFOCUS(Integer.MAX_VALUE);
        
        public final int value;

        BIo(int i) {
            this.value = i;
        }

        public int zZm() {
            return this.value;
        }
    }

    /* compiled from: AudioMetadata.java */
    /* loaded from: classes.dex */
    public enum zQM {
        ALARM(4),
        DTMF(8),
        MUSIC(3),
        NOTIFICATION(5),
        RING(2),
        SYSTEM(1),
        VOICE_CALL(0),
        EXTERNAL_STREAM(-1);
        
        public final int value;

        zQM(int i) {
            this.value = i;
        }

        public int zZm() {
            return this.value;
        }
    }

    /* compiled from: AudioMetadata.java */
    /* loaded from: classes.dex */
    public enum zZm {
        MOVIE(3),
        MUSIC(2),
        SPEECH(1),
        UNKNOWN(0);
        
        public final int value;

        zZm(int i) {
            this.value = i;
        }

        public int zZm() {
            return this.value;
        }
    }

    /* compiled from: AudioMetadata.java */
    /* loaded from: classes.dex */
    public enum zyO {
        ALARM(4),
        MEDIA(1),
        NOTIFICATION(5),
        RINGTONE(6),
        UNKNOWN(0),
        VOICE(2);
        
        public final int value;

        zyO(int i) {
            this.value = i;
        }

        public int zZm() {
            return this.value;
        }
    }

    public static PJz zZm(zQM zqm, BIo bIo) {
        return zZm(zqm, bIo, zyO.UNKNOWN, zZm.UNKNOWN);
    }

    public static PJz zZm(zQM zqm, BIo bIo, zyO zyo, zZm zzm) {
        return new PNy(zqm, bIo, zyo, zzm, true);
    }

    public boolean zZm() {
        PNy pNy = (PNy) this;
        return !zQM.EXTERNAL_STREAM.equals(pNy.zZm) && !BIo.NO_AUDIOFOCUS.equals(pNy.BIo);
    }

    public boolean zZm(@Nullable PJz pJz) {
        if (pJz != null) {
            PNy pNy = (PNy) this;
            PNy pNy2 = (PNy) pJz;
            if (pNy.BIo == pNy2.BIo && zZm() == pJz.zZm() && pNy.zQM == pNy2.zQM && pNy.zyO == pNy2.zyO) {
                return false;
            }
        }
        return true;
    }
}
