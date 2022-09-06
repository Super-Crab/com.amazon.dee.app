package com.amazon.alexa;

import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.Kqq;
import com.google.auto.value.AutoValue;
/* compiled from: ExternalMediaPlayerUpdateEvent.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class Qrm extends Kqq.zZm {
    public static Qrm zZm(@NonNull vQe vqe, @Nullable PlaybackStateCompat playbackStateCompat, @Nullable MediaMetadataCompat mediaMetadataCompat) {
        return new NMu(mediaMetadataCompat, playbackStateCompat, vqe);
    }
}
