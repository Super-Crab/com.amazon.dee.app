package com.amazon.alexa;

import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ExternalMediaPlayerUpdateEvent.java */
/* loaded from: classes.dex */
public final class NMu extends Qrm {
    public final MediaMetadataCompat BIo;
    public final PlaybackStateCompat zQM;
    public final vQe zyO;

    public NMu(@Nullable MediaMetadataCompat mediaMetadataCompat, @Nullable PlaybackStateCompat playbackStateCompat, vQe vqe) {
        this.BIo = mediaMetadataCompat;
        this.zQM = playbackStateCompat;
        if (vqe != null) {
            this.zyO = vqe;
            return;
        }
        throw new NullPointerException("Null externalPlayerIdentifier");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Qrm)) {
            return false;
        }
        MediaMetadataCompat mediaMetadataCompat = this.BIo;
        if (mediaMetadataCompat != null ? mediaMetadataCompat.equals(((NMu) obj).BIo) : ((NMu) obj).BIo == null) {
            PlaybackStateCompat playbackStateCompat = this.zQM;
            if (playbackStateCompat != null ? playbackStateCompat.equals(((NMu) obj).zQM) : ((NMu) obj).zQM == null) {
                if (this.zyO.equals(((NMu) obj).zyO)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        MediaMetadataCompat mediaMetadataCompat = this.BIo;
        int i = 0;
        int hashCode = ((mediaMetadataCompat == null ? 0 : mediaMetadataCompat.hashCode()) ^ 1000003) * 1000003;
        PlaybackStateCompat playbackStateCompat = this.zQM;
        if (playbackStateCompat != null) {
            i = playbackStateCompat.hashCode();
        }
        return ((hashCode ^ i) * 1000003) ^ this.zyO.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("ExternalMediaPlayerUpdateEvent{mediaMetadata=");
        zZm.append(this.BIo);
        zZm.append(", playbackState=");
        zZm.append(this.zQM);
        zZm.append(", externalPlayerIdentifier=");
        return C0179Pya.BIo(zZm, this.zyO, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
